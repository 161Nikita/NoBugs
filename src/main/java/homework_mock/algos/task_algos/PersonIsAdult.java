package homework_mock.algos.task_algos;

/**
 * Проверка возраста с использованием объекта. Создать метод isAdult в классе Person, возвращающий true, если возраст >= 18
 */

public class PersonIsAdult {

    private final int age;

    PersonIsAdult(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст человека не может быть отрицательным");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {

        return this.age >= 18;
    }

    public static void main(String[] args) {


        PersonIsAdult person2 = new PersonIsAdult(0);
        PersonIsAdult person3 = new PersonIsAdult(17);
        PersonIsAdult person4 = new PersonIsAdult(18);
        PersonIsAdult person5 = new PersonIsAdult(19);

        System.out.println(person2.isAdult()); // false
        System.out.println(person3.isAdult()); // false
        System.out.println(person4.isAdult()); // true
        System.out.println(person5.isAdult()); // true

        try {
            PersonIsAdult person1 = new PersonIsAdult(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
    }
}