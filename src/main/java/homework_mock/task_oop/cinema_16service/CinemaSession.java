package homework_mock.task_oop.cinema_16service;

import java.util.HashSet;
import java.util.Set;

public class CinemaSession {

    private final String movieTitle;
    private final int totalSeats;
    private final Set<Integer> reservedSeats = new HashSet<>();

    public CinemaSession(String movieTitle, int totalSeats) {
        this.movieTitle = movieTitle;
        this.totalSeats = totalSeats;
    }

    // забронировать место
    public void addReservedSeats(int seat) {
        if (this.reservedSeats.add(seat)) {
            System.out.println(seat);
        } else {
            System.out.println("Ошибка: место " + seat + " уже занято!");
        }
    }

    // отменить бронирование
    public void cancelReservedSeats(int seat) {
        System.out.println("\nОтменяем бронирование места:");
        if (this.reservedSeats.remove(seat)) {
            System.out.println(seat);
        } else {
            System.out.println("Ошибка: место " + seat + " не было забронировано!");
        }
    }

    // проверить, занято ли место
    public void checkReservedSeats(int seat) {
        if (this.reservedSeats.contains(seat)) {
            System.out.println("Да, место " + seat + " занято");
        } else {
            System.out.println("Нет, место " + seat + " не занято");
        }
    }

    // показать количество свободных мест
    public void getFreeSeats() {
        int freeSeatsCount = totalSeats - reservedSeats.size();
        System.out.println("\nСвободных мест:\n" + freeSeatsCount);
    }
}