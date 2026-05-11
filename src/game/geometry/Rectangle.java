package game.geometry;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 2D rectangle in a coordinate plane. This class provides methods for
 * drawing the rectangle, retrieving its properties, and working with its coordinates.
 */
public class Rectangle implements IShape {
    private Point upperLeft;
    private final double width;
    private final double height;
    private final java.awt.Color color;

    /**
     * Constructs a new rectangle with the given top-left point, width, height, and color.
     *
     * @param upperLeft The top-left corner point of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Constructs a new rectangle with the given x and y coordinates for the top-left
     * corner, width, height, and color.
     *
     * @param x1 The x-coordinate of the top-left corner of the rectangle.
     * @param y1 The y-coordinate of the top-left corner of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(double x1, double y1, double width, double height, java.awt.Color color) {
        this.upperLeft = new Point(x1, y1);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Draws the rectangle on the provided {@link DrawSurface}.
     * The rectangle will be filled with the color set during construction.
     *
     * @param d The {@link DrawSurface} on which the rectangle will be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

    /**
     * Returns the top-left corner of the rectangle.
     *
     * @return The {@link Point} representing the top-left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the bottom-right corner of the rectangle.
     * The bottom-right corner is calculated by adding the width and height to the
     * top-left corner's x and y coordinates, respectively.
     *
     * @return The {@link Point} representing the bottom-right corner of the rectangle.
     */
    public Point getBottomRight() {
        return new Point(this.getUpperLeft().getX() + width, this.getUpperLeft().getY() + height);
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the x-coordinate of the top-left corner of the rectangle.
     *
     * @return The x-coordinate of the top-left corner.
     */
    public double getX() {
        return upperLeft.getX();
    }

    /**
     * Returns the y-coordinate of the top-left corner of the rectangle.
     *
     * @return The y-coordinate of the top-left corner.
     */
    public double getY() {
        return upperLeft.getY();
    }

    /**
     * Returns the left side of the rectangle as a line.
     *
     * @return A {@link Line} representing the left side of the rectangle.
     */
    public Line getLeftSide() {
        return new Line(upperLeft, upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Returns the right side of the rectangle as a line.
     *
     * @return A {@link Line} representing the right side of the rectangle.
     */
    public Line getRightSide() {
        return new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + height);
    }

    /**
     * Returns the top side of the rectangle as a line.
     *
     * @return A {@link Line} representing the top side of the rectangle.
     */
    public Line getTopSide() {
        return new Line(upperLeft, upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Returns the bottom side of the rectangle as a line.
     *
     * @return A {@link Line} representing the bottom side of the rectangle.
     */
    public Line getBottomSide() {
        return new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line.
     *
     * @param line The {@link Line} to check for intersections.
     * @return A list of {@link Point}s where the line intersects the rectangle's sides.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line leftSide = this.getLeftSide();
        Line topSide = this.getTopSide();
        Line rightSide = this.getRightSide();
        Line bottomSide = this.getBottomSide();

        List<Point> pointList = new ArrayList<>();

        Point[] points = new Point[4];
        points[0] = leftSide.intersectionWith(line);
        points[1] = topSide.intersectionWith(line);
        points[2] = rightSide.intersectionWith(line);
        points[3] = bottomSide.intersectionWith(line);

        for (Point p : points) {
            if (p != null) {
                pointList.add(p);
            }
        }

        return pointList;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return The {@link java.awt.Color} of the rectangle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets a new top-left point for the rectangle.
     *
     * @param p The new {@link Point} to be set as the top-left corner.
     */
    public void setUpperLeft(Point p) {
        upperLeft = p;
    }
}
