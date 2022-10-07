package org.peAccounting.controller;

import org.peAccounting.dto.Generation;
import org.peAccounting.dto.GenerationDTO;
import org.peAccounting.model.GameOfLife;

/**
 * Controller class is the middle man/class in the game between the GameOfLife and the MainView.
 */
public class Controller {

    private final GameOfLife gameOfLife;
    public Controller(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
    }

    /**
     * starts the game. It is being called by the MainView
     */
    public void startGame() {
        this.gameOfLife.initializeGeneration();
    }

    /**
     * get the last initialized/updated generation from the GameOfLife instanec
     * @return a new GenerationDTO including the generationVersion and the grid cells.
     */
    public GenerationDTO getCurrentGeneration() {
        Generation generation = this.gameOfLife.getLastGeneration();
        return new GenerationDTO(generation.getGenerationVersion(), generation.getGameGrid().getCellsGrid());
    }

    /**
     * calls a function in the gameOfLife to produce the next generation
     */
    public void nextGeneration() {
        this.gameOfLife.produceNextGeneration();
    }
}
