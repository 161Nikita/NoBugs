package homework_mock;


import java.util.ArrayList;
import java.util.List;

public class Merge {

    public static List<String> merge(List<String> a, List<String> b) {

        List<String> arrayList = new ArrayList<>(a);

        arrayList.addAll(b);

        return arrayList;
    }

    public static void main(String[] args) {

        String[] a = {"Hello", "Privet", "Nikita", "Poka"};
        String[] b = {};

        System.out.println(merge(List.of(a), List.of(b)));
    }
}
