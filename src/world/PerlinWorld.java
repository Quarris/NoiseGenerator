package world;

import java.awt.*;

public class PerlinWorld extends AbstractWorld {

    private static final Color WATER_NORMAL = new Color(0x2a70e0);
    private static final Color WATER_SWAMP = new Color(0x316640);
    private static final Color GRASS_NORMAL = new Color(0x0da323);
    private static final Color GRASS_SWAMP = new Color(0x316d18);
    private static final Color SAND = new Color(0xc6ad59);
    private static final Color ROCK = new Color(0x6b6b6b);
    private static final Color SNOW = new Color(0xd6d6d6);
    private static final Color TREE = new Color(0x034704);

    private static final int BIOME_NORMAL = 0;
    private static final int BIOME_SWAMP = 1;
    private static final int BIOME_OCEAN = 2;

    private float[][] tileMap;
    private float[][] biomeMap;
    private float[][] treeMap;
    private int size;

    public PerlinWorld(float[][] tileMap, int size) {
        this(tileMap, null, null, size);
    }

    public PerlinWorld(float[][] tileMap, float[][] biomeMap, int size) {
        this(tileMap, biomeMap, null, size);
    }

    public PerlinWorld(float[][] tileMap, float[][] biomeMap, float[][] treeMap, int size) {
        this.tileMap = tileMap;
        this.biomeMap = biomeMap;
        this.treeMap = treeMap;
        this.size = size;
    }

    public Dimension getWorldSize() {
        return new Dimension(tileMap.length, tileMap[0].length);
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
            for (int y = (int)cam.y; y < (((cam.y + cam.height) <= getWorldSize().height) ? (cam.y + cam.height) : getWorldSize().height); y++) {
                for (int x = (int)cam.x; x < (((cam.x + cam.width) <= getWorldSize().width) ? (cam.x + cam.width) : getWorldSize().width); x++) {
                    size = (int)worldScale;
                    int biome = BIOME_NORMAL;
                    if (biomeMap != null) {
                        biome = getBiome(biomeMap, x, y);
                    }
                    Color tile = getTile(tileMap, biome, x, y);
                    g.setColor(tile);
                    g.fillRect((int)(x - cam.x) * size, (int)(y - cam.y) * size, size, size);
                }
            }
            for (int y = (int)cam.y; y < (((cam.y + cam.height) <= getWorldSize().height) ? (cam.y + cam.height) : getWorldSize().height); y++) {
                for (int x = (int)cam.x; x < (((cam.x + cam.width) <= getWorldSize().width) ? (cam.x + cam.width) : getWorldSize().width); x++) {
                    int height = (int) (tileMap[x][y] * 100);
                    int biome = getBiome(biomeMap, x, y);
                    boolean genTree = treeMap != null && treeMap[x][y] > 0.9f && getBiome(biomeMap, x, y) != BIOME_OCEAN;
                    if (genTree && ((biome == BIOME_NORMAL && height >= 50 && height < 70) || (biome == BIOME_SWAMP && height >= 50))) {
                        g.setColor(TREE);
                        g.fillOval((int)(x - cam.x) * size, (int)(y - cam.y) * size, size + 1, size + 1);
                    }
                }
            }
            if (player != null) {
                g.setColor(Color.RED);
                g.fillRect((int)(player.x - cam.x) * size - size, (int)(player.y - cam.y) * size - size, size * 2, size * 2);
            }
        }
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
