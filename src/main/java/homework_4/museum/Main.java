package homework_4.museum;

public class Main {
    public static void main(String[] args) {

        Meseum meseum = new Meseum();

        Exhibit manuscriot = new Manuscriot();
        Exhibit sculpture = new Sculpture();

        meseum.setExhibit(manuscriot);
        meseum.showExhibit();

        meseum.setExhibit(sculpture);
        meseum.showExhibit();
    }
}
