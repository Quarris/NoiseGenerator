public class Player {

    public int x = 0;
    public int y = 0;
    public String name;
    private World world;

    public Player(String name) {
        this.name = name;
    }

    public void setPos(int x, int y) {
        if (x < world.getWorldSize().width && x >=0)
            this.x = x;

        if (y < world.getWorldSize().height && y >= 0)
            this.y = y;

        world.getCam().adjust();
    }

    public void move(int movX, int movY) {
        setPos(this.x + movX, this.y + movY);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }
}
