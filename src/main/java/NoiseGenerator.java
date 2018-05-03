package main.java;

import main.java.player.InputHandler;
import main.java.tiles.Tiles;
import main.java.world.SimplexWorld;

import javax.swing.*;

public class NoiseGenerator extends JFrame {

    public int width;
    public int height;

    public NoiseGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Tiles.init();
        NoiseGenerator window = new NoiseGenerator(2000, 2000);
        window.setTitle("Noise");

        /*
        float[][] whiteNoise1 = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] treeNoise = gen.genWhiteNoiseMap(window.width, window.height);
        float[][] tileNoise = gen.genPerlinNoise(whiteNoise1, 5, 0.4f, 1.0f);
        float[][] biomeNoise = gen.genPerlinNoise(whiteNoise1, 7, 0.2f, 1.0f);

        java.java.worldmain.PerlinWorld java.java.world = new java.java.PerlinWorlderlinWorld(tileNoise, biomeNoise, treeNoise,  2);
        */
        SimplexWorld world = new SimplexWorld(Long.parseLong(JOptionPane.showInputDialog("Insert Integer Seed: ")));
        window.addKeyListener(new InputHandler(world));
        window.add(world);
        world.repaint();
    }
}
