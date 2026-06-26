package homework_11_tests.movie_service_tests;

import homework_11.movie_service.Movie;
import homework_11.movie_service.MovieService;
import homework_11.movie_service.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {
    /**
     * Позитивные кейсы:
     * <p>
     * Добавление рейтинга
     * Подсчет среднего значения рейтинга
     * Сортировка по рейтингу
     * <p>
     * Угловые кейсы:
     * <p>
     * Поставить рейтинг 1 и 10
     * <p>
     * Негативные кейсы:
     * Оценка выше 10
     * Рейтинг несуществующего фильма
     *
     */


    @Test
    @DisplayName("Добавление рейтинга")
    public void testAddRating() {
        MovieService<Integer> service = new MovieService<>();

        Movie movie = new Movie("Брат");

        service.addRating(movie, new Rating<>(5));

        assertEquals(5.0, service.getAvgRating(movie));
    }

    @Test
    @DisplayName("Подсчет среднего значения рейтинга")
    public void testAvgRating() {
        MovieService<Integer> service = new MovieService<>();

        Movie movie = new Movie("Брат");

        service.addRating(movie, new Rating<>(10));
        service.addRating(movie, new Rating<>(1));

        assertEquals(5.5, service.getAvgRating(movie));
    }

    @Test
    @DisplayName("Сортировка по рейтингу")
    public void testSortedByRating() {
        MovieService<Integer> service = new MovieService<>();

        Movie movie1 = new Movie("Брат");
        Movie movie2 = new Movie("Сестра");

        service.addRating(movie1, new Rating<>(10));
        service.addRating(movie2, new Rating<>(1));

        assertEquals(movie1, service.getSortedMoviesByRating().get(0));
        assertEquals(movie2, service.getSortedMoviesByRating().get(1));
    }

    @Test
    @DisplayName("Оценка выше 10")
    public void testInvalidRating() {
        MovieService<Integer> service = new MovieService<>();

        Movie movie = new Movie("Сестра");

        assertThrows(IllegalArgumentException.class, () -> service.addRating(movie, new Rating<>(11)));

    }

    @Test
    @DisplayName("Рейтинг несуществующего фильма")
    public void testInvalidRatingMovie() {
        MovieService<Integer> service = new MovieService<>();

        Movie movie = new Movie("Сестра");

        assertThrows(IllegalArgumentException.class, () -> service.getAvgRating(movie));

    }
}