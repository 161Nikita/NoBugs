package homework_mock.algos.repeat;

/**
 * Создать класс Person с полями name и age, геттерами/сеттерами и методом print().
 * <p>
 * person1 = Nikita, 34
 * person2 = null, 34 - IllegalArgumentException
 * person2 = Nikita, -4 - IllegalArgumentException
 *
 */

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new IllegalArgumentException("В качестве имени не может быть null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше 0");
        }
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String newName) {
        if (newName == null) {
            throw new IllegalArgumentException("В качестве имени не может быть null");
        }
        this.name = newName;
    }

    public void setAge(int newAge) {
        if (newAge < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше 0");
        }
        this.age = newAge;
    }

    public void print() {
        System.out.println("Имя: " + name + " Возраст: " + age);
    }

    public static void main(String[] args) {

        Person person1 = new Person("Nikita", 34);
        person1.print();

        try {
            Person person2 = new Person(null, 34);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

        try {
            Person person3 = new Person("Nikita", -4);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

    }
}