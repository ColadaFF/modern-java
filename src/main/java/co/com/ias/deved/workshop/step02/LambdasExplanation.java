package co.com.ias.deved.workshop.step02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static co.com.ias.deved.workshop.util.FileUtils.readFileFromResources;

public class LambdasExplanation {

    public static String processFile() throws IOException, URISyntaxException {
        File file = readFileFromResources("data.txt");
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            return br.readLine();
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException, URISyntaxException {

        File file = readFileFromResources("data.txt");
        FileReader in = new FileReader(file);
        try (BufferedReader br = new BufferedReader(in)) {
            return p.process(br);
        }
    }

    /**
     * Predicate using a Functional interface.
     *
     * @param <T> any
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }


    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static List<String> notEmptyStringsList(List<String> listOfStrings) {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        return filter(listOfStrings, nonEmptyStringPredicate);
    }

    /**
     * Consumer
     */

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T> void printValues(List<T> list) {
        Consumer<T> printer = t -> System.out.println(t);
        forEach(list, printer);
    }

    /**
     * Function
     */
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);

        default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mappingFunction) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            R mappingResult = mappingFunction.apply(t);
            result.add(mappingResult);
        }
        return result;
    }

    public static <T, R, U> List<U> doubleMap(List<T> list, Function<T, R> mappingFunction, Function<R, U> secondMappingFunction) {
        List<U> result = new ArrayList<>();
        for (T t : list) {
            U mappingResult = mappingFunction.andThen(secondMappingFunction).apply(t);
            result.add(mappingResult);
        }
        return result;
    }

    public static List<Integer> listOfLengths(List<String> list) {
        Function<String, Integer> mapToLength = s -> s.length();
        return map(list, mapToLength);
    }

    public static List<Integer> listOfLengthsAndThenSquare(List<String> list) {
        Function<String, Integer> mapToLength = s -> s.length();
        Function<Integer, Integer> square = integer -> integer * integer;
        return doubleMap(list, mapToLength, square);
    }

    /**
     * A word about Boxing ...
     * Exceptions ...
     */

    /**
     * Method references
     */
    public static List<Integer> listOfLengthsReference(List<String> list) {
        Function<String, Integer> mapToLength = String::length;
        return map(list, mapToLength);
    }


}
