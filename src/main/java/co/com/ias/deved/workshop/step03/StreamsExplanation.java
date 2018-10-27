package co.com.ias.deved.workshop.step03;

import co.com.ias.deved.workshop.domain.Juice;
import co.com.ias.deved.workshop.domain.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamsExplanation {

    /**
     * Collection transformation using old java <= 7
     *
     * @param juices list to transform
     * @return transformed list
     */
    public static List<String> getLowCaloriesJuicesName(List<Juice> juices) {
        List<Juice> lowCaloricDishes = new ArrayList<>();
        for (Juice juice : juices) {
            if (juice.getCalories() < 400) {
                lowCaloricDishes.add(juice);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Juice>() {
            public int compare(Juice juice1, Juice juice2) {
                return Integer.compare(juice1.getCalories(), juice2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Juice juice : lowCaloricDishes) {
            lowCaloricDishesName.add(juice.getName());
        }
        return lowCaloricDishesName;
    }

    /**
     * Collection processing using java 8
     *
     * @param juices list to transform
     * @return transformed list
     */
    public static List<String> getLowCaloriesJuicesNameUsingStreams(List<Juice> juices) {
        return juices.stream()
                .filter(juice -> juice.getCalories() < 400)
                .sorted(Comparator.comparing(Juice::getCalories))
                .map(Juice::getName)
                .collect(toList());
    }

    /**
     * Collection processing using java 8 parallel streams
     *
     * @param juices list to transform
     * @return transformed list
     */
    public static List<String> getLowCaloriesJuicesNameUsingParallelStreams(List<Juice> juices) {
        return juices.parallelStream()
                .filter(juice -> juice.getCalories() < 400)
                .sorted(Comparator.comparing(Juice::getCalories))
                .map(Juice::getName)
                .collect(toList());
    }

    /**
     * Split juices by type
     *
     * @param juices list to process
     * @return grouped map by type.
     */
    public static Map<Juice.Type, List<Juice>> splitJuicesByType(List<Juice> juices) {
        return juices.stream()
                .collect(Collectors.groupingBy(Juice::getType));
    }


    /**
     * FlatMap
     * This operator let you return an stream as a result of a function,
     * so the signature of this operator is Function<T, Stream<R>>
     * and you can apply any operation to this inner stream
     */
    public static List<String> findUniqueCharacters(List<String> words) {
        return words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    /**
     * Reduce
     * this operator let you combine all the elements of the stream
     * this give you the ability to answer more complicated queries
     * like: what is the total sum of calories of juices
     * or, what is the total cost of the order
     */
    public static BigDecimal totalOfOrder(List<Product> orderProducts) {
        return orderProducts.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, (acc, current) -> acc.add(current));
    }



}
