package practice_5.photo_editor;

import java.util.Stack;

public class PhotoEditor {
    // хранить последние действия
    private Stack<String> actions;

public PhotoEditor() {
    this.actions = new Stack<>();
}
    // добавление действия
    public  void addNewActions(String action) {
    actions.push(action);
    }
    // иметь возможность откатить последнее действие
    public void undoLastAction() {
    actions.pop();
    }

    public void printAction() {
        System.out.println("Все действия: ");
        actions.forEach(System.out::println);
    }
}
