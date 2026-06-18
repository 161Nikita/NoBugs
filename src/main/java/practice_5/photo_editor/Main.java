package practice_5.photo_editor;

public class Main {
    public static void main(String[] args) {
        PhotoEditor photoEditor = new PhotoEditor();

        photoEditor.addNewActions("Добавили действие корректировка ушей");
        photoEditor.addNewActions("Добавили действие корректировка глаз");
        photoEditor.addNewActions("Добавили действие корректировка тела");
        photoEditor.printAction();

        photoEditor.undoLastAction();
        photoEditor.printAction();
    }
}
