package homework_6.generics.generic_class;

public class Main {

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        
        stringBox.set("Установка");

        String textBox = stringBox.get();

        System.out.println(textBox);

        Box<Integer> integerBox = new Box<>();

        integerBox.set(123);

        Integer numberBox = integerBox.get();

        System.out.println(numberBox);


    }



}
