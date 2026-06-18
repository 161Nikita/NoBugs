package homework_5.tree_set;

import java.util.TreeSet;

public class TreeSetTask {

    private TreeSet<Integer> treeSet;

    public TreeSetTask(){
        this.treeSet = new TreeSet<>();
    }

    public void addElement(int integer) {
        treeSet.add(integer);
    }

    public void printElemets() {
        treeSet.forEach(System.out::println);
    }

    public void searchMaxAndMinNum(int num) {
        System.out.println("Большее число: " + treeSet.higher(num) + " Меньшее число: " + treeSet.lower(num));
    }
}
