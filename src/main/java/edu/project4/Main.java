package edu.project4;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Main {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final int SYMMETRY = 6;
    public static final int SAMPLES = 50000;
    public static final int ITER_PER_SAMPLE = 30;
    public static final int QUATER = 4;
    public static final List<List<Double>> COEFF = Arrays.asList(
        Arrays.asList(0.2, -1.26, 0.223, 0.2422, 0.0, 1.23),
        Arrays.asList(-.15, 0.28, 0.2336, 0.244, 0.0, 0.4455),
        Arrays.asList(0.2, 0.25, 0.223, 0.244, 0.0, 1.23),
        Arrays.asList(-0.15, -1.26, 0.2336, 0.2422, 0.0, 0.4455)
    );
    public static final int BOUND = 256;
    public static final Rect WORLD = new Rect(-1.777, -1, 3.554, 2);
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int INDEX_5 = 5;

    private Main() {
    }

    public static void main(String[] args) {
        // Настройки генерации
        int width = WIDTH;
        int height = HEIGHT;
        int symmetry = SYMMETRY;  // Количество симметрий
        int samples = SAMPLES;
        short iterPerSample = ITER_PER_SAMPLE;
        long seed = System.currentTimeMillis();
        var random = new Random(seed);

        // Создание изображения
        FractalImage fractalImage = FractalImage.create(width, height);

        // Создание списка вариаций (ваш код генерации вариаций)
//        for (short step = 0; step < 20; ++step)
        List<Transformation> variations = List.of(
            // Замените на свои реализации Transformation
            point -> new Point(Math.sin(point.getX()), Math.sin(point.getY())),
            point -> new Point(
                point.getX() * cos(Math.PI / QUATER) - point.getY() * Math.sin(Math.PI / QUATER),
                point.getX() * Math.sin(Math.PI / QUATER) + point.getY() * cos(Math.PI / QUATER)
            ),
            point -> new Point(
                point.getX() / (point.getX() * point.getX() + point.getY() * point.getY()),
                point.getY() / (point.getX() * point.getX() + point.getY() * point.getY())
            ),
            point -> new Point(
                Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()) * Math.sin(
                    Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY())
                        * Math.atan(point.getY() / point.getX())),
                -Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY())
                    *
                    cos(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY())
                        * Math.atan(point.getY() / point.getX()))
            ),
            point -> new Point(
                Math.sin(atan2(point.getY(), point.getX()))
                    * cos(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY())),
                Math.cos(atan2(point.getY(), point.getX()))
                    * sin(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()))
            )
        );

        List<Transformation> linear = new ArrayList<>();
        for (var coe : COEFF) {
            linear.add(new LinearTransformation(pw -> new Point(
                pw.getX() * coe.get(0) + pw.getY() * coe.get(1) + coe.get(2),
                pw.getX() * coe.get(INDEX_3) + pw.getY() * coe.get(INDEX_4) + coe.get(INDEX_5)
            ), new Pixel(
                random.nextInt(BOUND), // R от 0 до 255
                random.nextInt(BOUND), // G от 0 до 255
                random.nextInt(BOUND), // B от 0 до 255
                0
            )));
        }
        // Создание и настройка рендерера
        Renderer renderer = new SingleThreadRenderer(seed, symmetry);

        // Запуск генерации в одном потоке
        long startTime = System.currentTimeMillis();
        fractalImage =
            renderer.render(
                fractalImage,
                WORLD,
                variations,
                samples,
                iterPerSample,
                seed,
                linear
            );
        long endTime = System.currentTimeMillis();

        // Обработка изображения (пост-обработка)
        ImageProcessor imageProcessor = image -> {
            // Пример пост-обработки: инверсия цветов
            for (int i = 0; i < image.getData().length; i++) {
                Pixel pixel = image.getData()[i];
                if (pixel != null) {
                    image.getData()[i] = new Pixel(
                        BOUND - 1 - (int) pixel.getR(),
                        BOUND - 1 - (int) pixel.getG(),
                        BOUND - 1 - (int) pixel.getB(),
                        (int) pixel.getCount()
                    );
                }
            }
        };
        imageProcessor.process(fractalImage);

        // Сохранение изображения
        saveImage(fractalImage, Paths.get("fractal_single_threaded.png"), ImageFormat.PNG);
    }

    private static void saveImage(FractalImage image, Path filename, ImageFormat format) {
        ImageUtils.save(image, filename, format);
    }
}
