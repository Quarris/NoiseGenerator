import javax.swing.*;
import java.awt.*;

public class NoisePanel extends JPanel {

    private static final Color WATER_NORMAL = new Color(0x2a70e0);
    private static final Color WATER_SWAMP = new Color(0x316640);
    private static final Color GRASS_NORMAL = new Color(0x0da323);
    private static final Color GRASS_SWAMP = new Color(0x316d18);
    private static final Color SAND_NORMAL = new Color(0xc6ad59);
    private static final Color SAND_SWAMP = new Color(0x869947);
    private static final Color ROCK = new Color(0x6b6b6b);
    private static final Color SNOW = new Color(0xd6d6d6);
    private static final Color TREE = new Color(0x034704);


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
                boolean isSwamp = biomeMap != null && biomeMap[x][y] < 0.5f;
                boolean genTree = treeMap != null && treeMap[x][y] > 0.85f;
                if (height < 40) {
                    g.setColor(!isSwamp ? WATER_NORMAL : WATER_SWAMP);
                } else if (height < 50) {
                    g.setColor(!isSwamp ? SAND_NORMAL : SAND_NORMAL);
                } else if (height < 70) {
                    g.setColor(!isSwamp ? GRASS_NORMAL : GRASS_SWAMP);
                } else if (height < 87 && !isSwamp) {
                    g.setColor(ROCK);
                } else if (!isSwamp) {
                    g.setColor(SNOW);
                }
                g.fillRect(x * size, y * size, size, size);
            }
        }
        for (int y = 0; y < tileMap.length; y++) {
            for (int x = 0; x < tileMap[y].length; x++) {
                int height = (int) (tileMap[x][y] * 100);
                boolean isSwamp = biomeMap != null && biomeMap[x][y] < 0.5f;
                boolean genTree = treeMap != null && treeMap[x][y] > 0.8f;
                if (height >= 50 && height < 70 && genTree) {
                    g.setColor(TREE);
                    g.fillOval(x, y, 2, 2);
                }
            }
        }
    }
}
