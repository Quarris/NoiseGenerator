package main.java.world;

import main.java.noise.SimplexNoise;
import main.java.tiles.Tile;
import main.java.util.Pos;
import main.java.util.WorldUtil;

public class Chunk {

    private Pos pos;
    private SimplexWorld world;
    protected Tile[] tiles;

    public static final int CHUNK_SIZE = 16;

    public Chunk(SimplexWorld world, int x, int y) {
        float divider = 100;
        this.pos = new Pos(x, y);
        this.world = world;
        this.tiles = new Tile[CHUNK_SIZE*CHUNK_SIZE];
        Chunk chunk = this;
        SimplexNoise noise1 = new SimplexNoise(world.getSeed());
        SimplexNoise noise2 = new SimplexNoise(world.getSeed()<<2);
        SimplexNoise noise3 = new SimplexNoise(world.getSeed()<<4);
        SimplexNoise noise4 = new SimplexNoise(world.getSeed()<<8);
        for (int tileX = 0; tileX < CHUNK_SIZE; tileX++) {
            for (int tileY = 0; tileY < CHUNK_SIZE; tileY++) {
                double noiseX = (chunk.getX()*Chunk.CHUNK_SIZE+tileX)/divider;
                double noiseY = (chunk.getY()*Chunk.CHUNK_SIZE+tileY)/divider;

                double p1 = (noise1.noise(noiseX, noiseY)+1)/2d;
                double p2 = (noise2.noise(noiseX*2, noiseY*2)+1)/2d;
                double p3 = (noise3.noise(noiseX*4, noiseY*4)+1)/2d;
                double p4 = (noise4.noise(noiseX*8, noiseY*8)+1)/2d;

                double p = (p1+0.5d*p2+0.25d*p3+0.125d*p4)/1.875d;

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

    public Pos getPos() {
        return pos;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }
}
