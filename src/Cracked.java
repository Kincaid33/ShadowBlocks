/**
 * Class for the sprite Cracked Wall
 */
public class Cracked extends Sprite {

    private static float crackedX;
    private static float crackedY;

    public Cracked(float x, float y, String tag) {
        super("res/cracked_wall.png", x, y, tag);
        crackedX = getX();
        crackedY = getY();
    }

    /**
     * Get the current coordinates
     * @return
     */
    public static float getCrackedX() {
        return crackedX;
    }

    public static float getCrackedY() {
        return crackedY;
    }
}
