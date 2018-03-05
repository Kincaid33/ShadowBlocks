import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

public class World {

    /** Arraylist to store all the sprite objects created */
    private static ArrayList<Sprite> sprites;
    /** Store the number of moves made by the player */
    private int moveCount;

    /**
     * Constructor for the world class
     */
    public World() {
        sprites = Loader.loadSprites(App.level);
    }

    /**
     * create the appropriate sprite given a name and location.
     *
     * @param name the name of the sprite
     * @param x    the x POSITION
     * @param y    the y position
     * @return the sprite object
     */

    public static Sprite createSprite(String name, float x, float y) {

        switch (name) {
            case "wall":
                return new Wall(x, y, "wall");
            case "cracked":
                return new Cracked(x, y, "cracked");
            case "floor":
                return new Floor(x, y, "floor");
            case "target":
                return new Target(x, y, "target");
            case "switch":
                return new Switch(x, y, "switch");
            case "stone":
                return new Stone(x, y, "stone");
            case "ice":
                return new Ice(x, y, "ice");
            case "tnt":
                return new TNT(x, y, "tnt");
            case "explosion":
                return new Explosion(x, y, "explosion");
            case "skeleton":
                return new Skeleton(x, y, "skeleton");
            case "rogue":
                return new Rogue(x, y, "rogue");
            case "mage":
                return new Mage(x, y, "mage");
            case "door":
                return new Door(x, y, "door");
            case "player":
                return new Player(x, y, "player");
        }
        return null;
    }

    /**
     * converts a world coordinate to a tile coordinate
     * and returns if that location is a blocked tile
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isBlocked(float x, float y) {
        x -= Loader.getOffset_x();
        x /= App.TILE_SIZE;
        y -= Loader.getOffset_y();
        y /= App.TILE_SIZE;

        // Rounding is important here
        x = Math.round(x);
        y = Math.round(y);

        // Do bounds checking!
        if (Loader.inBounds((int) x, (int) y)) {
            return Loader.getSpriteOfType("wall", (int) x, (int) y);
        }
        // Default to blocked
        return true;
    }

    /**
     * Update all the sprites
     * @param input
     * @param delta
     * @throws SlickException
     */
    public void update(Input input, int delta) throws SlickException {

        for (Sprite sprite :
                sprites) {
            if (sprite != null) {
                sprite.update(delta);
            }
        }

        int dir = Sprite.DIR_NONE;

        /** Get the key presses*/
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            dir = Sprite.DIR_LEFT;
            Rogue.setOnMove(true);
            moveCount++;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            dir = Sprite.DIR_RIGHT;
            Rogue.setOnMove(true);
            moveCount++;
        } else if (input.isKeyPressed(Input.KEY_UP)) {
            dir = Sprite.DIR_UP;
            Rogue.setOnMove(true);
            moveCount++;
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            dir = Sprite.DIR_DOWN;
            Rogue.setOnMove(true);
            moveCount++;
        }

        if (TNT.isBlowUp()) {
            explode(Cracked.getCrackedX(), Cracked.getCrackedY());
            destroySprite("cracked");
            destroySprite("tnt");
            TNT.setBlowUp(false);
        }

        Player.setDir(dir);

    }

    /**
     * Render all the sprites
     * @param g
     */
    public void render(Graphics g) {
        for (Sprite sprite :
                sprites) {
            if (sprite != null) {
                sprite.render(g);
            }
        }

        /** Print out the number of moves */
        g.drawString("Number of moves: " + Integer.toString(moveCount), 10.0f, 10.0f);
    }

    /**
     * Get the sprite of type given the coordinates
     * @param x
     * @param y
     * @return
     */
    public static Sprite getSpriteOfType(float x, float y) {
        for (Sprite sprite :
                sprites) {
            if (sprite.getX() == x && sprite.getY() == y && !(sprite.getTag().equals("floor")) && !(sprite.getTag().equals("target")) && !(sprite.getTag().equals("switch"))) {
                return sprite;
            }
        }
        return null;
    }

    /**
     * Check if all the targets are active or not
     * @return
     */
    public static boolean checkActive() {
        for (Sprite sprite :
                sprites) {
            if ((sprite instanceof Target) && !((Target) sprite).isActivated()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the switch is active or not
     * @return
     */
    public static boolean checkSwitch() {
        for (Sprite sprite :
                sprites) {
            if ((sprite instanceof Switch) && !((Switch) sprite).isActivated()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create the explosion
     * @param x
     * @param y
     */
    public static void explode(float x, float y) {
        sprites.add(createSprite("explosion", x, y));
    }

    /**
     * Destroy the sprite
     * @param tag
     */
    public static void destroySprite(String tag) {
        sprites.removeIf(sprite -> sprite.getTag().equals(tag));
    }

}
