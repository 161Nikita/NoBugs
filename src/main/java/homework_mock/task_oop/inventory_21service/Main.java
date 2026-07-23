package homework_mock.task_oop.inventory_21service;

/*
"В системе есть инвентарь.
Инвентарь хранит предметы игрока.
У каждого предмета есть:
название
количество

Нужно реализовать систему, которая может:
добавить предмет в инвентарь
использовать предмет
узнать количество предмета
проверить, есть ли предмет в инвентаре
показать количество разных предметов

Правила:
если предмет уже есть, при добавлении количество увеличивается
использовать можно только существующий предмет
при использовании количество уменьшается на 1
если количество стало 0, предмет удаляется из инвентаря
название предмета уникально

Пример:
Добавляем предметы:
Potion → 3
Arrow → 10
Potion → 2

Количество:
Potion → 5
Arrow → 10
Используем:
Potion
Количество:

Potion → 4"
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Добавляем предметы:");
        Inventory inventory = new Inventory();
        inventory.addItem("Potion", 3);
        System.out.println("Potion → 3");
        inventory.addItem("Arrow", 10);
        System.out.println("Arrow → 10");
        inventory.addItem("Potion", 2);
        System.out.println("Potion → 2");
        System.out.println("Количество:");
        System.out.println("Potion → " + inventory.countItem("Potion"));
        System.out.println("Arrow → " + inventory.countItem("Arrow"));
        System.out.println("Используем:\n" + "Potion");
        inventory.useItem("Potion");
        System.out.println("Количество:");
        System.out.println("Potion → " + inventory.countItem("Potion"));
    }
}