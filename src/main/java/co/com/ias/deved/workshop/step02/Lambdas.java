package co.com.ias.deved.workshop.step02;

import co.com.ias.deved.workshop.domain.Apple;

import java.util.Comparator;

public class Lambdas {

    Comparator<Apple> byWeight = new Comparator<Apple>() {
        public int compare(Apple a1, Apple a2) {
            return Integer.compare(a1.getWeight(), a2.getWeight());
        }
    };
}
