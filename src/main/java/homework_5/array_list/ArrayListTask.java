package homework_5.array_list;

import java.util.ArrayList;

public class ArrayListTask {

    private ArrayList<Integer> arrayList;

    public ArrayListTask() {
        this.arrayList = new ArrayList<>(5);
    }

    public void addElements(int element) {
        arrayList.add(element);
    }

    public void printElements() {
        arrayList.forEach(element -> {if(element % 2 == 0) {
            System.out.print(element + " ");
        }});
    }
}
