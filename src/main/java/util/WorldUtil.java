package main.java.util;

import main.java.tiles.Tile;
import main.java.tiles.Tiles;

public class WorldUtil {

    public static Tile getTileFromNoise(double height) {

        if (height < 0.49) {
            return Tiles.water;
        } else if (height < 0.57) {
            return Tiles.sand;
        } else if (height < 0.85) {
            return Tiles.grass;
        } else if (height < 0.93) {
            return Tiles.stone;
        } else {
            return Tiles.snow;
        }
    }
}
