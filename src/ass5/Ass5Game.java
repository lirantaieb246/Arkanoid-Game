package ass5;

import game.sprites.Game;

/**
 * The Ass5Game class contains the main method that initializes and runs the game.
 * It creates an instance of the Game.Sprites.Game class, initializes it, and starts the game loop.
 */

public class Ass5Game {

    /**
     * The main method that serves as the entry point of the game application.
     * It creates an instance of the Game.Sprites.Game class, initializes the game, and starts the main game loop.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
