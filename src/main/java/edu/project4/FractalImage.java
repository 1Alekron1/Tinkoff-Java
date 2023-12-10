package edu.project4;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        return new FractalImage(data, width, height);
    }

    boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    Pixel pixel(int x, int y) {
        return data[x + y * width];
    }

    void setPixel(int x, int y, Pixel pixel) {
        data[x + y * width] = pixel;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Pixel[] getData() {
        return this.data;
    }
}
