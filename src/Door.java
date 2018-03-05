import org.newdawn.slick.Graphics;

/**
 * Class for the sprite Door
 */
public class Door extends Sprite {

    public Door(float x, float y, String tag) {
        super("res/door.png", x, y, tag);
    }

    /**
     * Override the render class
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (!(World.checkSwitch())){
            super.render(g);
        }
    }
}
