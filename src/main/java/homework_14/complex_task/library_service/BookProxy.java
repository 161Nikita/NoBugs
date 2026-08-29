package homework_14.complex_task.library_service;

public class BookProxy implements IBook {
    private final Book realBook;
    private String cachedContent;
    private boolean isLoaded = false;

    public BookProxy(Book book) {
        this.realBook = book;
    }

    @Override
    public String getId() {
        return realBook.getId();
    }

    @Override
    public String getTitle() {
        return realBook.getTitle();
    }

    @Override
    public String getAuthor() {
        return realBook.getAuthor();
    }

    @Override
    public String getDescription() {
        return realBook.getDescription();
    }

    @Override
    public String getContent() {
        if (!isLoaded) {
            System.out.println("[Proxy]: Идет ленивая загрузка");
            this.cachedContent = realBook.getContent();
            this.isLoaded = true;
        } else {
            System.out.println("[Proxy]: возврат из кеша");
        }
        return cachedContent;

    }
}
