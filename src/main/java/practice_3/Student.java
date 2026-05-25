package practice_3;

public class Student {
    final static int MAX_YEARS;
    static int studentCount;

    static {
       MAX_YEARS = 11;
        studentCount = 0;
    }

    private int age;
    String name;

    Student(int someAge, String someName) {
        this.age = someAge;
        this.name = someName;
        studentCount++; // инкремент, это увеличения на 1

    }

    private static void printMaxYears() {
        System.out.println(MAX_YEARS);
    }

}
