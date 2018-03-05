/**
 * Class for the sprite Switch
 */

public class Switch extends Sprite {
    public Switch(float x, float y, String tag) {
        super("res/switch.png", x, y, tag);
    }

    /** decision if the switch is activated or not */
    private boolean activated;

    @Override
    public void update(int dir) {
        Sprite sprite = World.getSpriteOfType(getX(), getY());

        // activate the switch if its pushable sprite
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
