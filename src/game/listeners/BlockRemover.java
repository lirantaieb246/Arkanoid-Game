package game.listeners;

import game.objects.Ball;
import game.objects.Block;
import game.sprites.Counter;
import game.sprites.Game;

/**
 * The {@code BlockRemover} class is a listener that removes blocks from the game
 * once they are hit. It also updates a counter tracking the remaining blocks.
 * Additionally, it sets the color of the hitting ball to match the block before removal.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a {@code BlockRemover} with the specified game instance and block counter.
     *
     * @param game the game from which blocks will be removed
     * @param remainingBlocks the counter tracking the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever a block is hit.
     * It removes the block from the game, updates the block counter,
     * and sets the color of the ball to the block's color before removal.
     *
     * @param beingHit the block that was hit and will be removed
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}
