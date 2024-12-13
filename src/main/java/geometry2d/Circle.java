package geometry2d;

import Exceptions.SizeException;

public class Circle implements Figure {
    private final double radius;

    public Circle(int radius) throws SizeException {
        if (radius <= 0) {
            throw new SizeException("Radius can't be less or equals 0");
        }
        this.radius = radius;
    }

    public double area() {
        return radius * radius * Math.PI;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Круг\n");
        builder.append("Радиус: ").append(this.radius).append("\n");
        builder.append("Площадь: ").append(this.area());
        return builder.toString();
    }
}
