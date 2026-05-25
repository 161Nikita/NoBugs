package practice_3;

public class Main {
    public static void main(String[] args) {
        // создали переменную
        // присвоили переменной экземпляр класса Студент
        // вызвав дефолтный конструктор
        System.out.println(Student.studentCount);
        Student petya = new Student(18, "Петя");
        System.out.println(Student.studentCount);
        Student kolya = new Student(20, "Коля");
        System.out.println(Student.studentCount);


    }
}
