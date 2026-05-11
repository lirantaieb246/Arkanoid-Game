package game.sprites;

import game.collision.Collidable;
import game.collision.CollisionInfo;
import game.geometry.Line;
import game.geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game.Sprites.GameEnvironment class holds a collection of collidable objects.
 * It is used to manage the environment in which objects move and potentially collide with others.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs an empty game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the environment.
     *
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the list of collidables currently in the environment.
     *
     * @return a list of collidable objects.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Determines the closest collision that will occur along the given trajectory,
     * if any, with the collidables in the environment.
     *
     * @param trajectory the path an object will move along.
     * @return the Game.Sprites.Game.Collision.CollisionInfo object that holds the closest collision
     * point and the involved collidable, or null if no collision is detected.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }

        Point closestPoint = null;
        Collidable closestCollidable = null;
        double minDistance = 10000;

        for (Collidable c : collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisonRectangle());

            if (intersection != null) {
                double distance = trajectory.start().distance(intersection);
                if (distance < minDistance) {
                    if (c.getType().equals("Game.Objects.Paddle") && trajectory.start().isInsideCollidable(c)) {
                        continue;
                    }
                    minDistance = distance;
                    closestPoint = intersection;
                    closestCollidable = c;
                }
            }
        }

        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }

    /**
     * Removes the specified {@link Collidable} object from the game environment.
     * If the given collidable is not present, this method has no effect.
     *
     * @param c the {@link Collidable} object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

}
