package main.java.renderer;

import main.java.assets.AssetManager;
import main.java.assets.Graphics;
import main.java.tiles.Tile;


public class DefaultTileRenderer implements ITileRenderer<Tile> {

    protected String name;

    public DefaultTileRenderer(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics g, int renderX, int renderY, Tile tile, int size) {
        //g.draw(AssetManager.getTexture(tile.getName()), renderX, renderY, size);
        g.drawImage(AssetManager.getTexture(tile.getName()), renderX, renderY, size);
    }
}
