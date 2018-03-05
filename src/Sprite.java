
import org.newdawn.slick.*;

/**
 * Class to create all the sprites
 */
public abstract class Sprite {
    // Used to decide what direction an object is moving
    public static final int DIR_NONE = 0;
    public static final int DIR_LEFT = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_UP = 3;
    public static final int DIR_DOWN = 4;

    private Image image = null;
    private float x;
    private float y;
    private String tag;

    public Sprite(String image_src, float x, float y, String tag) {
        try {
            image = new Image(image_src);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.tag = tag;
    }

    public void update(int delta) {
    }

    public void render(Graphics g) {
        image.drawCentered(x, y); // draw the image based on it's center
    }

    /**
     * Getters and setters
     * @return
     */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
