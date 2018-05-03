package main.java.assets;

import java.awt.*;

public class Graphics {

    protected java.awt.Graphics graphics;

    public Graphics(java.awt.Graphics g) {
        this.graphics = g;
    }

    public void draw(Texture tex, int x, int y, int size) {
        for (int i = 0; i < tex.getWidth(); i++) {
            for (int j = 0; j < tex.getHeight(); j++) {
                graphics.setColor(new Color(tex.getPixel(i, j)));
                graphics.fillRect((x+i)*size, (y+j)*size, size, size);
            }
        }
    }

    public void setGraphics(java.awt.Graphics graphics) {
        this.graphics = graphics;
    }
}
