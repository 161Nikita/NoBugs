package practice_2;

import practice_3.Test1;

public class Main {
    public static void main(String[] args) {
        //Проверка конструктора по умолчанию
        Student petya = new Student(18, "Петя");

        petya.print();

        Student kolya = new Student(20, "Коля");

        kolya.print();

        petya.setName("Антон");
       petya.print();
        kolya.setAge(21);
        kolya.print();

    }
}
