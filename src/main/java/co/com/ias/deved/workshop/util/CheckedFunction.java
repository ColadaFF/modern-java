package co.com.ias.deved.workshop.util;

@FunctionalInterface
public interface CheckedFunction<T, R> {

    R apply(T t) throws Exception;
}
