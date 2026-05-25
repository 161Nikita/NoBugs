package practice_1;

public class Userinfo { // определение класса которое включает в себя модификатор доступа, ключевое слово, имя класса
    private static final String DEFAULT_NAME = "unknown";
    private String name; // объявление поля класса с типом данных String и именем name
    private int age;

    public void printInfo() {
        System.out.println("Имя: " + name); // вызов распечатывания в консоль с новой строки с аргументом "Имя: ", конкатенированное с полем
        System.out.println("Возраст: " + age);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args){ // определение метода main (точка входа)
        Userinfo userinfo = new Userinfo(); // определяем переменную с именем userInfo и типом practice_1.Userinfo,
        // инициализирую переменную с помощью оператора new и конструктора по умолчанию userInfo

    userinfo.setName("Nikita");

    userinfo.setAge(21);

    userinfo.printInfo();
    }
}
