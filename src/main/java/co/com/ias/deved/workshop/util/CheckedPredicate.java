package co.com.ias.deved.workshop.util;

@FunctionalInterface
public interface CheckedPredicate<T> {
    Boolean test(T t) throws Exception;
}
