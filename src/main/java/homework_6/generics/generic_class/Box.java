package homework_6.generics.generic_class;

public class Box <T> {

    private T item;

    public T get() {
        return this.item;
    }

    public void set(T item) {
        this.item = item;
    }
}
