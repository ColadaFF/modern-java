package co.com.ias.deved.workshop.step03;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

// Comment the following line to test your solution
import static co.com.ias.deved.workshop.step03.StreamsExercisesSolution.*;

// Uncomment the following line to test your solution
//import static co.com.ias.deved.workshop.step03.StreamsExercises.*;

public class StreamsTest {

    @Test
    @DisplayName("Test square numbers list")
    void testSquareNumbers() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actual = squareNumbers(integers);
        List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("Test square divisibleByThree")
    void testDivisibleByThree() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> actual = divisibleByThree(integers);
        List<Integer> expected = Arrays.asList(3, 6);
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("Test PairsOfLists")
    void testPairsOfLists() {
        List<Integer> integers = Arrays.asList(1, 2);
        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Pair<Integer, Integer>> actual = PairsOfLists(integers, integers2);
        List<Pair<Integer, Integer>> expected = Arrays.asList(
                Pair.of(1, 1),
                Pair.of(1, 2),
                Pair.of(1, 3),
                Pair.of(1, 4),
                Pair.of(1, 5),
                Pair.of(2, 1),
                Pair.of(2, 2),
                Pair.of(2, 3),
                Pair.of(2, 4),
                Pair.of(2, 5)
        );
        assertIterableEquals(expected, actual);
    }


    @Test
    @DisplayName("Test PairsOfListsDivisibleByThree")
    void testPairsOfListsDivisibleByThree() {
        List<Integer> integers = Arrays.asList(1, 2);
        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Pair<Integer, Integer>> actual = PairsOfListsDivisibleByThree(integers, integers2);
        List<Pair<Integer, Integer>> expected = Arrays.asList(
                Pair.of(1, 3),
                Pair.of(1, 6),
                Pair.of(2, 3),
                Pair.of(2, 6)
        );
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("Test Maximum")
    void textMax() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer actual = maxOfAListReduce(integers);
        Integer expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test Minimum")
    void testMin() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer actual = minOfAListReduce(integers);
        Integer expected = 1;
        assertEquals(expected, actual);
    }

}
