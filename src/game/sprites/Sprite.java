package game.sprites;

import biuoop.DrawSurface;

/**
 * The Game.Sprites.Sprite interface represents an object that can be drawn on the screen and notified that
 * time has passed.
 * Typically used for game elements that change over time and need to be rendered on a {@link DrawSurface}.
 */
public interface Sprite {
    /**
     * Draws the sprite to the screen using the provided {@link DrawSurface}.
     *
     * @param d the surface on which the sprite should be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     * This method is typically called once per frame or time unit.
     */
    void timePassed();


}
