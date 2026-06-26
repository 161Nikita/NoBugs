package homework_mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookEquality {

    /*
Реализация контракта equals и hashCode для кастомного класса.
Важно: если equals возвращает true, hashCode обязаны совпадать.
*/

    private final String title;
    private final String author;
    private final ArrayList<String> job;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<String> getJob() {
        return job;
    }

    public BookEquality(String title, String author, ArrayList<String> job) {
        this.title = title;
        this.author = author;
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEquality)) return false;
        BookEquality b = (BookEquality) o;
        return title.equals(b.title) && author.equals(b.author) && job.equals(b.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, job);
    }

    public static void main(String[] args) {

        BookEquality bookEquality1 = new BookEquality("Techo", "Nikita", new ArrayList<String>(List.of("qa", "AQA")));
        BookEquality bookEquality2 = new BookEquality("Techo", "Nikita", new ArrayList<String>(List.of("qa", "AQA")));


        System.out.println(bookEquality1.equals(bookEquality2));
    }
}



