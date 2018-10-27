package co.com.ias.deved.workshop.step05;

import co.com.ias.deved.workshop.util.CheckedFunction;
import co.com.ias.deved.workshop.util.CheckedPredicate;
import co.com.ias.deved.workshop.util.NonFatalException;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public abstract class TrySolution<T> {
    // sealed
    private TrySolution() {
    }

    public static <T> TrySolution<T> of(Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "callable is null");
        try {
            return success(callable.call());
        } catch (Throwable t) {
            return failure(t);
        }
    }

    public abstract Throwable getCause() throws UnsupportedOperationException;

    public abstract T get() throws NonFatalException;

    public static <T> TrySolution<T> success(T value) {
        return new Success<>(value);
    }

    public static <T> TrySolution<T> failure(Throwable cause) {
        return new Failure<>(cause);
    }

    public abstract boolean isFailure();

    public abstract boolean isSuccess();

    @SuppressWarnings("unchecked")
    public <U> TrySolution<U> map(CheckedFunction<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (isSuccess()) {
            try {
                return success(mapper.apply(get()));
            } catch (Throwable t) {
                return failure(t);
            }
        } else {
            return (TrySolution<U>) this;
        }
    }

    public TrySolution<T> mapFailure(CheckedFunction<? super Throwable, ? extends Throwable> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (isFailure()) {
            try {
                return failure(mapper.apply(getCause()));
            } catch (Throwable t) {
                return failure(t);
            }
        } else {
            return this;
        }
    }

    public TrySolution<T> onFailure(Consumer<? super Throwable> action) {
        Objects.requireNonNull(action, "action is null");
        if (isFailure()) {
            action.accept(getCause());
        }
        return this;
    }

    public TrySolution<T> onSuccess(Consumer<? super T> action) {
        Objects.requireNonNull(action, "action is null");
        if (isSuccess()) {
            action.accept(get());
        }
        return this;
    }

    /**
     * Exercise
     *
     * @param predicate value checker
     * @return the new try
     */

    public TrySolution<T> filter(CheckedPredicate<T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        if (isSuccess()) {
            try {
                final T value = get();
                if (!predicate.test(value)) {
                    return failure(new NoSuchElementException("Predicate does not hold for " + value));
                }
            } catch (Exception e) {
                return failure(e);
            }
        }
        return this;
    }


    public static class Success<T> extends TrySolution<T> {
        private final T value;

        public Success(T value) {
            Objects.requireNonNull(value);
            this.value = value;
        }

        @Override
        public Throwable getCause() {
            throw new UnsupportedOperationException("getCause() on Success");
        }

        @Override
        public T get() throws NonFatalException {
            return value;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }
    }

    public static class Failure<T> extends TrySolution<T> {

        private final Throwable cause;


        public Failure(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Throwable getCause() {
            throw new UnsupportedOperationException("getCause() on Success");
        }

        @Override
        public T get() throws NonFatalException {
            throw new NonFatalException(cause);
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }
    }

}
