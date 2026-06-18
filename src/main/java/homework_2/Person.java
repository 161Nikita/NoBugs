package homework_2;

public class Person {
    private String firstName;
    private String lastName;
    private final String SSN;

    Person(String someFirstName, String someLastName, String someSSN) {
        this.firstName = someFirstName;
        this.lastName = someLastName;
        this.SSN = someSSN;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    String getSSN() {
        return this.SSN;
    }

    void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    void printPersonInfo() {
        System.out.println("Имя: " + this.firstName + ", Фамилия: " + this.lastName + ", SSN: " + SSN);
    }
}