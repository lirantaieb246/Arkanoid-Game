package game.objects;

import game.collision.Collidable;
import game.collision.CollisionInfo;
import game.geometry.IShape;
import game.geometry.Line;
import game.geometry.Point;
import biuoop.DrawSurface;
import game.sprites.Game;
import game.sprites.GameEnvironment;
import game.sprites.Sprite;
import java.awt.Color;


/**
 * The Game.Objects.Ball class represents a bouncing ball in a 2D space.
 * It can draw itself, move according to velocity, and bounce off walls or obstacles.
 */
public class Ball implements IShape, Sprite {
    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment env;

    /**
     * Constructs a Game.Objects.Ball with the given center, radius, color, velocity, and game environment.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     * @param v      the velocity of the ball.
     * @param env    the game environment that contains obstacles for collision detection.
     */

    public Ball(Point center, int r, java.awt.Color color, Velocity v, GameEnvironment env) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
        this.env = env;
    }

    /**
     * Constructs a Game.Objects.Ball with the given x, y coordinates, radius, color, velocity, and game environment.
     *
     * @param x     the x coordinate of the center.
     * @param y     the y coordinate of the center.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param v     the velocity of the ball.
     * @param env   the game environment that contains obstacles for collision detection.
     */
    public Ball(double x, double y, int r, java.awt.Color color, Velocity v, GameEnvironment env) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = v;
        this.env = env;
    }

    /**
     * Constructs a Game.Objects.Ball with no initial velocity.
     *
     * @param x     the x coordinate of the center.
     * @param y     the y coordinate of the center.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param env   the game environment that contains obstacles for collision detection.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment env) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.env = env;
    }

    /**
     * Constructs a Game.Objects.Ball with the given x, y coordinates, radius, color, and velocity.
     *
     * @param x     the x coordinate of the center.
     * @param y     the y coordinate of the center.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param v     the velocity of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, Velocity v) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = v;
    }


    /**
     * @return the x coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the ball.
     */
    public int getRadius() {
        return this.r;
    }

    /**
     * @return an approximation of the ball's size (area).
     */
    public int getSize() {
        return (int) (Math.pow(this.r, 2) * Math.PI);
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle((int) center.getX(), (int) center.getY(), r);

        d.setColor(Color.BLACK);
        d.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * Sets the ball's velocity.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the ball's velocity from dx and dy.
     *
     * @param dx change in x per step.
     * @param dy change in y per step.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the current velocity of the ball.
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * Prints ball's location, radius, and color to the console.
     */
    public void printBall() {
        System.out.println("Center: (" + (int) center.getX() + ", " + (int) center.getY() + ")");
        System.out.println("Radius: " + r);
        System.out.println("Color: " + color);
    }

    /**
     * Sets the game environment for the ball.
     *
     * @param env the game environment that contains obstacles for collision detection.
     */
    public void setGameEnvironment(GameEnvironment env) {
        this.env = env;
    }

    /**
     * @return the current game environment for the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.env;
    }

    /**
     * Moves the ball one step along its velocity vector.
     * If a collision is detected, the ball bounces off the obstacle.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, center.getX() + v.getDX(), center.getY() + v.getDY());
        CollisionInfo info = env.getClosestCollision(trajectory);

        if (info == null) {
            center = trajectory.end();
        } else {
            Collidable c = info.getCollisionObject();
            Point collisionPoint = info.getCollisionPoint();
            double dx = v.getDX();
            double dy = v.getDY();
            double epsilon = 1;


            this.center = new Point(
                        collisionPoint.getX() - dx * epsilon,
                        collisionPoint.getY() - dy * epsilon
                );

            this.v = c.hit(collisionPoint, this.v, this);
        }
    }

    /**
     * This method is called each time a frame passes in the game, making the ball move.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the game.
     *
     * @param g the game to add the ball to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the {@link Color} to set for the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }
}