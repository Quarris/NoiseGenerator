package main.java.player;

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
                player.move(0, -100);
                break;
            case 's':
                player.move(0, 100);
                break;
            case 'a':
                player.move(-100, 0);
                break;
            case 'd':
                player.move(100, 0);
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
                world.setWorldScale(world.getWorldScale()*2);
                System.out.println(world.getWorldScale());
                break;
            case '-':
                world.setWorldScale(Math.max(1, world.getWorldScale()/2));
            default:
                break;
        }
        System.out.println("Pressed Key " + e + " - " + e.getKeyCode());
        world.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
