package main.java.tiles;

import java.awt.*;

public class Tiles {

    public static Tile water;
    public static Tile stone;
    public static Tile grass;
    public static Tile sand;
    public static Tile snow;

    public static void init() {
        water = new Tile("water", new Color(50, 50, 255)).register();
        stone = new Tile("stone", Color.LIGHT_GRAY).register();
        grass = new Tile("grass", Color.GREEN).register();
        sand = new Tile("sand", Color.YELLOW).register();
        snow = new Tile("snow", Color.WHITE).register();
    }

}
