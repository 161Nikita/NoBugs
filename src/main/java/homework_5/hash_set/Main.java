package homework_5.hash_set;

public class Main {
    public static void main(String[] args) {

        /*HashSetTask hashSetTask = new HashSetTask();

        hashSetTask.addList("1");
        hashSetTask.addList("2");
        hashSetTask.addList("3");
        hashSetTask.addList("4");
        hashSetTask.addList("4");
        hashSetTask.addList("4");

        hashSetTask.printList();

        hashSetTask.noDublicates(hashSetTask.getList()).forEach(System.out::println);*/

        HashSetTaskTwo hashSetTaskTwo = new HashSetTaskTwo();

        hashSetTaskTwo.addNames("Nikita");
        hashSetTaskTwo.addNames("Stepan");
        hashSetTaskTwo.addNames("Petr");

        hashSetTaskTwo.printNames();

        hashSetTaskTwo.checkDublicatesNames("Nikita");
        hashSetTaskTwo.checkDublicatesNames("Игорь");

    }
}
