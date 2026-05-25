package homework_2;

public class LibraryTest {

    public static void main(String[] args) {
        Library library = new Library();

        System.out.println(library.category);
        System.out.println(library.year);
        System.out.println(library.author);
        /*System.out.println(library.bookTitle);*/ // потому что private

        System.out.println(library.getBookTitle());
        library.year = 3;
        System.out.println(library.year);
        library.setBookTitle("Властелин колец");
        System.out.println(library.getBookTitle());

    }





}
