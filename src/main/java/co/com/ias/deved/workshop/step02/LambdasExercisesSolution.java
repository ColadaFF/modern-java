package co.com.ias.deved.workshop.step02;

import java.util.Objects;

public class LambdasExercisesSolution {

    /**
     * Implement a function that receives two values
     * @param <T> Input parameter #1
     * @param <R> Input parameter #2
     * @param <U> Output parameter
     */
    // remove next comment to implement
    // @FunctionalInterface
    interface BiFunction<T, R, U> {
        U apply(T t, R r);
    }

    /**
     * Implement a consumer that receives two values
     * @param <T> Input parameter #1
     * @param <R> Input parameter #2
     */
    // remove next comment to implement
    // @FunctionalInterface
    interface BiConsumer<T, R> {
        void accept(T t, R r);
    }

    /**
     * Implement a predicate that receives two values
     * @param <T> Input parameter #1
     * @param <R> Input parameter #2
     */
    // remove next comment to implement
    // @FunctionalInterface
    interface BiPredicate<T, R> {
        Boolean test(T t, R r);
    }

    /**
     * Implement compose method to the function
     */
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);

        default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }

        default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.apply(v));
        }
    }


}
