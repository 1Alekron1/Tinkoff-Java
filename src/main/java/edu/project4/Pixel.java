package edu.project4;

public record Pixel(int r, int g, int b, int hitCount) {

    public double getR() {
        return this.r;
    }

    public double getG() {
        return this.g;
    }

    public double getB() {
        return this.b;
    }

    public double getCount() {
        return this.hitCount;
    }

    public Pixel mixColor(Pixel color) {
        int mixedR = (this.r + (int) color.getR()) / 2;
        int mixedG = (this.g + (int) color.getG()) / 2;
        int mixedB = (this.b + (int) color.getB()) / 2;
        return new Pixel(mixedR, mixedG, mixedB, this.hitCount);
    }

    // Добавляем метод для увеличения счетчика попаданий
    public Pixel incrementHitCount() {
        return new Pixel(this.r, this.g, this.b, this.hitCount + 1);
    }
}
