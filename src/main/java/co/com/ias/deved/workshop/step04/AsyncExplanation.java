package co.com.ias.deved.workshop.step04;

import co.com.ias.deved.workshop.domain.Cake;
import co.com.ias.deved.workshop.domain.Juice;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AsyncExplanation {

    public static CompletableFuture<List<Juice>> queryJuices(Predicate<Juice> predicate) {
        List<Juice> list = Arrays.asList(
                new Juice("Blueberries", 250, Juice.Type.FIT),
                new Juice("Banana", 250, Juice.Type.RELAX),
                new Juice("Strawberries + Blueberries + nuts", 600, Juice.Type.FAT),
                new Juice("Pineapple", 250, Juice.Type.FRESH),
                new Juice("Carrot", 180, Juice.Type.FIT),
                new Juice("pineapple", 450, Juice.Type.FAT),
                new Juice("Must", 500, Juice.Type.FAT),
                new Juice("Passion", 500, Juice.Type.FAT),
                new Juice("Tomato", 250, Juice.Type.FRESH),
                new Juice("Lemonade", 150, Juice.Type.FRESH),
                new Juice("Melon", 300, Juice.Type.RELAX)
        );
        CompletableFuture<List<Juice>> future = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            List<Juice> collect = list.stream().filter(predicate).collect(Collectors.toList());
            future.complete(collect);
            return null;
        });
        return future;
    }

    public static CompletableFuture<List<Cake>> queryCakes(Predicate<Cake> predicate) {
        List<Cake> list = Arrays.asList(
                new Cake("Banana", 300, Cake.Type.RELAX),
                new Cake("Carrot", 280, Cake.Type.FIT),
                new Cake("Chocolate", 350, Cake.Type.FIT),
                new Cake("Lemon Pie", 200, Cake.Type.FIT),
                new Cake("Vanilla", 190, Cake.Type.FIT),
                new Cake("Birthday", 300, Cake.Type.FAT),
                new Cake("Gluten free", 200, Cake.Type.FIT),
                new Cake("Cheese cake", 600, Cake.Type.FAT)
        );
        CompletableFuture<List<Cake>> future = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(1000);
            List<Cake> collect = list.stream().filter(predicate).collect(Collectors.toList());
            future.complete(collect);
            return null;
        });
        return future;
    }

    /**
     * Joining multiple completable futures
     *
     * @return CompletableFuture with joined results
     */
    public static CompletableFuture<Pair<List<Cake>, List<Juice>>> fitCakesAndJuices() {
        Predicate<Juice> fitJuiceOnly = juice -> juice.getType().equals(Juice.Type.FIT);
        Predicate<Cake> fitCakeOnly = cake -> cake.getType().equals(Cake.Type.FIT);
        CompletableFuture<List<Cake>> cakes = queryCakes(fitCakeOnly);
        CompletableFuture<List<Juice>> juices = queryJuices(fitJuiceOnly);
        return CompletableFuture.allOf(cakes, juices)
                .thenApply(aVoid -> Pair.of(cakes.join(), juices.join()));
    }
}
