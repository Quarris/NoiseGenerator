package main.java.renderer;

import main.java.tiles.Tile;
import main.java.assets.Graphics;

public interface ITileRenderer<T extends Tile> {

    void render(Graphics g, int renderX, int renderY, T tile, int size);
}
