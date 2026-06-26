package practice_11_complex_tasks.movie_service;

public class Rating<T extends Number> {

    private final T value;

    public Rating(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
