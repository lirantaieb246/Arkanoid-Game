package game.geometry;

import biuoop.DrawSurface;

/**
 * An interface for drawable shapes.
 * Classes that implement this interface should provide a method to draw themselves
 * onto a given {@link DrawSurface}.
 */
public interface IShape {
    /**
     * Draws the shape onto the given DrawSurface.
     *
     * @param d the surface to draw the shape on.
     */
    void drawOn(DrawSurface d);
}
