package homework_5.tree_set;

public class Main {

    public static void main(String[] args) {

    TreeSetTask treeSetTask = new TreeSetTask();

    treeSetTask.addElement(1);
    treeSetTask.addElement(3);
    treeSetTask.addElement(34);
    treeSetTask.addElement(434);
    treeSetTask.addElement(11);

    treeSetTask.printElemets();

    treeSetTask.searchMaxAndMinNum(3);

    }


}
