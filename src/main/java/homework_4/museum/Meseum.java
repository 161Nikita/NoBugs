package homework_4.museum;

public class Meseum {
    private Exhibit exhibit;

    public void setExhibit(Exhibit e) {
        this.exhibit = e;
    }

    public void showExhibit() {
        System.out.println("Описание: " + this.exhibit.describe());
        this.exhibit.preserve();
    }
}
