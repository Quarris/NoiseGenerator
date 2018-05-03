package player;

import world.AbstractWorld;
import world.PerlinWorld;

public class Player {

    public int x = 0;
    public int y = 0;
    public String name;
    private AbstractWorld world;

    public Player(String name) {
        this.name = name;
    }

    public void setPos(int x, int y) {
        if (world instanceof PerlinWorld) {
            PerlinWorld worldIn = (PerlinWorld)world;
            if (x < worldIn.getWorldSize().width && x >=0)
                this.x = x;

            if (y < worldIn.getWorldSize().height && y >= 0)
                this.y = y;
        } else {
            this.x = x;
            this.y = y;
        }

        world.getCam().adjust();
    }

    public void move(int movX, int movY) {
        setPos(this.x + movX, this.y + movY);
    }

    public void setWorld(AbstractWorld world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }
}
