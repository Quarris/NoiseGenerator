package main.java.player;

import main.java.tiles.Tile;
import main.java.util.Direction;
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
    private SimplexWorld world;
    public Direction facing;

    public Player(String name) {
        this.name = name;
        this.facing = Direction.UP;
    }

    public void setPos(int x, int y) {
        this.lastX = this.x;
        this.lastY = this.y;
        this.x = x;
        this.y = y;
        loadChunks();
    }

    public void move(Direction dir, int amount) {
        Tile tile = world.getTile(x + dir.x*amount, y + dir.y*amount);
        System.out.println("("+(x + dir.x*amount)+", "+(y + dir.y*amount)+")"+tile.getName());
        if (tile.canCollide) {
            setPos(this.x + dir.x*amount, this.y + dir.y*amount);
            for (Direction direction : Direction.ADJACENTS) {
                if (dir == direction) {
                    this.facing = dir;
                    break;
                }
            }
        }
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
