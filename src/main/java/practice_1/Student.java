package practice_1;

public class Student {

    int age;
    String name;

    /*practice_1.Student petya = new practice_1.Student(); // Дефолтный конструктор*/

    Student(int someAge, String someName) {

        this.age = someAge;
        this.name = someName;
    }

    Student petya = new Student(18, "Nikita");

    int getAge() {
        return this.age;
    }

    String getName() {
        return this.name;
    }

    void setAge(int newAge) {
        this.age = newAge;
    }

    void setName(String newName) {
        this.name = newName;
    }

    int age1 = petya.getAge();

    /*petya.setName("Nikita");*/ // внутри класса нельзя обращаться к методу
}
