package homework_5.linked_list;

public class Main {
    public static void main(String[] args) {

        LinkedListTask linkedListTask = new LinkedListTask();

        linkedListTask.addString("Задача № 1");
        linkedListTask.addString("Задача № 2");
        linkedListTask.addString("Задача № 3");
        linkedListTask.addString("Задача № 4");
        linkedListTask.printString();

        linkedListTask.addFirstTask("СРОЧНАЯ № 2 которая в списке будет стоять первая");
        linkedListTask.addFirstTask("СРОЧНАЯ № 3 которая в списке будет стоять вторая");
        linkedListTask.addFirstTask("СРОЧНАЯ № 4 которая в списке будет стоять третья");

        linkedListTask.printString();
    }
}
