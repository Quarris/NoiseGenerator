import javax.swing.*;
import java.awt.*;

public class NoisePanel extends JPanel {

    private static final Color WATER_NORMAL = new Color(0x2a70e0);
    private static final Color WATER_SWAMP = new Color(0x316640);
    private static final Color GRASS_NORMAL = new Color(0x0da323);
    private static final Color GRASS_SWAMP = new Color(0x316d18);
    private static final Color SAND_NORMAL = new Color(0xc6ad59);
    private static final Color SAND_SWAMP = new Color(0xd8bd61);
    private static final Color DEEP_WATER = new Color(0x0e2177);
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

    public NoisePanel(float[][] tileMap, int size) {
        this(tileMap, null, null, size);
    }

    public NoisePanel(float[][] tileMap, float[][] biomeMap, int size) {
        this(tileMap, biomeMap, null, size);
    }

    public NoisePanel(float[][] tileMap, float[][] biomeMap, float[][] treeMap, int size) {
        this.tileMap = tileMap;
        this.biomeMap = biomeMap;
        this.treeMap = treeMap;
        this.size = size;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < tileMap.length; y++) {
            for (int x = 0; x < tileMap[y].length; x++) {
                int height = (int) (tileMap[x][y] * 100);
                int biome = BIOME_NORMAL;
                if (biomeMap != null) {
                    biome = getBiome(biomeMap, x, y);
                }
                switch (biome) {
                    case BIOME_NORMAL: {
                        if (height < 40) {
                            g.setColor(WATER_NORMAL);
                        } else if (height < 50) {
                            g.setColor(SAND_NORMAL);
                        } else if (height < 70) {
                            g.setColor(GRASS_NORMAL);
                        } else if (height < 87) {
                            g.setColor(ROCK);
                        } else
                            g.setColor(SNOW);
                        }
                        break;
                    case BIOME_SWAMP: {
                        if (height < 40) {
                            g.setColor(WATER_SWAMP);
                        } else if (height < 50) {
                            g.setColor(SAND_SWAMP);
                        } else
                            g.setColor(GRASS_SWAMP);
                        break;
                    }

                    case BIOME_OCEAN: {
                        if (height < 40) {
                            g.setColor(DEEP_WATER);
                        } else if (height < 90) {
                            g.setColor(WATER_NORMAL);
                        } else if (height < 92){
                            g.setColor(SAND_NORMAL);
                        } else
                            g.setColor(GRASS_NORMAL);
                        break;
                    }

                }
                g.fillRect(x * size, y * size, size, size);
            }
        }
        for (int y = 0; y < tileMap.length; y++) {
            for (int x = 0; x < tileMap[y].length; x++) {
                int height = (int) (tileMap[x][y] * 100);
                int biome = getBiome(biomeMap, x, y);
                boolean genTree = treeMap != null && treeMap[x][y] > 0.85f && getBiome(biomeMap, x, y) != BIOME_OCEAN;
                if (genTree && ((biome == BIOME_NORMAL && height >= 50 && height < 70) || (biome == BIOME_SWAMP && height >= 50))) {
                    g.setColor(TREE);
                    g.fillOval(x * size, y * size, size + 1, size + 1);
                }
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
}
