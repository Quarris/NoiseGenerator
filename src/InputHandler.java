import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    World world;

    public InputHandler(World world) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':

                break;
            case 's':
                break;
            case 'a':
                break;
            case 'd':
                break;
            case ' ': {
                System.out.println("Pressed Space First");
                int x = Integer.parseInt(JOptionPane.showInputDialog("Position X: "));
                int y = Integer.parseInt(JOptionPane.showInputDialog("Position Y: "));
                Player newPlayer = new Player("Potato");
                newPlayer.x = x;
                newPlayer.y = y;
                world.createPlayer(newPlayer);
                System.out.println("Pressed Space Last");
                break;
            }
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
