package geometry2d;

import Exceptions.SizeException;

public class Triangle implements Figure {
    private final double b;
    private final double h;

    public Triangle(double b, double h) throws SizeException {
        if (b <= 0 || h <= 0) {
            throw new SizeException("Base and height can't be less or equals 0");
        }
        this.b = b;
        this.h = h;
    }

    public double area() {
        return (b * h) / 2;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Круг\n");
        builder.append("Основание: ").append(this.b).append("\n");
        builder.append("Высота: ").append(this.h).append("\n");
        builder.append("Площадь: ").append(this.area());
        return builder.toString();
    }
}
