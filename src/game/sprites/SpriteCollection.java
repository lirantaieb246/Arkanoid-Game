package game.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A Game.Sprites.SpriteCollection is a container that holds and manages a list of Game.Sprites.Sprite objects.
 * It can notify all sprites that time has passed and ask them to draw themselves.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructs a new, empty Game.Sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls timePassed() on all sprites in the collection,
     * allowing them to update their state as needed.
     */
    public void notifyAllTimePassed() {
        if (sprites.isEmpty()) {
            return;
        }

        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection,
     * rendering them on the provided DrawSurface.
     *
     * @param d the drawing surface where sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        if (sprites.isEmpty()) {
            return;
        }

        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite s : copy) {
            s.drawOn(d);
        }
    }

    /**
     * Removes the specified {@link Sprite} from the collection of sprites.
     * If the sprite is not in the collection, this method does nothing.
     *
     * @param s the {@link Sprite} to be removed
     */

    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
