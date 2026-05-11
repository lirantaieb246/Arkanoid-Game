package game.objects;

import game.geometry.Point;

/**
 * Represents the velocity of an object in 2D space.
 * This class stores the change in position (dx, dy) and provides methods for applying
 * the velocity to a point and for creating a velocity from an angle and speed.
 */
public class Velocity {
    /** The change in the x-coordinate (horizontal velocity component). */
    private final double dx;

    /** The change in the y-coordinate (vertical velocity component). */
    private final double dy;

    /**
     * Constructs a new velocity with the specified horizontal and vertical components.
     *
     * @param dx The change in the x-coordinate (horizontal velocity).
     * @param dy The change in the y-coordinate (vertical velocity).
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point, returning a new point that is offset
     * by the velocity components (dx, dy).
     *
     * @param p The point to which the velocity will be applied.
     * @return A new {@link Point} representing the result of applying the velocity to the point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the horizontal component (dx) of the velocity.
     *
     * @return The horizontal change in position (dx).
     */
    public double getDX() {
        return dx;
    }

    /**
     * Returns the vertical component (dy) of the velocity.
     *
     * @return The vertical change in position (dy).
     */
    public double getDY() {
        return dy;
    }

    /**
     * Creates a new {@link Velocity} object from a given angle and speed.
     * The angle is in degrees, with 0 degrees pointing to the right (positive x-axis),
     * and the speed is the magnitude of the velocity.
     *
     * @param angle The angle (in degrees) at which the velocity is directed.
     * @param speed The speed (magnitude) of the velocity.
     * @return A new {@link Velocity} object with the calculated components (dx, dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = -speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Calculates and returns the speed, which is the magnitude of the velocity vector.
     *
     * @return the speed based on dx and dy components.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
