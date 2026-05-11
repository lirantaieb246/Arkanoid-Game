package game.objects;

import biuoop.DrawSurface;
import game.sprites.Counter;
import game.sprites.Game;
import game.sprites.Sprite;
import java.awt.Color;

/**
 * A {@link Sprite} that displays the current score on the screen.
 * The score is shown as text at the top of the screen with a white background.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructs a ScoreIndicator with the given score counter.
     *
     * @param score the {@link Counter} object tracking the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the provided DrawSurface.
     * The background is a white rectangle spanning the screen width,
     * and the current score is displayed in black text centered horizontally.
     *
     * @param d the {@link DrawSurface} to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, Screen.SCREEN_WIDTH, 20);

        d.setColor(Color.BLACK);
        String text = "Score: " + score.getValue();
        d.drawText(Screen.SCREEN_WIDTH / 2 - 40, 17, text, 15);
    }

    /**
     * Called to notify that time has passed.
     * This implementation does not require any action on time passing,
     * so the method is empty.
     */
    @Override
    public void timePassed() {
        // No action needed on time passage for score display
    }

    /**
     * Adds this ScoreIndicator to the given game as a sprite,
     * so it will be drawn and updated as part of the game loop.
     *
     * @param game the {@link Game} to add this score indicator to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
