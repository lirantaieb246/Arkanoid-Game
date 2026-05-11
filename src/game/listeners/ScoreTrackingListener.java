package game.listeners;

import game.objects.Ball;
import game.objects.Block;
import game.sprites.Counter;

/**
 * The {@code ScoreTrackingListener} class is responsible for updating the score
 * when a block is hit by a ball. Each hit increases the score by a fixed value.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a {@code ScoreTrackingListener} with the given score counter.
     *
     * @param scoreCounter the counter to be updated when blocks are hit
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever a block is hit.
     * It increases the score by 5 points.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
