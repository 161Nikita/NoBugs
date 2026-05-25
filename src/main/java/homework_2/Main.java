package homework_2;

public class Main {
    public static void main(String[] args) {

        Person person1 = new Person("Иван", "Иванов", "123-45-6789");
        person1.printPersonInfo();
        Person person2 = new Person("Никита", "Никитин", "123-45-2222");
        person2.printPersonInfo();
        person2.setFirstName("Егор");
        person2.printPersonInfo();
        System.out.println(person1.getLastName());
        System.out.println(person1.getFirstName());
        System.out.println(person1.getSSN());
        System.out.println(person2.getFirstName());
        System.out.println(person2.getLastName());
        System.out.println(person2.getSSN());

    }
}
