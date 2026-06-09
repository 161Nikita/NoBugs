package homework_7.part_1_fuction_interface.anonymous_class;

public class AnonymousClass {
    public static void main(String[] args) {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous class!");
            }
        };
        r1.run();

        // Короткая запись, как пишут сейчас
        /*Runnable r2 = () -> System.out.println("Hello from anonymous class!");

        r2.run();*/
    }
}
