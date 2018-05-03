package main.java.world;

import java.awt.*;

public class SimplexWorld extends AbstractWorld {

    private long seed;
    private final int LOADED_CHUNK_SIZE = 7;
    protected Chunk[] loadedChunks;
    protected main.java.assets.Graphics graphics;

    public SimplexWorld(long seed) {
        this.seed = seed;
        graphics = new main.java.assets.Graphics(getGraphics());
        loadedChunks = new Chunk[LOADED_CHUNK_SIZE*LOADED_CHUNK_SIZE];
        for (int x = 0; x < LOADED_CHUNK_SIZE; x++) {
            for (int y = 0; y < LOADED_CHUNK_SIZE; y++) {
                loadChunk(new Chunk(this, x, y));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        graphics.setGraphics(g);
        int size = 2;
        super.paintComponent(g);
        for (Chunk chunk : loadedChunks) {
            for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
                for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
                    chunk.getTile(x, y).getRenderer().render(graphics, (chunk.getX()*Chunk.CHUNK_SIZE+x)*size, (chunk.getY()*Chunk.CHUNK_SIZE+y)*size, chunk.getTile(x, y), size);
                }
            }
        }
    }

    public Chunk[] getLoadedChunks() {
        return loadedChunks;
    }

    public Chunk getChunk(int chunkX, int chunkY) {
        return loadedChunks[chunkX + LOADED_CHUNK_SIZE*chunkY];
    }

    public void loadChunk(Chunk chunk) {
        loadedChunks[chunk.getX() + LOADED_CHUNK_SIZE*chunk.getY()] = chunk;

    }

    private void unloadChunk(Chunk chunk) {
        loadedChunks[chunk.getX()+LOADED_CHUNK_SIZE*chunk.getY()] = null;
    }

    public long getSeed() {
        return this.seed;
    }
}
