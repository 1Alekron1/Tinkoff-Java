package edu.project4;

import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {

    Function<Point, Point> FUNC = null;
    Pixel COLOR = null;

    default Function<Point, Point> getFunc() {
        return this.FUNC;
    }

    default Pixel getColor() {
        return this.COLOR;
    }
}
