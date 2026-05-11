package game.sprites;

import game.collision.Collidable;
import game.geometry.Point;
import game.geometry.Rectangle;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.objects.Screen;
import game.objects.ScoreIndicator;
import game.objects.Ball;
import game.objects.Block;
import game.objects.Velocity;
import game.objects.Paddle;


import java.awt.Color;
import java.util.Random;


/**
 * The Game.Sprites.Game class is responsible for initializing the game,
 * adding all sprites and collidables, and running the main animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment env;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        env.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Sets the GUI used by the game.
     *
     * @param gui the GUI to set
     */
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    /**
     * Initializes the game: sets up the GUI, environment, sprites, balls, blocks, and paddle.
     */
    public void initialize() {
        GUI gui = new GUI("game", Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);
        this.setGUI(gui);

        sprites = new SpriteCollection();
        env = new GameEnvironment();

        score = new Counter();
        ScoreIndicator scoreIndicator =  new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        remainingBalls = new Counter();
        remainingBalls.increase(3);
        BallRemover br = new BallRemover(this, remainingBalls);

        // Create and add two balls with random directions
        Random random = new Random();
        double angle1 = random.nextDouble(1, 360);
        double angle2 = random.nextDouble(1, 360);
        double angle3 = random.nextDouble(1, 360);
        Velocity v1 = Velocity.fromAngleAndSpeed(angle1, 5);
        Velocity v2 = Velocity.fromAngleAndSpeed(angle2, 5);
        Velocity v3 = Velocity.fromAngleAndSpeed(angle3, 5);


        Ball ball1 = new Ball(500, 450, 5, Color.WHITE, v1, env);
        ball1.addToGame(this);
        Ball ball2 = new Ball(500, 450, 5, Color.WHITE, v2, env);
        ball2.addToGame(this);
        Ball ball3 = new Ball(500, 450, 5, Color.WHITE, v3, env);
        ball3.addToGame(this);

        // Create and add blocks in rows
        int blockWidth = 50;
        int blockHeight = 20;
        Color color;

        // Game.Sprites.Counter that counts the amount of blocks that remain in the game
        remainingBlocks = new Counter();
        remainingBlocks.increase(57);

        BlockRemover bl = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener stl = new ScoreTrackingListener(score);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                int blocksInRow = 12 - i;
                int rowWidth = blocksInRow * blockWidth;
                int startX = (Screen.SCREEN_WIDTH - rowWidth) / 2;
                int x = startX + j * blockWidth;
                int y = 100 + i * blockHeight;

                if (i == 0) {
                    color = Color.WHITE;
                } else if (i == 1) {
                    color = Color.RED;
                } else if (i == 2) {
                    color = Color.YELLOW;
                } else if (i == 3) {
                    color = Color.CYAN;
                } else if (i == 4) {
                    color = Color.PINK;
                } else {
                    color = Color.GREEN;
                }

                Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight, color));
                block.addHitListener(bl);
                block.addHitListener(stl);
                block.addToGame(this);
            }
        }

        // Add the paddle
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle paddleRect = new Rectangle(new Point(350, 560), 150, 20, Color.ORANGE);
        Paddle paddle = new Paddle(paddleRect, keyboard);
        paddle.addToGame(this);

        // Add borders
        Block topBorder = new Block(new Rectangle(new Point(20, 20),
                Screen.SCREEN_WIDTH, 20, Color.GRAY));
        Block rightBorder = new Block(new Rectangle(new Point(Screen.SCREEN_WIDTH - 20, 20), 20,
                Screen.SCREEN_HEIGHT, Color.GRAY));
        Block leftBorder = new Block(new Rectangle(new Point(0, 20), 20,
                Screen.SCREEN_HEIGHT, Color.GRAY));
        Block bottomBorder = new Block(new Rectangle(new Point(0, Screen.SCREEN_HEIGHT - 20),
                Screen.SCREEN_WIDTH, 20, Color.GRAY));
        bottomBorder.addHitListener(br);

        topBorder.addToGame(this);
        rightBorder.addToGame(this);
        bottomBorder.addToGame(this);
        leftBorder.addToGame(this);
    }

    /**
     * Starts the main animation loop that runs the game.
     * It handles drawing all sprites and updating their state every frame.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        while (remainingBlocks.getValue() > 0 && remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, Screen.SCREEN_WIDTH, Screen.SCREEN_HEIGHT);

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            System.out.println("You Win!");
        } else {
            System.out.println("Game over.");
        }
        System.out.println("Your score is: " + score.getValue());
        gui.close();
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        env.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
