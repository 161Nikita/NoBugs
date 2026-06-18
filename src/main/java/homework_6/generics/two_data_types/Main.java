package homework_6.generics.two_data_types;

public class Main {
    public static void main(String[] args) {

        Pair<String, Integer> stringIntegerPair = new Pair<>();

        stringIntegerPair.setFirst("Стоймость ноутбука");
        stringIntegerPair.setSecond(123123123);

        /*String first = stringIntegerPair.getFirst();
        Integer second = stringIntegerPair.getSecond();*/

        System.out.println(stringIntegerPair.getFirst() + " " + stringIntegerPair.getSecond());
    }
}
