package homework_mock.task_oop.warehouse_19service;

/*
"В системе есть склад.

Склад хранит товары.
У каждого товара есть:
название
количество на складе

Нужно реализовать систему, которая может:
добавить товар на склад
увеличить количество существующего товара
уменьшить количество товара
узнать остаток по названию
проверить, есть ли товар на складе
показать количество разных товаров

Правила:
если товар уже есть, при добавлении количество увеличивается
нельзя уменьшить количество больше, чем есть на складе
если количество товара стало 0, его можно удалить со склада
название товара уникально

Пример:
Добавляем товары:
Apple → 10
Banana → 5
Apple → 3

Остатки:
Apple → 13
Banana → 5

Списываем:
Banana → 2

Остатки:
Banana → 3"
 */

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        System.out.println("Добавляем товары:");
        warehouse.addProduct("Apple", 10);
        System.out.println("Apple → 10");

        warehouse.addProduct("Banana", 5);
        System.out.println("Banana → 5");

        warehouse.addProduct("Apple", 3);
        System.out.println("Apple → 3");

        System.out.println("\nОстатки:");
        System.out.println("Apple → " + warehouse.getQuantityProduct("Apple"));
        System.out.println("Banana → " + warehouse.getQuantityProduct("Banana"));

        System.out.println("\nСписываем:");
        warehouse.minusProduct("Banana", 2);
        System.out.println("Banana → 2");

        System.out.println("\nОстатки:");
        System.out.println("Banana → " + warehouse.getQuantityProduct("Banana"));
    }
    }
