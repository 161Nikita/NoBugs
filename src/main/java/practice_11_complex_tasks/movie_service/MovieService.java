package practice_11_complex_tasks.movie_service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService<T extends Number> {

    private final Map<Movie, List<Rating<T>>> ratings = new HashMap<>();

    public synchronized void addRating(Movie movie, Rating<T> rating) {

        double val = rating.getValue().doubleValue();
        if (val < 1 || val > 10) {
            throw new IllegalArgumentException("Оценка фильма должна быть от 1 до 10");
        }
        ratings.computeIfAbsent(movie, m -> new ArrayList<>()).add(rating);
    }

    public synchronized double getAvgRating(Movie movie) {
        List<Rating<T>> movieRatings = ratings.get(movie);

        if (movieRatings == null || movieRatings.isEmpty()) {
            throw new IllegalArgumentException("Нет оценок для фильма");
        }
        return movieRatings.stream().mapToDouble(r -> r.getValue().doubleValue()).average().orElse(0.0);
    }

    public List<Movie> getSortedMoviesByRating() {
        return ratings.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .sorted((e1, e2) -> Double.compare(average(e2.getValue()), average(e1.getValue()))).
                map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private double average(List<Rating<T>> ratings) {
        return ratings.stream().mapToDouble(r -> r.getValue().doubleValue()).average().orElse(0.0);

    }
}