import javax.swing.*;

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
        Main window = new Main(2500, 2500);
        window.setTitle("Noise");

        NoiseGenerator gen = new NoiseGenerator(0L);
        NoiseGenerator gen1 = new NoiseGenerator(18245719L);

        float[][] whiteNoise1 = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] whiteNoise2 = gen1.genWhiteNoiseMap(window.width, window.height);
        float[][] treeNoise = gen.genPerlinNoise(whiteNoise1, 2, 0.2f, 1.0f);
        float[][] tileNoise = gen.genPerlinNoise(whiteNoise1, 5, 0.4f, 1.0f);
        float[][] biomeNoise = gen.genPerlinNoise(whiteNoise1, 7, 0.2f, 1.0f);
        NoisePanel panel = new NoisePanel(tileNoise, biomeNoise, treeNoise,  1);
        window.add(panel);

    }
}
