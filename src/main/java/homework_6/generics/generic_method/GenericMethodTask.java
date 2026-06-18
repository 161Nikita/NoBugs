package homework_6.generics.generic_method;

public class GenericMethodTask {

    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
}
