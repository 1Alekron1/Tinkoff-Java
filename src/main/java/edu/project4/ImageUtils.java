package edu.project4;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ImageUtils {

    public static final double GAMMA = 2.2;
    public static final int R_VAL = 16;
    public static final int G_VAL = 8;

    private final static Logger LOGGER = LogManager.getLogger();

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage =
            new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB);

        var max = calculateMaxValue(image);
        Pixel defaultPixel = new Pixel(0, 0, 0, 0);
        var gamma = GAMMA;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel == null) {
                    pixel = defaultPixel;
                }
                var norm = Math.log10(pixel.getCount()) / max;
                pixel = new Pixel(
                    (int) (pixel.r() * Math.pow(norm, 1 / gamma)),
                    (int) (pixel.g() * Math.pow(norm, 1 / gamma)),
                    (int) (pixel.b() * Math.pow(norm, 1 / gamma)),
                    pixel.hitCount()
                );
                int rgb = ((int) pixel.getR() << R_VAL) | ((int) pixel.getG() << G_VAL) | (int) pixel.getB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(), filename.toFile());
        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
    }

    private static double calculateMaxValue(FractalImage image) {
        var max = 0.0;
        var gamma = GAMMA;
        Pixel defaultPixel = new Pixel(0, 0, 0, 0);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel == null) {
                    // Если null, используем начальный пиксель
                    pixel = defaultPixel;
                }
                var norm = Math.log10(pixel.getCount());
                if (norm > max) {
                    max = norm;
                }
            }
        }
        return max;
    }
}
