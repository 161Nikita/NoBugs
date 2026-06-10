package practice_7_exception.generics;

public class Box<T> {
    private T element;

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return this.element;
    }
}
