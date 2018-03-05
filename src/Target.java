/**
 * Class for the target sprite
 */
public class Target extends Sprite {

    public Target(float x, float y, String tag) {
        super("res/target.png", x, y, tag);
    }

    /** to check if this particular target is activated or not */
    private boolean activated;

    @Override
    public void update(int dir) {
        Sprite sprite = World.getSpriteOfType(getX(), getY());

        if (sprite instanceof Pushable) {
            activated = true;
        } else {
            activated = false;
        }
    }

    public boolean isActivated() {
        return activated;
    }
}
