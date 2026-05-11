package game.collision;

import game.geometry.Point;

/**
 * The Game.Sprites.Game.Collision.CollisionInfo class represents information about a collision event.
 * It stores the point where the collision occurred and the object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructs a Game.Sprites.Game.Collision.CollisionInfo object with the given collision point and
     * collision object.
     *
     * @param collisionPoint the point where the collision occurred.
     * @param collisionObject the object that was involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point where the collision occurred.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the object involved in the collision.
     */

    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}
