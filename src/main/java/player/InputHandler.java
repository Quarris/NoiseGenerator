package main.java.player;

import main.java.world.AbstractWorld;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    AbstractWorld world;

    public InputHandler(AbstractWorld world) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'k':
                world.setWorldScale(Integer.parseInt(JOptionPane.showInputDialog("Set java.java.worldmain.PerlinWorld Scale: ")));
                world.getCam().adjust();
                break;
            case 'w':
                world.getPlayer().move(0, -1);
                System.out.println(world.getPlayer().x + ", " + world.getPlayer().y);
                break;
            case 's':
                world.getPlayer().move(0, 1);
                System.out.println(world.getPlayer().x + ", " + world.getPlayer().y);

                break;
            case 'a':
                world.getPlayer().move(-1, 0);
                System.out.println(world.getPlayer().x + ", " + world.getPlayer().y);

                break;
            case 'd':
                world.getPlayer().move(1, 0);
                System.out.println(world.getPlayer().x + ", " + world.getPlayer().y);
                break;
            case ' ':
                int x = Integer.parseInt(JOptionPane.showInputDialog("Position X: "));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Position Y: "));
                Player newPlayer = new Player("Potato");
                newPlayer.x = x;
                newPlayer.y = y;
                world.createPlayer(newPlayer);
                 break;
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
