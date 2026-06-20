package homework_11.movie_service;

import homework_11.grade_service.InvalidGradeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService<T extends Number> {

    Map<Movie, List<Rating<T>>> ratings = new HashMap<>();

    public synchronized void addRating(Movie movie, Rating<T> rating) {


        if (rating == null) {
            throw new IllegalArgumentException("Рейтинг не может быть null");
        }
        double val = rating.getValue().doubleValue();
        if (val < 1 || val > 10) {
            throw new IllegalArgumentException("Оценка должна быть от 1 до 10");
        }
        ratings.computeIfAbsent(movie, movie1 -> new ArrayList<>()).add(rating);
    }

    public double getAvgRating(Movie movie) {
        List<Rating<T>> list = ratings.get(movie);
        if (list == null || list.isEmpty()){
            throw new IllegalArgumentException("Такого фильма нет");
        }
        return list.stream().mapToDouble(r -> r.getValue().doubleValue()).average().orElse(0.0);
    }

    public List<Movie> getSortedMoviesByRating() {
        return ratings.entrySet().stream().filter(e -> !e.getValue().isEmpty())
                .sorted((e1, e2) -> Double.compare(average(e2.getValue())
                        , average(e1.getValue()))).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private double average(List<Rating<T>> ratings) {
        return ratings.stream().mapToDouble((r -> r.getValue().doubleValue()))
                .average().orElse(0.0);
    }
}