package co.com.ias.deved.workshop.domain;

public class Cake {

    public enum Type {
        FIT, FAT, RELAX
    }

    private final String name;
    private final int calories;
    private final Type type;

    public Cake(String name, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
