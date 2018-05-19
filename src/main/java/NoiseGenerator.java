package main.java;

import main.java.player.InputHandler;
import main.java.tiles.Tiles;
import main.java.world.SimplexWorld;

import javax.swing.*;
import java.util.Random;

public class NoiseGenerator extends JFrame {

    public int width;
    public int height;
    public static InputHandler inputs;

    public NoiseGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Tiles.init();
        final NoiseGenerator window = new NoiseGenerator(680, 400);
        window.setTitle("Noise");

        String stringSeed = JOptionPane.showInputDialog("Insert Integer Seed: ");
        Long seed = stringSeed.isEmpty() ? new Random().nextLong() : Long.parseLong(stringSeed);
        final SimplexWorld world = new SimplexWorld(seed);
        inputs = new InputHandler(world);

        SwingUtilities.invokeLater(() -> {
            window.addKeyListener(inputs);
            window.add(world);
            window.getContentPane().revalidate();
            world.repaint();
        });


    }
}
