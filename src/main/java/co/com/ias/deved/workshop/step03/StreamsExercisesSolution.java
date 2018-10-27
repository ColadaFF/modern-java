package co.com.ias.deved.workshop.step03;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsExercisesSolution {
    /**
     * Return the square of a list of numbers
     */
    public static List<Integer> squareNumbers(List<Integer> list) {
        return list.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());
    }

    /**
     * Return the list of numbers divisible by 3
     */
    public static List<Integer> divisibleByThree(List<Integer> list) {
        return list.stream()
                .filter(integer -> integer % 3 == 0)
                .collect(Collectors.toList());
    }

    /**
     * Return the list of pairs of numbers of two different lists
     */
    public static List<Pair<Integer, Integer>> PairsOfLists(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .flatMap(integer -> list2.stream()
                        .map(integer2 -> Pair.of(integer, integer2))
                )
                .collect(Collectors.toList());
    }

    /**
     * Return the list of pairs of numbers
     * of two different collections that the multiplication of the pair is divisible by three
     */
    public static List<Pair<Integer, Integer>> PairsOfListsDivisibleByThree(List<Integer> list, List<Integer> list2) {
        return list.stream()
                .flatMap(integer -> list2.stream()
                        .map(integer1 -> Pair.of(integer, integer1))
                        .filter(pair -> pair.getLeft() * pair.getRight() % 3 == 0)
                )
                .collect(Collectors.toList());
    }

    /**
     * Find the maximum of a list of numbers using reduce
     */
    public static Integer maxOfAListReduce(List<Integer> list) {
        return list.stream()
                .reduce(list.get(0), (currentMax, currentItem) -> {
                    if (currentItem > currentMax) {
                        return currentItem;
                    }
                    return currentMax;
                });
    }

    /**
     * Find the minimum of a list of numbers using reduce
     */
    public static Integer minOfAListReduce(List<Integer> list) {
        return list.stream()
                .reduce(list.get(0), (currentMin, currentItem) -> {
                    if (currentItem < currentMin) {
                        return currentItem;
                    }
                    return currentMin;
                });
    }

}
