package main.java.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {

    private int[] pixels;
    private int width, height;
    public BufferedImage image;

    public Texture(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new int[getWidth()*getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                this.pixels[i + getWidth()*j] = image.getTransparency() << 24 | image.getRGB(i, j);
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int x, int y) {
        return getPixels()[x + getWidth()*y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
