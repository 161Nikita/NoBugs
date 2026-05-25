package homework_1;

public class Point {

    int x;
    int y;

    Point(int someX, int someY) {
        this.x = someX;
        this.y = someY;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void setX(int newX) {
        this.x = newX;
    }

    void print() {
        System.out.println("Координата X: " + this.x + " Координата Y: " + this.y);
    }
}
