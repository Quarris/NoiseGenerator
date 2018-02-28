public class Camera {

    public float x, y, width, height;

    public Camera(Player player, float width, float height) {
        if (player.x - width/2 >= 0)
            this.x = player.x - width/2;
        else
            this.x = 0;

        if (player.y - height/2 >= 0)
            this.y = player.y - height/2;
         else
            this.y = 0;

        this.width = width;
        this.height = height;
    }
}
