package co.com.ias.deved.workshop.domain;

public class Juice {
    public enum Type {
        FIT, FAT, RELAX, FRESH
    }

    private final String name;
    private final int calories;
    private final Type type;


    public Juice(String name, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
