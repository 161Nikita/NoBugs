package homework_mock.task_oop.library_8service;

public class Book {
    private String titleBook;
    private String author;
    private StatusBook statusBook;

    public Book(String titleBook, String author) {
        this.titleBook = titleBook;
        this.author = author;
        this.statusBook = StatusBook.AVAILABLE;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getAuthor() {
        return author;
    }

    public StatusBook getStatusBook() {
        return statusBook;
    }

    public void setStatusBook(StatusBook statusBook) {
        this.statusBook = statusBook;
    }

    @Override
    public String toString() {
        return "\"" + titleBook + "\" — " + author;
    }
}
