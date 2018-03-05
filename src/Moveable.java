/**
 * Class for all the moveable sprites
 */
public abstract class Moveable extends Sprite {

    private float x = getX();
    private float y = getY();

    public Moveable(String image_src, float x, float y, String tag) {
        super(image_src, x, y, tag);
    }

    /**
     * To move a certain sprite to its destination
     * @param dir
     */
    public void moveToDest(int dir) {
        // translate the direction to an x and y displacement
        float speed = 32;
        float delta_x = 0,
                delta_y = 0;
        switch (dir) {
            case DIR_LEFT:
                delta_x = -speed;
                break;
            case DIR_RIGHT:
                delta_x = speed;
                break;
            case DIR_UP:
                delta_y = -speed;
                break;
            case DIR_DOWN:
                delta_y = speed;
                break;
        }

        // make sure the position isn't occupied!
        if (!World.isBlocked(x + delta_x, y + delta_y)) {
            x += delta_x;
            y += delta_y;
            setX(x);
            setY(y);
        }
    }

}
