package game.geometry;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a line segment in 2D space defined by two points.
 */
public class Line implements IShape {
    private static final double EPSILON = 1e-10;

    private final Point p1;
    private final Point p2;

    /**
     * Constructs a Game.Sprites.Game.Geometry.Line using two Game.Sprites.Game.Geometry.Point objects.
     *
     * @param p1 The starting point of the line.
     * @param p2 The ending point of the line.
     */
    public Line(Point p1, Point p2) {
        // Points that define the start and end of the line segment.
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Constructs a Game.Sprites.Game.Geometry.Line using four coordinates (x1, y1) and (x2, y2).
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * Constructs a Game.Sprites.Game.Geometry.Line using a Game.Sprites.Game.Geometry.Point and an additional set
     * of coordinates.
     *
     * @param p1 The starting point of the line.
     * @param x The x-coordinate of the ending point.
     * @param y The y-coordinate of the ending point.
     */
    public Line(Point p1, double x, double y) {
        this.p1 = p1;
        this.p2 = new Point(x, y);
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return The length of the line segment as a double.
     */
    public double length() {
        return p1.distance(p2);
    }

    /**
     * Calculates the midpoint of the line segment.
     *
     * @return A Game.Sprites.Game.Geometry.Point representing the midpoint of the line.
     */
    public Point middle() {
        double x = (this.p1.getX() + this.p2.getX()) / 2;
        double y = (this.p1.getY() + this.p2.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return The start point of the line.
     */
    public Point start() {
        return this.p1;
    }

    /**
     * Returns the end point of the line.
     *
     * @return The end point of the line.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * Calculates the orientation of three points (p1, p2, p3) to determine if they are collinear.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param x3 The x-coordinate of the third point.
     * @param y3 The y-coordinate of the third point.
     * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise.
     */
    public int orientation(double x1, double y1, double x2, double y2, double x3, double y3) {
        double val = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
        if (isClose(val, 0)) {
            return 0; // Collinear
        }
        if (val > 0) {
            return 1; // Clockwise
        } else {
            return 2; // Counterclockwise
        }
    }

    /**
     * Checks if a point (x, y) lies on the segment between (x1, y1) and (x2, y2).
     *
     * @param x1 The x-coordinate of the start point of the segment.
     * @param y1 The y-coordinate of the start point of the segment.
     * @param x2 The x-coordinate of the end point of the segment.
     * @param y2 The y-coordinate of the end point of the segment.
     * @param x The x-coordinate of the point to check.
     * @param y The y-coordinate of the point to check.
     * @return true if the point lies on the segment, false otherwise.
     */
    public boolean onSegment(double x1, double y1, double x2, double y2, double x, double y) {
        return (x <= Math.max(x1, x2)  + EPSILON && x >= Math.min(x1, x2) - EPSILON && y <= Math.max(y1, y2) + EPSILON
                && y >= Math.min(y1, y2) - EPSILON);
    }

    /**
     * Checks if two lines intersect.
     * This method uses the orientation technique and checks for collinearity or intersection.
     *
     * @param other The other line to check for intersection with this line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        int o1 = orientation(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), other.start().getX(),
                other.start().getY());
        int o2 = orientation(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), other.end().getX(),
                other.end().getY());
        int o3 = orientation(other.start().getX(), other.start().getY(), other.end().getX(), other.end().getY(),
                this.p1.getX(), this.p1.getY());
        int o4 = orientation(other.start().getX(), other.start().getY(), other.end().getX(), other.end().getY(),
                this.p2.getX(), this.p2.getY());

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special cases: checking for collinearity and overlap
        if (o1 == 0 && onSegment(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), other.start().getX(),
                other.start().getY())) {
            return true;
        }
        if (o2 == 0 && onSegment(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), other.end().getX(),
                other.end().getY())) {
            return true;
        }
        if (o3 == 0 && onSegment(other.start().getX(), other.start().getY(), other.end().getX(), other.end().getY(),
                this.p1.getX(), this.p1.getY())) {
            return true;
        }
        if (o4 == 0 && onSegment(other.start().getX(), other.start().getY(), other.end().getX(), other.end().getY(),
                this.p2.getX(), this.p2.getY())) {
            return true;
        }

        return false;
    }

    /**
     * Checks if three lines intersect with each other.
     *
     * @param other1 The first line to check for intersection.
     * @param other2 The second line to check for intersection.
     * @return true if all three lines intersect with each other, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
         return this.isIntersecting(other1) && this.isIntersecting(other2) && other1.isIntersecting(other2);
    }

    /**
     * Calculates the slope of the line.
     * Returns Double.POSITIVE_INFINITY if the line is vertical.
     *
     * @return The slope of the line.
     */
    public double slope() {
        if (this.isVertical()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.p1.getY() - this.p2.getY()) / (this.p1.getX() - this.p2.getX());
    }

    /**
     * Calculates the y-intercept of the line.
     *
     * @return The y-intercept of the line.
     */
    public double yIntercept() {
        if (this.isVertical()) {
            return Double.NaN;
        }
        return this.p1.getY() - this.slope() * this.p1.getX();
    }

    /**
     * Checks if the line is vertical.
     *
     * @return true if the line is vertical, false otherwise.
     */
    public boolean isVertical() {
        return this.p1.getX() == this.p2.getX();
    }

    /**
     * Finds the intersection point of this line with another line.
     * Returns null if the lines do not intersect.
     *
     * @param other The other line to check for intersection.
     * @return A Game.Sprites.Game.Geometry.Point representing the intersection or null if no intersection.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // Case 1: Both lines are vertical
        if (this.isVertical() && other.isVertical()) {
            if (this.p1.getX() == other.start().getX()) { // Same x-coordinates
                double yMin = Math.max(Math.min(this.p1.getY(), this.p2.getY()), Math.min(other.start().getY(),
                        other.end().getY()));
                double yMax = Math.min(Math.max(this.p1.getY(), this.p2.getY()), Math.max(other.start().getY(),
                        other.end().getY()));

                if (yMin == yMax) { // If there is an overlapping range
                    return new Point(this.p1.getX(), yMin); // Return lowest intersection point
                }
                return null;
            }
            return null; // Parallel vertical lines with no overlap
            // Case 2: One line is vertical
        } else if (this.isVertical()) {
            double x = this.p1.getX();
            double y = other.slope() * x + other.yIntercept();
            return new Point(x, y);

        } else if (other.isVertical()) {
            double x = other.start().getX();
            double y = this.slope() * x + this.yIntercept();
            return new Point(x, y);
        }

        // Case 3: Neither line is vertical
        double m1 = this.slope();
        double m2 = other.slope();
        double b1 = this.yIntercept();
        double b2 = other.yIntercept();

        if (isClose(m1, m2)) {
            return null; // Parallel lines
        }

        double intX = (b2 - b1) / (m1 - m2);
        double intY = m1 * intX + b1;
        Point intersection = new Point(intX, intY);

        // Check if the intersection is within the segment bounds of both lines
        if (onSegment(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), intX, intY)
                && onSegment(other.start().getX(), other.start().getY(), other.end().getX(), other.end().getY(),
                intX, intY)) {
            return intersection;
        }
        return null;
    }

    /**
     * Compares two double values for equality within a small threshold (EPSILON)
     * to account for floating-point precision errors.
     *
     * @param a The first double value.
     * @param b The second double value.
     * @return true if the absolute difference between the values is less than EPSILON, false otherwise.
     */
    public boolean isClose(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * Compares two lines for equality.
     * Two lines are equal if their start and end points are identical in either order.
     *
     * @param other The line to compare this line to.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.p1.equals(other.start()) && this.p2.equals(other.end())) || (this.p1.equals(other.end())
                && this.p2.equals(other.start()));
        }

    /**
     * Draws the line on the given {@link DrawSurface}.
     *
     * @param d the surface on which to draw the line.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    /**
     * Finds the closest intersection point between this line and a rectangle.
     *
     * @param rect The rectangle to check for intersections.
     * @return The closest intersection point to the start of the line, or null if no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = new ArrayList<>();
        pointList = rect.intersectionPoints(this);

        if (pointList.isEmpty()) {
            return null;
        }

        double smallestDistance = 1000000;
        Point closestPoint = new Point(0, 0);

        for (Point p : pointList) {
            if (p != null) {
                if (p.distance(this.start()) < smallestDistance) {
                    smallestDistance = p.distance(this.start());
                    closestPoint = p;
                }
            }
        }

        return closestPoint;
    }
}

