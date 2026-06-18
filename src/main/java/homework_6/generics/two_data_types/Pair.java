package homework_6.generics.two_data_types;

public class Pair <T, U> {

    private T first;
    private U second;

    public void setFirst(T item) {
        this.first = item;
    }
    public T getFirst() {
        return this.first;
    }

    public void setSecond(U item) {
        this.second = item;
    }
    public U getSecond() {
        return this.second;
    }
}
