package homework_5.tree_map;

import java.util.TreeMap;

public class TreeMapTask {

    private TreeMap<String, Integer> treeMap;

    TreeMapTask(){
        this.treeMap = new TreeMap<>();
    }

    public void addElement(String name, int score) {
        treeMap.put(name, score);
    }

    public void printElement(){
        treeMap.forEach((name, score) -> System.out.println("Имя: " + name + " Балл:" + score));
    }
}
