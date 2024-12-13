package geometry2d;

public class Rectangle implements Figure {
    private final int a;
    private final int b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double area() {
        return this.a * this.b;
    }

    @Override
    public void printInfo() {
        Figure.super.printInfo();
    }
}
