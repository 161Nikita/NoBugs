package practice_6;

public class Main {
    public static void main(String[] args) {

        Person nikita = new Person("Никита",25);

        // Значение toString по умолчанию
        System.out.println(nikita.toString());

        // Сравнение объектов

        Person nikita2 = new Person("Никита", 25);

        System.out.println(nikita.equals(nikita2));

        // Клонирование

        Person cloneNikita = nikita.clone();
        System.out.println(cloneNikita.toString());
        System.out.println(cloneNikita.getClass());

        //
    }
}
