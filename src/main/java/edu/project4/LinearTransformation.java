package edu.project4;

import java.util.function.Function;

public class LinearTransformation implements Transformation {
    public Function<Point, Point> func;
    public Pixel color;

    public LinearTransformation(Function<Point, Point> func, Pixel color) {
        this.func = func;
        this.color = color;
    }

    @Override
    public Point apply(Point point) {
        return func.apply(point);
    }

    public Function<Point, Point> getFunc() {
        return this.func;
    }

    public Pixel getColor() {
        return this.color;
    }
}
