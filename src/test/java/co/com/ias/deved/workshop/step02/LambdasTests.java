package co.com.ias.deved.workshop.step02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static co.com.ias.deved.workshop.step02.LambdasExercisesSolution.BiConsumer;
import static co.com.ias.deved.workshop.step02.LambdasExercisesSolution.BiFunction;
import static co.com.ias.deved.workshop.step02.LambdasExercisesSolution.BiPredicate;
import static co.com.ias.deved.workshop.step02.LambdasExercisesSolution.Function;

/**
 * Uncomment the following lines to check your solution
 */
//import static co.com.ias.deved.workshop.step02.LambdasExercises.BiConsumer;
//import static co.com.ias.deved.workshop.step02.LambdasExercises.BiFunction;
//import static co.com.ias.deved.workshop.step02.LambdasExercises.BiPredicate;
//import static co.com.ias.deved.workshop.step02.LambdasExercises.Function;

public class LambdasTests {

    @Test
    @DisplayName("Test BiConsumer")
    void testBiConsumer() {
        Integer expected = 10;
        String expectedString = "Empty";
        BiConsumer<Integer, String> actual = (integer, s) -> {
            assertEquals(integer, expected);
            assertEquals(s, expectedString);
        };

        actual.accept(10, "Empty");
    }

    @Test
    @DisplayName("Test BiPredicate")
    void testBiPredicate() {
        Integer expected = 10;
        String expectedString = "Empty";
        BiPredicate<Integer, String> biPredicate = (integer, s) -> integer.equals(expected) && s.equals(expectedString);
        assertEquals(biPredicate.test(10, "Empty"), true);
        assertEquals(biPredicate.test(11, "Empty"), false);
        assertEquals(biPredicate.test(10, ""), false);
    }

    @Test
    @DisplayName("Test BiFunction")
    void testBiFunction() {
        Integer value1 = 10;
        Integer value2 = 10;
        Integer expected = 100;
        BiFunction<Integer, Integer, Integer> biFunction = (integer, integer2) -> integer * integer2;
        assertEquals(biFunction.apply(value1, value2), expected);
    }

    @Test
    @DisplayName("Test Function Compose")
    void testBiFunctionCompose() {
        Integer value1 = 10;
        Integer expected = 49;
        Function<Integer, Integer> functionFirst = integer -> integer - 3;
        Function<Integer, Integer> functionLast = integer -> integer * integer;
        assertEquals(functionLast.compose(functionFirst).apply(value1), expected);
    }


}
