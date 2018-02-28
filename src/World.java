import javax.swing.*;
import java.awt.*;

public class World extends JPanel {

    private static final Color WATER_NORMAL = new Color(0x2a70e0);
    private static final Color WATER_SWAMP = new Color(0x316640);
    private static final Color GRASS_NORMAL = new Color(0x0da323);
    private static final Color GRASS_SWAMP = new Color(0x316d18);
    private static final Color SAND = new Color(0xc6ad59);
    private static final Color ROCK = new Color(0x6b6b6b);
    private static final Color SNOW = new Color(0xd6d6d6);
    private static final Color TREE = new Color(0x034704);

    private Player player;
    private Camera cam;

    private static final int BIOME_NORMAL = 0;
    private static final int BIOME_SWAMP = 1;
    private static final int BIOME_OCEAN = 2;

    private float[][] tileMap;
    private float[][] biomeMap;
    private float[][] treeMap;
    private int size;

    public World(float[][] tileMap, int size) {
        this(tileMap, null, null, size);
    }

    public World(float[][] tileMap, float[][] biomeMap, int size) {
        this(tileMap, biomeMap, null, size);
    }

    public World(float[][] tileMap, float[][] biomeMap, float[][] treeMap, int size) {
        this.tileMap = tileMap;
        this.biomeMap = biomeMap;
        this.treeMap = treeMap;
        this.size = size;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cam == null) {
            for (int y = 0; y < tileMap.length; y++) {
                for (int x = 0; x < tileMap[y].length; x++) {
                    int biome = BIOME_NORMAL;
                    if (biomeMap != null) {
                        biome = getBiome(biomeMap, x, y);
                    }
                    Color tile = getTile(tileMap, biome, x, y);
                    g.setColor(tile);
                    g.fillRect(x * size, y * size, size, size);
                }
            }
            for (int y = 0; y < tileMap.length; y++) {
                for (int x = 0; x < tileMap[y].length; x++) {
                    int height = (int) (tileMap[x][y] * 100);
                    int biome = getBiome(biomeMap, x, y);
                    boolean genTree = treeMap != null && treeMap[x][y] > 0.9f && getBiome(biomeMap, x, y) != BIOME_OCEAN;
                    if (genTree && ((biome == BIOME_NORMAL && height >= 50 && height < 70) || (biome == BIOME_SWAMP && height >= 50))) {
                        g.setColor(TREE);
                        g.fillOval(x * size, y * size, size + 1, size + 1);
                    }
                }
            }
            if (player != null) {
                g.setColor(Color.RED);
                g.fillRect(player.x * size - size, player.y * size - size, size * 2, size * 2);
            }
        } else {
            for (int y = (int)cam.y; y < cam.height; y++) {
                for (int x = (int)cam.x; x < cam.width; x++) {
                    size = 10;//getWidth() / cam.width > getHeight() / cam.height ?
                            //(int)(getWidth()/cam.width) : (int)(getHeight()/cam.height);
                    int biome = BIOME_NORMAL;
                    if (biomeMap != null) {
                        biome = getBiome(biomeMap, x, y);
                    }
                    Color tile = getTile(tileMap, biome, x, y);
                    g.setColor(tile);
                    g.fillRect(x * size, y * size, size, size);
                }
            }
            for (int y = (int)cam.y; y < cam.height; y++) {
                for (int x = (int)cam.x; x < cam.width; x++) {
                    int height = (int) (tileMap[x][y] * 100);
                    int biome = getBiome(biomeMap, x, y);
                    boolean genTree = treeMap != null && treeMap[x][y] > 0.9f && getBiome(biomeMap, x, y) != BIOME_OCEAN;
                    if (genTree && ((biome == BIOME_NORMAL && height >= 50 && height < 70) || (biome == BIOME_SWAMP && height >= 50))) {
                        g.setColor(TREE);
                        g.fillOval(x * size, y * size, size + 1, size + 1);
                    }
                }
            }
            if (player != null) {
                g.setColor(Color.RED);
                g.fillRect(player.x * size - size, player.y * size - size, size * 2, size * 2);
            }
        }
    }

    public void createPlayer(Player player) {
        this.player = player;
        player.setWorld(this);
        this.cam = new Camera(player, 20, 20);
    }

    public Player getPlayer() {
        return player;
    }

    public int getBiome(float[][] biomeMap, int x, int y) {
        if (biomeMap[x][y] < 0.5f) {
            return BIOME_OCEAN;
        } else if (biomeMap[x][y] < 0.8f) {
            return BIOME_NORMAL;
        } else
            return BIOME_SWAMP;
    }

    public Color getTile(float[][] tileMap, int biome, int x, int y) {
        int height = (int)(tileMap[x][y] * 100);
        Color tile = null;
        switch (biome) {
            case BIOME_NORMAL: {
                if (height < 40) {
                    tile = WATER_NORMAL;
                } else if (height < 50) {
                    tile = SAND;
                } else if (height < 70) {
                    tile = GRASS_NORMAL;
                } else if (height < 87) {
                    tile = ROCK;
                } else
                    tile = SNOW;
            }
            break;
            case BIOME_SWAMP: {
                if (height < 40) {
                    tile = WATER_SWAMP;
                } else if (height < 50) {
                    tile = SAND;
                } else
                    tile = GRASS_SWAMP;
                break;
            }

            case BIOME_OCEAN: {
                if (height < 90) {
                    tile = WATER_NORMAL;
                } else if (height < 92) {
                    tile = SAND;
                } else
                    tile = GRASS_NORMAL;
                break;
            }
        }
        return tile;
    }
}
