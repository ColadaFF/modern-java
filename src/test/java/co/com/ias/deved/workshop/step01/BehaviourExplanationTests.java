package co.com.ias.deved.workshop.step01;

import co.com.ias.deved.workshop.domain.Apple;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static co.com.ias.deved.workshop.step01.BehaviourExplanation.filterApples;
import static co.com.ias.deved.workshop.step01.BehaviourExplanation.filterGreenApples;
import static co.com.ias.deved.workshop.step01.BehaviourExplanation.filterHeavyApples;

public class BehaviourExplanationTests {

    private static List<Apple> inventory;

    @BeforeAll
    static void init() {
        System.out.println("----- Initializing tests -----");
        inventory = new ImmutableList.Builder<Apple>()
                .add(new Apple(80, "green"))
                .add(new Apple(155, "green"))
                .add(new Apple(120, "red"))
                .build();
    }

    @Test
    @DisplayName("Filter using specific parameter: heavy apples")
    void filterHeavyApplesTest() {
        List<Apple> filtered = filterHeavyApples(inventory);
        List<Apple> expected = Collections.singletonList(
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(expected, filtered);
    }

    @Test
    @DisplayName("Filter using specific parameter: green apples")
    void filterGreenApplesTest() {
        List<Apple> filtered = filterGreenApples(inventory);
        List<Apple> expected = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(expected, filtered);
    }


    @Test
    @DisplayName("Filter green apples using lambda function")
    void filterUsingOldLambda() {
        List<Apple> greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        List<Apple> onlyGreenApples = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(onlyGreenApples, greenApples);
    }

    @Test
    @DisplayName("Filter green apples using method reference")
    void filterUsingMethodReference() {
        List<Apple> greenApples = filterApples(inventory, BehaviourExplanation::isGreenApple);
        List<Apple> onlyGreenApples = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(onlyGreenApples, greenApples);
    }

    @Test
    @DisplayName("Filter heavy apples using lambda function")
    void filterHeavyUsingLambda() {
        List<Apple> heavyApples = filterApples(inventory, a -> a.getWeight() > 150);
        List<Apple> onlyHeavyApples = Collections.singletonList(
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(onlyHeavyApples, heavyApples);
    }

    @Test
    @DisplayName("Filter heavy apples using method reference")
    void filterHeavyUsingMethodReference() {
        List<Apple> heavyApples = filterApples(inventory, BehaviourExplanation::isHeavyApple);
        List<Apple> onlyHeavyApples = Collections.singletonList(new Apple(155, "green"));
        Assertions.assertIterableEquals(onlyHeavyApples, heavyApples);
    }

    @Test
    @DisplayName("Filter using predicate")
    void filterUsingPredicate() {
        List<Apple> current = filterApples(inventory, BehaviourExplanation.onlyGreenApple);
        List<Apple> expected = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green")
        );
        Assertions.assertIterableEquals(current, expected);

    }
}
