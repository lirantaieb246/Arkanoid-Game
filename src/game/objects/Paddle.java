package game.objects;

import game.collision.Collidable;
import game.geometry.Point;
import game.geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.sprites.Game;
import game.sprites.Sprite;

import java.awt.Color;

/**
 * The Game.Objects.Paddle class represents the player-controlled paddle in the game.
 * It can move left and right in response to keyboard input and also responds to collisions.
 */
public class Paddle implements Sprite, Collidable {
    private final Rectangle rect;
    private final biuoop.KeyboardSensor keyboard;

    /**
     * Constructs a Game.Objects.Paddle with a specified rectangle and keyboard sensor.
     *
     * @param rect     the shape and position of the paddle.
     * @param keyboard the keyboard sensor to detect movement keys.
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard) {
        this.rect = rect;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left by a small step.
     * Wraps around to the right side of the screen if it goes too far left.
     */
    public void moveLeft() {
        double step = 5;
        double newX = rect.getUpperLeft().getX() - step;

        if (newX + rect.getWidth() < 20) {
            newX = Screen.SCREEN_WIDTH + 20;
        }

        rect.setUpperLeft(new Point(newX, rect.getUpperLeft().getY()));
    }

    /**
     * Moves the paddle to the right by a small step.
     * Wraps around to the left side of the screen if it goes too far right.
     */
    public void moveRight() {
        double step = 5;
        double newX = rect.getUpperLeft().getX() + step;

        if (newX > Screen.SCREEN_WIDTH - 20) {
            newX = -rect.getWidth() + 20;
        }

        rect.setUpperLeft(new Point(newX, rect.getUpperLeft().getY()));
    }

    /**
     * Updates the paddle's position based on keyboard input.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given drawing surface.
     *
     * @param d the drawing surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Returns the rectangle representing the paddle's collision area.
     *
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisonRectangle() {
        return this.rect;
    }

    /**
     * Handles the collision logic when a ball hits the paddle.
     * Different regions of the paddle return different bounce angles.
     *
     * @param collisionPoint   the point where the collision occurred.
     * @param currentVelocity  the ball's current velocity.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double paddleX = rect.getUpperLeft().getX();
        double paddleWidth = rect.getWidth();
        double regionWidth = paddleWidth / 5;

        double hitX = collisionPoint.getX();
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();

        if (collisionPoint.getY() <= rect.getUpperLeft().getY() + 1e-6) {
            if (hitX < paddleX + regionWidth) {
                return Velocity.fromAngleAndSpeed(150, currentVelocity.getSpeed());
            } else if (hitX < paddleX + 2 * regionWidth) {
                return Velocity.fromAngleAndSpeed(120, currentVelocity.getSpeed());
            } else if (hitX < paddleX + 3 * regionWidth) {
                return new Velocity(dx, -dy);
            } else if (hitX < paddleX + 4 * regionWidth) {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            } else {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
        }

        return new Velocity(-dx, dy);
    }

    /**
     * Adds the paddle to the game as both a sprite and a collidable object.
     *
     * @param g the game to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the type of the collidable object.
     * In this case, it specifically returns the string "Game.Objects.Paddle" to indicate the object is a paddle.
     *
     * @return the type of the object as a string, which is "Game.Objects.Paddle" for this implementation.
     */
    @Override
    public String getType() {
        return "Game.Objects.Paddle";
    }
}
