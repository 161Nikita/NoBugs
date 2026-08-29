package homework_14.complex_task.library_service;

import java.util.UUID;

public class Book implements IBook {
    private final String id;
    private final String title;
    private final String author;
    private final String description;
    private final String content;

    Book(String title, String author, String description, String content) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getContent() {
        return content;
    }
}
