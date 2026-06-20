package homework_10;

public class DebugTask5 {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);
        person = updateAge(person);
        System.out.println("Updated age: " + person.getAge());
    }
    public static Person updateAge(Person person) {
       return new Person(person.getName(), person.getAge() + 1);
    }
}
class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
}