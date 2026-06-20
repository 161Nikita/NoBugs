package homework_11.movie_service;

public class Rating<T extends Number> {

    private T value;

    public Rating(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
