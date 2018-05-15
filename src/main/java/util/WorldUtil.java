package main.java.util;

import main.java.tiles.Tile;
import main.java.tiles.Tiles;
import main.java.world.Chunk;

public class WorldUtil {

    public static Tile getTileFromNoise(double height) {

        if (height < 0.49) {
            return Tiles.water;
        }
        else if (height < 0.57) {
            return Tiles.sand;
        }
        else if (height < 0.85) {
            return Tiles.grass;
        }
        else if (height < 0.93) {
            return Tiles.stone;
        }
        else {
            return Tiles.snow;
        }
    }

    public static Pos getChunkGridFromPos(Pos pos) {
        return getChunkGridFromPos(pos.x, pos.y);
    }

    public static Pos getChunkGridFromPos(int x, int y) {
        return new Pos(x/Chunk.CHUNK_SIZE, y/Chunk.CHUNK_SIZE);
    }

    public static Pos getPosFromChunkGrid(Pos pos) {
        return getPosFromChunkGrid(pos.x, pos.y);
    }

    public static Pos getPosFromChunkGrid(int x, int y) {
        return new Pos(x*Chunk.CHUNK_SIZE, y*Chunk.CHUNK_SIZE);
    }
}
