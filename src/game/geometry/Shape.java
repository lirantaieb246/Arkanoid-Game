package game.geometry;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents an abstract shape with a color. This class provides common functionality for all shapes,
 * such as setting and getting the color of the shape, as well as defining the abstract method for drawing the shape.
 *
 * <p>Subclasses of this class will implement specific shape types (e.g., rectangles, circles) and provide
 * their own implementation of the {@link #drawOn(DrawSurface)} method to render the
 * shape on a given {@link DrawSurface}.
 */
public abstract class Shape implements IShape {
    /** The color of the shape. */
    private Color color;

    /**
     * Constructs a new shape with the specified color.
     *
     * @param color The color of the shape.
     */
    public Shape(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of the shape.
     *
     * @return The color of the shape.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the shape to the specified color.
     *
     * @param color The new color for the shape.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the shape on the given {@link DrawSurface}.
     * This method must be implemented by subclasses to define the shape-specific drawing behavior.
     *
     * @param d The {@link DrawSurface} on which the shape will be drawn.
     */
    public abstract void drawOn(DrawSurface d);
}
