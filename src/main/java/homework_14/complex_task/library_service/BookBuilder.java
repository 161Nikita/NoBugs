package homework_14.complex_task.library_service;

public class BookBuilder {
    private  String title;
    private  String author;
    private  String description;
    private String content;

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public Book build() {
        if(this.content == null) {
            this.content = "текст книги " + this.title;
        }
        return new Book(title, author, description, content);
    }
}
