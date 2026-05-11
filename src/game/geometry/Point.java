package game.geometry;

import biuoop.DrawSurface;
import game.collision.Collidable;

/**
 * Represents a 2D point in coordinates
 * This class provides method for calculating the distance between two points and checking if two points are equals.
 */
public class Point implements IShape {

    /** The x - coordinate of the point. */
    private final double x;

    /** The y - coordinate of the point. */
    private final double y;

    /**
     * Constructs a new point with the given x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point to which the distance is calculated.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX())) + (this.y - other.getY())
                * (this.y - other.getY()));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The point to compare with this point.
     * @return {@code true} if the points have the same coordinates, {@code false} otherwise.
     */
    public boolean equals(Point other) {
        return ((other.getX() == this.x) && (other.getY() == this.y));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Draws this point on the given {@link DrawSurface} as a small circle with a radius of 3.
     *
     * @param d The {@link DrawSurface} on which the point will be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawCircle((int) x, (int) y, 3);
    }

    /**
     * Prints the coordinates of this point in the format (x, y).
     */
    public void printPoint() {
        System.out.println("(" + x + ", " + y + ")");
    }

    /**
     * Determines whether the center of the ball is currently inside the given collidable's collision rectangle.
     *
     * @param c the collidable to check against.
     * @return true if the ball's center is within the bounds of the collidable's rectangle; false otherwise.
     */
    public boolean isInsideCollidable(Collidable c) {
        Rectangle rect = c.getCollisonRectangle();

        double rectLeft = rect.getUpperLeft().getX();
        double rectRight = rectLeft + rect.getWidth();
        double rectTop = rect.getUpperLeft().getY();
        double rectBottom = rectTop + rect.getHeight();

        return this.getX() >= rectLeft
                && this.getX() <= rectRight
                && this.getY() >= rectTop
                && this.getY() <= rectBottom;
    }
}
