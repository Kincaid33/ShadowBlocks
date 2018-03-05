/**
 * Class for the sprite Mage which is a subclass of Moveable
 */

public class Mage extends Moveable {

    /**
     * Constructor of Mage class
     * @param x x coordinate of the sprite
     * @param y y coordinate of the sprite
     * @param tag name of the sprite
     */
    public Mage(float x, float y, String tag) {
        super("res/mage.png", x, y, tag);
    }

}
