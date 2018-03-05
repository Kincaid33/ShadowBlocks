import org.newdawn.slick.*;

/**
 * Class to start the app
 */
public class App extends BasicGame {

    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    /** number of levels available */
    public static final int MAX_LEVEL = 5;
    /** time for which the explosion can be seen */
    public static final int EXPLOSION_PERIOD = 400;
    /** time between each TILE_SIZE movement */
    public static final int ICE_MOVEMENT_TIME = 250;
    /** time between each TILE_SIZE movement */
    public static final int SKELETON_MOVEMENT_TIME = 1000;
    /** current level to load */
    public static int level = 0;

    // declare a World reference variable
    private World world;

    public App() {
        super("Shadow Blocks");
    }

    @Override
    public void init(GameContainer gc)
        throws SlickException
    {
        world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
        throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);

        // Press "Escape" to exit the game
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }

        // Press "R" to exit the game
        else if (input.isKeyPressed(Input.KEY_R)) {
            createNewWorld();
        }

        // On death of player respawn to initial position
        else if (Player.isDeath()){
            createNewWorld();
            Player.setDeath(false);
        }
        // Press "N" for loading next level
        else if (input.isKeyPressed(Input.KEY_N)) {
            if (level < MAX_LEVEL){
                level++;
                createNewWorld();
            }
        }

        // Press "P" for loading previous level
        else if (input.isKeyPressed(Input.KEY_P)) {
            if (level > 0){
                level--;
                createNewWorld();
            }
        }
        createNextWorld();
    }

    /**
     * Create a new world for the next level
     */
    private void createNextWorld() {
        if (World.checkActive() && level < MAX_LEVEL){
            level++;
            createNewWorld();
        }
    }

    /** Delete the old world
     * Create new world
     */
    private void createNewWorld() {
        world = null;
        world = new World();
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */

    public void render(GameContainer gc, Graphics g)
        throws SlickException
    {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     * @throws SlickException
     */

    public static void main(String[] args)
        throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}
