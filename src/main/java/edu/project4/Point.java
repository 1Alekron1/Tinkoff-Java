package edu.project4;

public record Point(double x, double y) {
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
