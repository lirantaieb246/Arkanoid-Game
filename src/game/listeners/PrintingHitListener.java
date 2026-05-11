package game.listeners;

import game.objects.Ball;
import game.objects.Block;

/**
 * The {@code PrintingHitListener} class is a simple implementation of {@link HitListener}
 * that prints a message to the console whenever a block is hit.
 * Useful for debugging or verifying that hit events are working correctly.
 */
public class PrintingHitListener implements HitListener {

    /**
     * Constructs a new {@code PrintingHitListener}.
     */

    public PrintingHitListener() {

    }

    /**
     * Called whenever a block is hit.
     * This implementation prints a message to the console.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A block was hit");
    }
}
