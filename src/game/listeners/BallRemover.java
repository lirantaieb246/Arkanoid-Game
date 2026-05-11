package game.listeners;

import game.objects.Ball;
import game.objects.Block;
import game.sprites.Counter;
import game.sprites.Game;

/**
 * The {@code BallRemover} class is a listener that removes balls from the game
 * when they hit a designated block (typically the bottom border).
 * It also updates the counter tracking the number of remaining balls.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructs a {@code BallRemover} with the given game and ball counter.
     *
     * @param game the game instance to remove the ball from
     * @param remainingBalls the counter tracking the number of balls remaining in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called when a ball hits the designated block (usually the bottom border).
     * It removes the ball from the game and decreases the remaining balls counter by 1.
     *
     * @param beingHit the block that was hit (usually the bottom border)
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
