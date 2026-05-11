package game.sprites;

/**
 * A simple counter class for tracking an integer value.
 * This class can be used to count scores, lives, or any other
 * incrementing/decrementing quantity in the game.
 */

public class Counter {
    private int value;

    /**
     * Constructs a new Counter initialized to zero.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increases the counter's value by the specified number.
     *
     * @param number the amount to add to the counter
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Decreases the counter's value by the specified number.
     *
     * @param number the amount to subtract from the counter
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the counter's value
     */
    public int getValue() {
        return this.value;
    }
}
