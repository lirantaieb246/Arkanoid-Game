package game.collision;

import game.geometry.Point;
import game.geometry.Rectangle;
import game.objects.Ball;
import game.objects.Velocity;

/**
 * The Game.Sprites.Game.Collision.Collidable interface represents objects that can be collided with.
 * It provides the necessary methods to determine collision geometry and respond to collisions.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object — a rectangle that defines the boundaries for collision.
     *
     * @return a {@link Rectangle} representing the object's collision boundaries.
     */
    Rectangle getCollisonRectangle();

    /**
     * Notifies the block that it has been hit by a ball at a specific point with a given velocity.
     * The block responds by calculating and returning a new velocity based on the side of the block
     * that was hit. If the ball's color does not match the block's, hit listeners are also notified.
     *
     * @param collisionPoint  the point at which the collision occurred.
     * @param currentVelocity the velocity of the ball before the collision.
     * @param hitter          the ball that hit the block.
     * @return the new velocity of the ball after the collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);

    /**
     * Returns the type of the collidable object.
     * The type can be used to distinguish between different kinds of collidable objects (e.g., paddle, wall, etc.).
     *
     * @return a string representing the type of the collidable object.
     */
    String getType();
}
