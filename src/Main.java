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
        Main window = new Main(2000, 2000);
        window.setTitle("Noise");

        NoiseGenerator gen = new NoiseGenerator(Long.parseLong(JOptionPane.showInputDialog("Insert Integer Seed: ")));

        float[][] whiteNoise1 = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] treeNoise = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] tileNoise = gen.genPerlinNoise(whiteNoise1, 5, 0.4f, 1.0f);
        float[][] biomeNoise = gen.genPerlinNoise(whiteNoise1, 7, 0.2f, 1.0f);
        World world = new World(tileNoise, biomeNoise, treeNoise,  2);
        window.addKeyListener(new InputHandler(world));
        window.add(world);
        world.repaint();
    }
}
