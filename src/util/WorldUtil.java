package util;

import tiles.Tile;
import tiles.Tiles;

public class WorldUtil {

    public static Tile getTileFromNoise(double height) {

        if (height < 0.55) {
            return Tiles.water;
        } else if (height < 0.63) {
            return Tiles.sand;
        } else if (height < 0.85) {
            return Tiles.grass;
        } else if (height < 0.95) {
            return Tiles.stone;
        } else {
            return Tiles.snow;
        }
    }
}
