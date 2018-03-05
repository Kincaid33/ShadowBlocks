import org.newdawn.slick.Graphics;

/**
 * Class for the sprite Explosion
 */
public class Explosion extends Sprite {

    private int sinceExplosion= 0;

    public Explosion(float x, float y, String tag) {
        super("res/explosion.png", x, y, tag);
    }

    /**
     * Get delta required to set the time
     * @param delta
     */
    @Override
    public void update(int delta) {
        sinceExplosion += delta;
        super.update(delta);
    }

    /**
     * Use delta to set the time for which the explosion should be displayed
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (sinceExplosion <= App.EXPLOSION_PERIOD){
            super.render(g);
        }
    }
}
