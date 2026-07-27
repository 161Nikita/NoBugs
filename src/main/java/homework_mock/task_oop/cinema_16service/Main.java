package homework_mock.task_oop.cinema_16service;

/*
"В системе есть сеанс.

У сеанса есть:
название фильма
общее количество мест
список занятых мест

Нужно реализовать систему, которая может:
забронировать место
отменить бронирование
проверить, занято ли место
показать количество свободных мест

Правила:
одно место можно забронировать только один раз
если место уже занято, бронирование невозможно
при отмене бронирования место снова становится свободным

Пример:
Бронируем места:
1
2
3

Свободных мест:
7

Отменяем бронирование места:
2

Свободных мест:
8"
 */

public class Main {
    public static void main(String[] args) {
        CinemaSession cinemaSession = new CinemaSession("Leon King", 10);
        System.out.println("Бронируем места:");
        cinemaSession.addReservedSeats(1);
        cinemaSession.addReservedSeats(2);
        cinemaSession.addReservedSeats(3);
        cinemaSession.getFreeSeats();
        cinemaSession.cancelReservedSeats(2);
        cinemaSession.getFreeSeats();
    }
}