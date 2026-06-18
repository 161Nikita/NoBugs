package homework_2;

public class Library {

    private String bookTitle = "Гарри Поттер";
    protected String author;
    int year;
    public String category;

    String getBookTitle() {
        return this.bookTitle;
    }

    String getAuthor() {
        return this.author;
    }

    int getYear() {
        return this.year;
    }

    String getCategory() {
        return this.category;
    }

    void setBookTitle(String newBookTitle) {
        this.bookTitle = newBookTitle;
    }

    void setAuthor(String newAutore) {
        this.author = newAutore;
    }

    void setYear(int newYear) {
        this.year = newYear;
    }

    void setCategory(String newCategory) {
        this.category = newCategory;
    }
}