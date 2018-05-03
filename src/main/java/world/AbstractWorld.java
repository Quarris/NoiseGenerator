package main.java.world;

import javax.swing.*;
import main.java.player.Camera;
import main.java.player.Player;

public abstract class AbstractWorld extends JPanel {

    protected Player player;
    protected Camera cam;
    protected float worldScale = 1;

    public AbstractWorld() {

    }

    public void setWorldScale(int scale) {
        this.worldScale = worldScale;
        getCam().width = this.getWidth() / worldScale;
        getCam().height = this.getHeight() / worldScale;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Camera getCam() {
        return this.cam;
    }

    public void createPlayer(Player player) {
        this.player = player;
        player.setWorld(this);
        this.cam = new Camera(player, this.getWidth() / worldScale, this.getHeight() / worldScale);
    }


}
