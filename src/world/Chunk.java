package world;

import noise.SimplexNoise;
import tiles.Tile;
import util.WorldUtil;

public class Chunk {

    private int x, y;
    private SimplexWorld world;
    protected Tile[] tiles;

    public static final int CHUNK_SIZE = 16;

    public Chunk(SimplexWorld world, int x, int y) {
        float divider = 25;
        this.x = x;
        this.y = y;
        this.world = world;
        this.tiles = new Tile[CHUNK_SIZE*CHUNK_SIZE];
        Chunk chunk = this;
        SimplexNoise noise = new SimplexNoise(world.getSeed());
        for (int tileX = 0; tileX < CHUNK_SIZE; tileX++) {
            for (int tileY = 0; tileY < CHUNK_SIZE; tileY++) {
                double p = (noise.noise((chunk.getX()*Chunk.CHUNK_SIZE+tileX)/divider, (chunk.getY()*Chunk.CHUNK_SIZE+tileY)/divider)+1)/2d;
                System.out.println(p);
                tiles[tileX+CHUNK_SIZE*tileY] = WorldUtil.getTileFromNoise(p);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x+CHUNK_SIZE*y];
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
