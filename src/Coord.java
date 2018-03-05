/**
 * Helper class to convert the conver the coordinates
 * From tile coordinates to world coordinates
 */
public class Coord {

    public static float x;
    public static float y;
    public static float checkX;
    public static float checkY;

    /**
     * @return
     */
    public static float getX() {

        x -= Loader.getOffset_x();
        x /= App.TILE_SIZE;

        // Rounding is important here
        x = Math.round(x);
        return x;
    }

    public static float getY() {

        y -= Loader.getOffset_y();
        y /= App.TILE_SIZE;

        // Rounding is important here
        y = Math.round(y);
        return y;
    }

    /**
     * Check coordinates for pushable sprites
     * @param dir
     */
    public static void checkCoord(int dir) {

        if (dir == Sprite.DIR_UP){
            checkX = x;
            checkY = y-App.TILE_SIZE;
        }
        else if (dir == Sprite.DIR_DOWN) {
            checkX = x;
            checkY = y+App.TILE_SIZE;
        }
        else if (dir == Sprite.DIR_LEFT) {
            checkX = x-App.TILE_SIZE;
            checkY = y;
        }
        else if (dir == Sprite.DIR_RIGHT) {
            checkX = x+App.TILE_SIZE;
            checkY = y;
        }
    }

}

