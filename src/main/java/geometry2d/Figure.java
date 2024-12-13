package geometry2d;

public interface Figure {
    String name = "";

    double area();
    String toString();

    default void printInfo() {
        System.out.println("I am an interface");
    }
}

abstract class FigureNextGen {
    public void printInfo() {

    }

    abstract String getName();
}