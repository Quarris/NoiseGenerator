package main.java.world;

import main.java.NoiseGenerator;
import main.java.player.Camera;
import main.java.player.Player;
import main.java.util.Pos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SimplexWorld extends JPanel {

    private long seed;
    public static final int LOADED_CHUNK_SIZE = 21;
    protected Map<Pos, Chunk> loadedChunks;
    protected main.java.assets.Graphics graphics;
    protected int worldScale = 1;
    private Player player;

    public SimplexWorld(long seed) {
        this.seed = seed;
        graphics = new main.java.assets.Graphics(getGraphics());
        loadedChunks = new HashMap<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        graphics.setGraphics(g);
        super.paintComponent(g);
        if (player == null) {
            for (Map.Entry<Pos, Chunk> chunkPos : loadedChunks.entrySet()) {
                Chunk chunk = chunkPos.getValue();
                for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
                    for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
                        chunk.getTile(x, y).getRenderer().render(graphics,  (chunk.getX() * Chunk.CHUNK_SIZE + x) * worldScale, (chunk.getY() * Chunk.CHUNK_SIZE + y) * worldScale, chunk.getTile(x, y), worldScale);
                    }
                }
            }
        }
        else {
            for (Map.Entry<Pos, Chunk> chunkPos : loadedChunks.entrySet()) {
                Chunk chunk = chunkPos.getValue();
                g.setColor(Color.RED);
                g.fillRect(getWidth()/2 - worldScale, getHeight()/2 - worldScale, 2*worldScale, 2*worldScale);
                for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
                    for (int y = 0; y < Chunk.CHUNK_SIZE; y++) {
                        chunk.getTile(x, y).getRenderer().render(graphics,  getWidth()/2 + ((chunk.getX() * Chunk.CHUNK_SIZE + x) * worldScale) - 72 * worldScale, getHeight()/2 + ((chunk.getY() * Chunk.CHUNK_SIZE + y) * worldScale) - 71 * worldScale, chunk.getTile(x, y), worldScale);
                    }
                }
            }
        }
    }

    public void setWorldScale(int scale) {
        this.worldScale = scale;
        repaint();
    }

    public int getWorldScale() {
        return worldScale;
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

    public void unloadChunk(Chunk chunk) {
        loadedChunks.remove(new Pos(chunk.getX(), chunk.getY()));
    }

    public long getSeed() {
        return this.seed;
    }

    public void spawnPlayer(Player player) {
        this.player = player;
        player.setWorld(this);
        Camera cam = new Camera(player, 640, 480);
        player.setCam(cam);
        player.loadChunks();
    }
}
