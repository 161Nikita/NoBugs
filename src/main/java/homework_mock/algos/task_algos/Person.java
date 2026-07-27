package homework_mock.algos.task_algos;

/**
 * Реализация класса с геттерами и сеттерами
 * Создать класс Person с полями name и age, геттерами/сеттерами и методом print()
 */

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null");
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
            throw new IllegalArgumentException("Имя не может быть null");
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
        System.out.println(person1.getName());
        System.out.println(person1.getAge());
        person1.setName("Petr");
        person1.setAge(40);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());


        try {
            Person person2 = new Person(null, 34);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }
        try {
            Person person2 = new Person("Vladimir", -3);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

    }

}
