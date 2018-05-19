package main.java.player;

import main.java.util.Direction;
import main.java.world.SimplexWorld;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    SimplexWorld world;
    Player player;

    public InputHandler(SimplexWorld world) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'k':
                world.setWorldScale(Integer.parseInt(JOptionPane.showInputDialog("Set java.java.worldmain.PerlinWorld Scale: ")));
                break;
            case 'w':
                player.move(Direction.UP, 1);
                break;
            case 's':
                player.move(Direction.DOWN, 1);
                break;
            case 'a':
                player.move(Direction.LEFT, 1);
                break;
            case 'd':
                player.move(Direction.RIGHT, 1);
                break;
            case ' ':
                int x = Integer.parseInt(JOptionPane.showInputDialog("Position X: "));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Position Y: "));
                Player newPlayer = new Player("Potato");
                newPlayer.x = x;
                newPlayer.y = y;
                world.spawnPlayer(newPlayer);
                this.player = newPlayer;
                break;
            case '=':
                world.setWorldScale(world.getWorldScale() * 2);
                System.out.println(world.getWorldScale());
                break;
            case '-':
                world.setWorldScale(Math.max(1, world.getWorldScale() / 2));
            case 'W':
                player.move(Direction.UP, 16);
                break;
            case 'S':
                player.move(Direction.DOWN, 16);
                break;
            case 'A':
                player.move(Direction.LEFT, 16);
                break;
            case 'D':
                player.move(Direction.RIGHT, 16);
                break;
            default:
                break;
        }
        world.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
