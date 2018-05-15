package main.java.player;

import main.java.util.Pos;
import main.java.util.WorldUtil;
import main.java.world.Chunk;
import main.java.world.SimplexWorld;

public class Player {

    public int x;
    public int y;
    private int lastX;
    private int lastY;
    public String name;
    private Camera cam;
    private SimplexWorld world;

    public Player(String name) {
        this.name = name;
    }

    public void setPos(int x, int y) {
        this.lastX = this.x;
        this.lastY = this.y;
        this.x = x;
        this.y = y;
        loadChunks();
    }

    public Camera getCam() {
        return cam;
    }

    public void setCam(Camera cam) {
        this.cam = cam;
    }

    public void move(int movX, int movY) {
        setPos(this.x + movX, this.y + movY);
    }

    public void setWorld(SimplexWorld world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void loadChunks() {
        Pos playerChunkPos = WorldUtil.getChunkGridFromPos(x, y);
        Pos lastChunkPos = WorldUtil.getChunkGridFromPos(lastX, lastY);
        Chunk currChunk = world.getChunk(playerChunkPos.x, playerChunkPos.y);
        Chunk lastChunk = world.getChunk(lastChunkPos.x, lastChunkPos.y);
        if (currChunk == null) {
            currChunk = new Chunk(world, playerChunkPos.x, playerChunkPos.y);
        }
        world.loadChunk(currChunk);
        int halfChunk = SimplexWorld.LOADED_CHUNK_SIZE/2;
        for (int x = currChunk.getX() - halfChunk; x < currChunk.getX() + halfChunk; x++) {
            for (int y = currChunk.getY() - halfChunk; y < currChunk.getY() + halfChunk; y++) {
                if (world.getChunk(x, y) == null) {
                    world.loadChunk(new Chunk(world, x, y));
                }
            }
        }
        if (lastChunk != null && lastChunk != currChunk) {
            for (int x = lastChunk.getX() - halfChunk; x <= lastChunk.getX() + halfChunk; x++) {
                for (int y = lastChunk.getY() - halfChunk; y <= lastChunk.getY() + halfChunk; y++) {
                    Chunk testChunk = world.getChunk(x, y);
                    if (testChunk != null) {
                        if (Math.abs(x - currChunk.getX()) > halfChunk) {
                            world.unloadChunk(testChunk);
                        }
                        if (Math.abs(y - currChunk.getY()) > halfChunk) {
                            world.unloadChunk(testChunk);
                        }
                    }
                }
            }
        }

    }
}
