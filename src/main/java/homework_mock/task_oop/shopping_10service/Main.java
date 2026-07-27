package homework_mock.task_oop.shopping_10service;

/*
"В системе есть корзина.  ShoppingCart

У корзины есть:
список товаров List<Item>
У каждого товара в корзине есть: Item

название
цена
количество

Нужно реализовать систему, которая может:
добавить товар в корзину
удалить товар из корзины
изменить количество товара
посчитать общую стоимость корзины
показать содержимое корзины

Правила:
если количество стало 0, товар удаляется из корзины
общая стоимость корзины считается как сумма цена × количество для всех товаров

Пример:
В корзине:

Телефон — 500 × 1
Чехол — 20 × 2
Зарядка — 30 × 1

Общая стоимость:
570"
 */

public class Main {


    public static void main(String[] args) {
        Item phone = new Item("Телефон", 500, 1);
        Item keys = new Item("Чехол", 20, 2);
        Item powerBank = new Item("Зарядка", 30, 1);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItemInShoppingCart(phone);
        shoppingCart.addItemInShoppingCart(keys);
        shoppingCart.addItemInShoppingCart(powerBank);
        shoppingCart.printShoppingCart();
        shoppingCart.removeItemInShoppingCart(keys);
        shoppingCart.printShoppingCart();
        shoppingCart.changeQuantity(phone, 10);
        shoppingCart.total();
        shoppingCart.printShoppingCart();
    }
}