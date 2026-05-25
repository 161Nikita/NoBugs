package homework_1;

public class Reactangle {

    int width;
    int height;

    Reactangle(int someWidth, int someHeight) {
        this.height = someHeight;
        this.width = someWidth;
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    void setWidth(int newWidth) {
        this.width = newWidth;
    }

    int calculateArea() {
        return width * height;
    }
}