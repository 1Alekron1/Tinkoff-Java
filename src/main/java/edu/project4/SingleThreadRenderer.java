package edu.project4;

import java.util.List;
import java.util.Random;

public class SingleThreadRenderer implements Renderer {
    public static final int STEP = -20;
    public static final double MIN_X = -1.777;
    public static final double MAX_X = 1.777;
    public static final int MIN_Y = -1;
    public static final int MAX_Y = 1;
    private Random random;
    private final int symmetry; // Добавляем symmetry

    public SingleThreadRenderer(long seed, int symmetry) {
        this.random = new Random(seed);
        this.symmetry = symmetry;
    }

    private Point random(Rect rect) {
        double x = rect.getX() + random.nextDouble() * rect.getWidth();
        double y = rect.getY() + random.nextDouble() * rect.getHeight();
        return new Point(x, y);
    }

    private int random(List<Transformation> variations) {
        var temp = random.nextInt(variations.size());
        return temp;
    }

    private Point transform(Point p, Transformation variation) {
        return variation.apply(p);
    }

    private Point rotate(Point p, double theta) {
        double x = p.getX() * Math.cos(theta) - p.getY() * Math.sin(theta);
        double y = p.getX() * Math.sin(theta) + p.getY() * Math.cos(theta);
        return new Point(x, y);
    }

    private Pixel mapRange(Rect world, Point p, FractalImage canvas) {
        int x = calculateXCoordinate(p, world, (int) canvas.getWidth());
        int y = calculateYCoordinate(p, world, (int) canvas.getHeight());

        if (canvas.contains(x, y)) {
            return canvas.pixel(x, y);
        }

        return null;
    }

    private int calculateXCoordinate(Point p, Rect world, int canvasWidth) {
        return (int) ((p.getX() - world.getX()) / world.getWidth() * canvasWidth);
    }

    private int calculateYCoordinate(Point p, Rect world, int canvasHeight) {
        return (int) ((p.getY() - world.getY()) / world.getHeight() * canvasHeight);
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed, List<Transformation> linear
    ) {
        for (int num = 0; num < samples; ++num) {
            Point pw = random(world);

            for (short step = STEP; step < iterPerSample; ++step) {
                int variation = random(variations);
                int line = random(linear);

                pw = transform(pw, linear.get(line));
                pw = transform(pw, variations.get(variation));

                if (step > 0) {

                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        var pwr = rotate(pw, theta2);
                        if (!(pwr.getX() >= MIN_X && pwr.getX() <= MAX_X && pwr.getY() >= MIN_Y
                            && pwr.getY() <= MAX_Y)) {
                            continue;
                        }

                        Pixel pixel = mapRange(world, pwr, canvas);

                        int x = Math.min(
                            calculateXCoordinate(pwr, world, (int) canvas.getWidth()),
                            (int) canvas.getWidth() - 1
                        );
                        int y = Math.min(
                            calculateYCoordinate(pwr, world, (int) canvas.getHeight()),
                            (int) canvas.getHeight() - 1
                        );

                        if (pixel == null) {
                            canvas.setPixel(x, y, linear.get(line).getColor().incrementHitCount());
                        } else {
                            canvas.setPixel(x, y, pixel.mixColor(linear.get(line).getColor()).incrementHitCount());
                        }
                    }
                }
            }
        }

        return canvas;
    }
}
