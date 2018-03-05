/**
 * Class for all the pushable sprites
 */
public abstract class Pushable extends Moveable {

    public Pushable(String image_src, float x, float y, String tag) {
        super(image_src, x, y, tag);
    }


    /**
     * Method to push a sprite
     * @param dir the direction at which it will be pushed
     */
    public void push(int dir) {

        // send the coordinates for checking
        Coord.x = getX();
        Coord.y = getY();

        // check the coordinates for this particular direction
        Coord.checkCoord(dir);

        // create a sprite object
        Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);

        if (sprite instanceof Moveable) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Wall) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Cracked) {
            dir = DIR_NONE;
        }
        else if (sprite instanceof Door){
            if (!(World.checkSwitch())){ // check if the door is open or closed
                dir =DIR_NONE;
            }
            else {
                moveToDest(dir);
            }
        }

        moveToDest(dir); // move itself in the direction of being pushed
        Player.setDir(dir); // give space for the player to move in if pushed
    }
}

