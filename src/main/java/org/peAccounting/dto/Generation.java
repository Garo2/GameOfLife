package org.peAccounting.dto;

public class Generation {
    private final GameGrid gameGrid;
    private final int generationVersion;

    /**
     * Representation for each gameGrid generation including the generation number.
     * @param gameGrid the gameGrid of the generation
     * @param generationVersion the generation number which works as an id, counter
     */
    public Generation(GameGrid gameGrid, int generationVersion) {
        this.gameGrid = gameGrid;
        this.generationVersion = generationVersion;
    }

    /**
     * Gets the generation Version
     * @return the generationVersion.
     */
    public int getGenerationVersion() {
        return this.generationVersion;
    }

    /**
     * Gets the game grid.
     * @return the game grid.
     */
    public GameGrid getGameGrid() {
        return this.gameGrid;
    }

    /**
     * A representation function for the class that includes the generationVersion and gameGrid/game cells
     * @return return the representation as a string
     */
    public String toString() {
       return String.format("Generation number: %d\n%s", this.generationVersion, this.gameGrid);
    }

}
