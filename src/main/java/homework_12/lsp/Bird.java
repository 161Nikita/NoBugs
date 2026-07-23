package homework_12.lsp;

/*
Нарушение LSP (Liskov Substitution Principle) – некорректное поведение подклассов
Задача: Перепроектируйте код так, чтобы классы-наследники не нарушали поведение базового класса.
 */

public class Bird {
    public void eat() {
        System.out.println("Птица ест");
    }
}
