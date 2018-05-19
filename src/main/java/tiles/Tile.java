package main.java.tiles;

import main.java.assets.Texture;

import main.java.renderer.DefaultTileRenderer;
import main.java.renderer.ITileRenderer;
import main.java.registry.Registry;

public class Tile {

    protected String name;
    private ITileRenderer<Tile> renderer;
    public boolean canCollide;

    public Tile(String name) {
        canCollide = true;
        this.name = name;
        this.renderer = createRenderer(name);
    }

    public Tile register() {
        Registry.TEXTURE_REGISTRY.put(this.getName(), new Texture("src/main/assets/"+getName()+".png"));
        return this;
    }

    public Tile setCollision(boolean collision) {
        canCollide = collision;
        return this;
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
