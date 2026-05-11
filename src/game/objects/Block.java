package game.objects;

import game.collision.Collidable;
import game.geometry.Point;
import game.geometry.Rectangle;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import biuoop.DrawSurface;
import game.sprites.Game;
import game.sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game.Objects.Block class represents a rectangular block in the game.
 * Blocks can be drawn on the screen, detect collisions, and respond to hits by changing the velocity of
 * colliding objects.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rect;
    private final List<HitListener> hitListeners;

    /**
     * Constructs a block with the specified rectangle as its shape and position.
     *
     * @param rect the rectangle representing the block's geometry.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of this block.
     *
     * @return the rectangle representing this block's collision area.
     */
    @Override
    public Rectangle getCollisonRectangle() {
        return this.rect;
    }

    /**
     * Handles the logic when a collision with this block occurs.
     * Depending on the side of impact, this method changes the direction of the velocity.
     *
     * @param collisionPoint  the point at which the collision occurred.
     * @param currentVelocity the velocity of the object before the collision.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();

        double epsilon = 1e-6;

        double left = rect.getUpperLeft().getX();
        double right = left + rect.getWidth();
        double top = rect.getUpperLeft().getY();
        double bottom = top + rect.getHeight();

        boolean hitTop = collisionPoint.getY() <= top + epsilon
                && collisionPoint.getX() >= left - epsilon
                && collisionPoint.getX() <= right + epsilon;

        boolean hitBottom = collisionPoint.getY() >= bottom - epsilon
                && collisionPoint.getX() >= left - epsilon
                && collisionPoint.getX() <= right + epsilon;

        boolean hitRight = collisionPoint.getX() >= right - epsilon
                && collisionPoint.getY() >= top - epsilon
                && collisionPoint.getY() <= bottom + epsilon;

        boolean hitLeft = collisionPoint.getX() <= left + epsilon
                && collisionPoint.getY() >= top - epsilon
                && collisionPoint.getY() <= bottom + epsilon;

        if (hitTop || hitBottom) {
            dy = -dy;
        }

        if (hitRight || hitLeft) {
            dx = -dx;
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Draws the block onto the given DrawSurface.
     * Fills the rectangle with the block's color and outlines it in black.
     *
     * @param d the DrawSurface to draw the block on.
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
     * Called once per frame. Blocks currently have no behavior that changes over time.
     */
    @Override
    public void timePassed() {
        // No behavior for now
    }

    /**
     * Adds this block to the game as both a sprite and a collidable object.
     *
     * @param g the game to add the block to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the type of the collidable object.
     * In this case, it specifically returns the string "Game.Objects.Block" to indicate the object is a block.
     *
     * @return the type of the object as a string, which is "Game.Objects.Block" for this implementation.
     */
    @Override
    public String getType() {
        return "Game.Objects.Block";
    }

    /**
     * Checks whether the given ball has the same color as this block.
     *
     * @param ball the ball to compare with
     * @return true if colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
         return ball.getColor().equals(rect.getColor());
    }

    /**
     * Removes this block from the game.
     *
     * @param game the game from which the block should be removed
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered listeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Registers a new hit listener to this block.
     *
     * @param hl the listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from this block.
     *
     * @param hl the listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Returns the color of the block.
     *
     * @return the color used to fill the block
     */
    public Color getColor() {
        return rect.getColor();
    }
}
