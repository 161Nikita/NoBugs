package homework_14.complex_task.library_service;

public class Main {
    public static void main(String[] args) {
        BookBuilder builder = new BookBuilder();
        Book book = builder.setTitle("War and Peace")
                .setAuthor("Leo Tolstoy")
                .setDescription("Napoleonic Wars")
                .build();

        BookProxy proxy = new BookProxy(book);

        String content = proxy.getContent();
        System.out.println("Контент " + content);
    }
}
