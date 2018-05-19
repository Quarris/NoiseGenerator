package main.java.tiles;


public class Tiles {

    public static Tile water;
    public static Tile stone;
    public static Tile grass;
    public static Tile sand;
    public static Tile snow;

    public static void init() {
        water = new Tile("water").setCollision(false).register();
        stone = new Tile("stone").setCollision(false).register();
        grass = new Tile("grass").register();
        sand = new Tile("sand").register();
        snow = new Tile("snow").register();
    }

}
