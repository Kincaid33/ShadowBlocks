import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

    private static String[][] types;

    private static int world_width;
    private static int world_height;
    private static int offset_x;
    private static int offset_y;

    /**
     * Loads the sprites from a given file.
     * @param level
     * @return
     */

    public static ArrayList<Sprite> loadSprites(int level) {

        String filename = "res/levels/"+level+".lvl";
        ArrayList<Sprite> list = new ArrayList<>();

//        open the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

//        find the world size
            line = reader.readLine(); // read the first line
            String[] parts = line.split(","); // divide the line using the comma and store them into an array of strings
            world_width = Integer.parseInt(parts[0]); // convert the first string to int and get the width of the map
            world_height = Integer.parseInt(parts[1]); // convert the second string to int and get the height of the map

//            create the array of object types
            types = new String[world_width][world_height]; // store the name of the sprites in a 2D array of strings according to the map coordinates

//            calculate the top left of the tiles so that the level is centered
            offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2; // calculate the distance for the x coordinates
            offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2; // calculate the distance for the y coordinates

//            loop over every line of the file
            while ((line = reader.readLine()) != null) {
                String name;
                float x, y;

                // Split the line into parts
                parts = line.split(","); // divide the line using the comma and store them into an array of strings
                name = parts[0]; // name of the sprite
                x = Integer.parseInt(parts[1]); // x-coordinate on the map
                y = Integer.parseInt(parts[2]); // y-coordinate of the map

                types[(int) x][(int) y] = name; // create a 2D array and store all the name of the sprite accordingly

                x = offset_x + x * App.TILE_SIZE;
                y = offset_y + y * App.TILE_SIZE;

//                create the sprite
                list.add(World.createSprite(name, x, y)); // add each sprite created into the arraylist so that it lives in the heap
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * To check if a sprite is with the bounds set by the map
     * @param tx
     * @param ty
     * @return
     */
    public static boolean inBounds(int tx, int ty) {
        if (tx >= 0 && tx < world_width && ty >= 0 && ty < world_height) {
            return true;
        }
        return false;
    }

    /**
     * Getter and Setters
     * @return
     */
    public static int getOffset_x() {
        return offset_x;
    }

    public static int getOffset_y() {
        return offset_y;
    }

    public static boolean getSpriteOfType(String name, int x, int y){
        return types[(int)x][(int)y].equals(name);
    }

}
