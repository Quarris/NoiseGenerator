package main.java.world;

import main.java.util.Pos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SimplexWorld extends AbstractWorld {

    private long seed;
    private final int LOADED_CHUNK_SIZE = 9;
    protected Map<Pos, Chunk> loadedChunks;
    protected main.java.assets.Graphics graphics;

    public SimplexWorld(long seed) {
        this.seed = seed;
        graphics = new main.java.assets.Graphics(getGraphics());
        loadedChunks = new HashMap<>();
        for (int x = 0; x < LOADED_CHUNK_SIZE; x++) {
            for (int y = 0; y < LOADED_CHUNK_SIZE; y++) {
                loadChunk(new Chunk(this, x, y));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        graphics.setGraphics(g);
        super.paintComponent(g);
        for (Map.Entry<Pos, Chunk> chunkPos : loadedChunks.entrySet()) {
            Chunk chunk = chunkPos.getValue();
            for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
                for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
                    chunk.getTile(x, y).getRenderer().render(graphics, (chunk.getX()*Chunk.CHUNK_SIZE+x)*worldScale, (chunk.getY()*Chunk.CHUNK_SIZE+y)*worldScale, chunk.getTile(x, y), worldScale);
                }
            }
        }
    }

    @Override
    public void setWorldScale(int scale) {
        this.worldScale = scale;
        repaint();
    }

    public Map<Pos, Chunk> getLoadedChunks() {
        return loadedChunks;
    }

    public Chunk getChunk(int chunkX, int chunkY) {
        return loadedChunks.get(new Pos(chunkX, chunkY));
    }

    public Chunk getChunk(Pos pos) {
        return loadedChunks.get(pos);
    }

    public void loadChunk(Chunk chunk) {
        loadedChunks.put(new Pos(chunk.getX(), chunk.getY()), chunk);

    }

    private void unloadChunk(Chunk chunk) {
        loadedChunks.remove(chunk);
    }

    public long getSeed() {
        return this.seed;
    }
}
