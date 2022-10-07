package org.peAccounting.view;

import org.peAccounting.controller.Controller;
import org.peAccounting.dto.GenerationDTO;

/**
 * The MainView that starts the Game.
 */
public class MainView {

    private Controller controller;
    private GameFrame gameFrame;
    public MainView(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the interface of the game.
     * @param numberOfGenerations the number of the generated generations
     * @throws InterruptedException throws an Exception white using Thread.sleep().
     */
    public void startInterface(int numberOfGenerations) throws InterruptedException {
        this.controller.startGame();
        this.gameFrame = new GameFrame(500, 500);
        GenerationDTO firstGenerationDTO = this.controller.getCurrentGeneration();
        this.gameFrame.initializeGameFrame(firstGenerationDTO);
        this.gameFrame.repaint();
        playGame(numberOfGenerations);
    }

    /**
     * Continue the game by producing the next generation using to the current generation according
     * to the game rules.
     * @param numberOfGenerations the number of produced generations
     * @throws InterruptedException Throwing an exception while calling Thread.sleep()
     */
    private void playGame(int numberOfGenerations) throws InterruptedException {
        for (int generationVersion = 0; generationVersion < numberOfGenerations; generationVersion++) {
            Thread.sleep(1000);
            this.controller.nextGeneration();
            GenerationDTO generationDTO = this.controller.getCurrentGeneration();
            this.gameFrame.updateComponents(generationDTO);
            this.gameFrame.repaint();
        }
    }
}
