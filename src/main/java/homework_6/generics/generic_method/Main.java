package homework_6.generics.generic_method;

public class Main {
    public static void main(String[] args) {

        GenericMethodTask genericMethodTask = new GenericMethodTask();

        String[] stringsArrays = {"One", "Two", "Three"};

        genericMethodTask.printArray(stringsArrays);

        Integer[] integersArrays = {1, 2, 3};

        genericMethodTask.printArray(integersArrays);
    }
}
