
/**
 * Class for the sprite Rogue which is a subclass of Moveable
 */
public class Rogue extends Moveable {

    private static int direction = -1; // -1 indicates positive left
    private static boolean onMove = false;
    public Rogue(float x, float y, String tag) {
        super("res/rogue.png", x, y, tag);
    }

    @Override
    public void update(int delta) {
        if (onMove)
        {
            int dir;
            if (direction == 1){
                dir = DIR_RIGHT;
            } else {
                dir = DIR_LEFT;
            }
            // send the coordinates for checking
            Coord.x = getX();
            Coord.y = getY();

            // check the coordinates for this particular direction
            Coord.checkCoord(dir);

            // create a sprite object
            Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);

            if (sprite instanceof Pushable){
                ((Pushable) sprite).push(dir);
            }
            else if ((sprite instanceof Cracked) || (sprite instanceof Wall)) {
                direction = -1*(direction);
            }

            else if (sprite instanceof Door){
                if (!(World.checkSwitch())){
                    direction = -1*(direction);
                }
            }
            moveToDest(dir);

        }
    }

    public static boolean isOnMove() {
        return onMove;
    }

    public static void setOnMove(boolean onMove) {
        Rogue.onMove = onMove;
    }
}

