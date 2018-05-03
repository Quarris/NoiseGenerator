package main.java.tiles;

import main.java.assets.Texture;

import java.awt.*;
import main.java.renderer.DefaultTileRenderer;
import main.java.renderer.ITileRenderer;
import main.java.registry.Registry;

public class Tile {

    protected Color color;
    protected String name;
    private ITileRenderer<Tile> renderer;

    public Tile(String name, Color color) {
        this.color = color;
        this.name = name;
        this.renderer = createRenderer(name);
    }

    public Tile register() {
        Registry.TEXTURE_REGISTRY.put(this.getName(), new Texture("src/main/assets/"+getName()+".png"));
        return this;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public ITileRenderer<Tile> createRenderer(String name) {
        return new DefaultTileRenderer(name);
    }

    public ITileRenderer<Tile> getRenderer() {
        return renderer;
    }
}
