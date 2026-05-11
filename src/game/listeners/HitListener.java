package game.listeners;

import game.objects.Ball;
import game.objects.Block;

/**
 * Interface for objects that want to be notified when a {@link Block} is hit by a {@link Ball}.
 * Implementers of this interface can register to receive hit events
 * and define custom behavior that occurs when a block is hit.
 */
public interface HitListener {
    /**
     * Called whenever a {@link Block} is hit by a {@link Ball}.
     *
     * @param beingHit the {@link Block} that was hit
     * @param hitter the {@link Ball} that caused the hit event
     */
    void hitEvent(Block beingHit, Ball hitter);
}
