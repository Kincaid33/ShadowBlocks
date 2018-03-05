/**
 * Class for the sprite player which is a subclass of Moveable
 */

public class Player extends Moveable {


    /** instance variable to contain the direction of the player movement */
    private static int dir;
    /** instance variable to store the decision of death for the player */
    private static boolean death = false;

    /**
     * Constructor of Player class
     * @param x x coordinate of the sprite
     * @param y y coordinate of the sprite
     * @param tag name of the sprite
     */
    public Player(float x, float y, String tag) {
        super("res/player_left.png", x, y, tag);
    }

    @Override
    public void update(int delta) {

        // send the coordinates for checking
        Coord.x = getX();
        Coord.y = getY();

        // check the coordinates for this particular direction
        Coord.checkCoord(dir);

        // create a sprite object
        Sprite sprite = World.getSpriteOfType(Coord.checkX, Coord.checkY);
        Sprite killable = World.getSpriteOfType(getX(), getY());

        if (sprite instanceof Cracked){
            dir = DIR_NONE;
        }

        if (sprite instanceof Door){
            if (!(World.checkSwitch())){
                dir =DIR_NONE;
            }
        }

        if(sprite instanceof Pushable) {
            ((Pushable) sprite).push(dir); // if sprite is of superclass pushable then push the sprite
            if (sprite instanceof Ice){
                ((Ice) sprite).setActive(true);
                ((Ice) sprite).icePush(dir); // push the ice
            }
        }

        // sprites those kill the player
        if((killable instanceof Skeleton) || (killable instanceof Mage) || (killable instanceof Rogue)){
            death = true;
        }

        moveToDest(dir); // move the player to its destination
        Rogue.setOnMove(false); // to trigger the movement of the rogue
    }

    /**
     * Getters and Setters
     *
     */
    public static void setDir(int dir) {
        Player.dir = dir;
    }

    public static boolean isDeath() {
        return death;
    }

    public static void setDeath(boolean death) {
        Player.death = death;
    }

    public static int getDir() {
        return dir;
    }
}
