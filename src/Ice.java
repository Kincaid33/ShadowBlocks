/**
 * Class for the sprite Ice which is a subclass of Pushable
 */
public class Ice extends Pushable {


    /**
     * Constructor of Ice class
     * @param x x coordinate of the sprite
     * @param y y coordinate of the sprite
     * @param tag name of the sprite
     */
    public Ice(float x, float y, String tag) {
        super("res/ice.png", x, y, tag);
    }

    /** Time passed between each time movement after being pushed */
    private static int nextMove = 0;
    /** Active is true when pushed */
    private boolean active;
    /** Direction in which it was pushed */
    private int dir;


    /**
     * @param delta
     */
    @Override
    public void update(int delta) {
        nextMove += delta;
        if (active){
            if (nextMove >= App.ICE_MOVEMENT_TIME){
                icePush(dir);
                nextMove = 0;
            }

            // send the coordinates for checking
            Coord.x = getX();
            Coord.y = getY();

            // check the coordinates for this particular direction
            Coord.checkCoord(dir);

            // create a sprite object
            Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);

            if ((sprite instanceof Moveable) || (sprite instanceof Cracked)) {
                active = false;
            }
            else if (sprite instanceof Door){
                if (!(World.checkSwitch())){
                    active = false;
                }
            }
        }
    }

    /**
     * Push specific to ice movement
     * @param dir
     */
    public void icePush(int dir) {
        this.dir = dir;
        // send the coordinates for checking
        Coord.x = getX();
        Coord.y = getY();

        // check the coordinates for this particular direction
        Coord.checkCoord(dir);

        // create a sprite object
        Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);

        if ((sprite instanceof Moveable) || (sprite instanceof Wall) || (sprite instanceof Cracked)) {
            dir = DIR_NONE;
        }

        else if (sprite instanceof Door){
            if (!(World.checkSwitch())){
                dir =DIR_NONE;
            }
            // check if you can find a better solution to not jump the door spot
            else {
                moveToDest(dir);
            }
        }
        moveToDest(dir);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
