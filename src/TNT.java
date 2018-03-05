/**
 * Class to create the TNT sprite
 */
public class TNT extends Pushable {

    public TNT(float x, float y, String tag) {
        super("res/tnt.png", x, y, tag);
    }

    /** Decision to blow up or not */
    private static boolean blowUp = false;

    /**
     * Override the push methed from pushable to make space for the cracked wall
     * @param dir the direction at which it will be pushed
     */
    @Override
    public void push(int dir){
        // send the coordinates for checking
        Coord.x = getX();
        Coord.y = getY();

        // check the coordinates for this particular direction
        Coord.checkCoord(dir);

        Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);
        if (sprite instanceof Moveable) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Wall) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Door) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Cracked) {
            blowUp = true;
        }
        moveToDest(dir);
        Player.setDir(dir);
    }

    public static boolean isBlowUp() {
        return blowUp;
    }

    public static void setBlowUp(boolean blowUp) {
        TNT.blowUp = blowUp;
    }

}
