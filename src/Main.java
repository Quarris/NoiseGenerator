import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Main extends JFrame {

    public int width;
    public int height;

    public Main(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main window = new Main(1000, 1000);
        window.setTitle("Noise");

        NoiseGenerator gen = new NoiseGenerator(0L);
        NoiseGenerator gen1 = new NoiseGenerator(18245719L);

        float[][] whiteNoise1 = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] whiteNoise2 = gen1.genWhiteNoiseMap(window.width, window.height);
        float[][] treeNoise = gen.genPerlinNoise(whiteNoise1, 2, 0.2f, 1.0f);
        float[][] tileNoise = gen.genPerlinNoise(whiteNoise1, 6, 0.4f, 1.0f);
        float[][] biomeNoise = gen.genPerlinNoise(whiteNoise2, 7, 0.01f, 1.0f);
        NoisePanel panel = new NoisePanel(tileNoise, biomeNoise, treeNoise,  1);
        window.add(panel);

    }
}
