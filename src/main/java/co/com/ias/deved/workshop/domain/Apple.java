package co.com.ias.deved.workshop.domain;

public class Apple {
    private final int weight;
    private final String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }


    public String getColor() {
        return color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
        return String.format("Apple{color='%s', weight=%d}", color, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apple)) return false;
        Apple apple = (Apple) o;
        return getWeight() == apple.getWeight() &&
                com.google.common.base.Objects.equal(getColor(), apple.getColor());
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getWeight(), getColor());
    }
}
