package edu.hw2.task2;

public class Square extends Rectangle {

    private final int dimensionLength;

    public Square(int dimensionLength) {
        super(dimensionLength, dimensionLength);
        this.dimensionLength = dimensionLength;
    }

    public Square() {
        this(0);
    }

    @Override
    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.dimensionLength);
    }

    @Override
    public Rectangle setHeight(int height) {
        return new Rectangle(this.dimensionLength, height);
    }
}
