public class Player {

    public int x = 0;
    public int y = 0;
    public String name;
    private World world;

    public Player(String name) {
        this.name = name;
        this.world = world;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
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
