package co.com.ias.deved.workshop.step01;

import co.com.ias.deved.workshop.domain.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BehaviourExplanation {

    /**
     * This is the traditional approach to filter data in java v < 7
     * takes a list and using a specific filter, in this case, green apples
     * @param inventory list of apples
     * @return a filtered list
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * This is the traditional approach to filter data in java v < 7
     * takes a list and using a specific filter, in this case, heavy apples
     * @param inventory list of apples
     * @return a filtered list
     */
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Example of a predicate using the old good java api
     * in this case, checking if the apple is green
     * @param apple element to check
     * @return boolean if the element pass the validation
     */
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    /**
     * Example of a predicate using the old good java api
     * in this case, checking if the apple is heavy
     * @param apple element to check
     * @return boolean if the element pass the validation
     */
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }


    /**
     * Java 8 Predicate
     * checking in this case if the apple is green
     * the format is Function<Apple, Boolean>
     */
    public static Predicate<Apple> onlyGreenApple = apple -> Objects.equals(apple.getColor(), "green");


    /**
     * Filtering example using a predicate
     * @param inventory list to filter
     * @param p the predicate to apply
     * @return filtered list
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
