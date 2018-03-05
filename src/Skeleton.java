/**
 * Class for the sprite Skeleton which is a subclass of Moveable
 */
public class Skeleton extends Moveable {

    /** Time passed between each movement */
    private int nextMove = 0;
    private static int direction = -1; // -1 indicates positive down

    public Skeleton(float x, float y, String tag) {
        super("res/skull.png", x, y, tag);
    }

    @Override
    public void update(int delta) {
        nextMove += delta;

        if (nextMove >= App.SKELETON_MOVEMENT_TIME) {
            int dir;
            if (direction == 1){
                dir = DIR_DOWN;
            } else {
                dir = DIR_UP;
            }
            // send the coordinates for checking
            Coord.x = getX();
            Coord.y = getY();

            // check the coordinates for this particular direction
            Coord.checkCoord(dir);

            // create a sprite object
            Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);

            if ((sprite instanceof Cracked) || (sprite instanceof Pushable) || (sprite instanceof Wall)) {
                direction = -1*(direction);
            }
            else if (sprite instanceof Door){
                if (!(World.checkSwitch())){ // check if door is opened or closed
                    direction = -1*(direction);
                }
            }

            moveToDest(dir);
            nextMove = 0; // set the counter to zero
        }
    }
}