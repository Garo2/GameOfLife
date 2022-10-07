package org.peAccounting.dto;

public class GenerationDTO {

    private final int generationVersion;
    private final Cell[][] cellsGrid;

    /**
     * A DTO class for the generation data without including the GameGrid which may include functionalities.
     * This DTO class will be used to be passed to the view.
     * @param generationVersion the generation version
     * @param cellsGrid the cells of the grid.
     */
    public GenerationDTO(int generationVersion, Cell[][] cellsGrid) {
        this.generationVersion = generationVersion;
        this.cellsGrid = cellsGrid;
    }

    /**
     * Get the cells of the game
     * @return returns the cellsGrid.
     */
    public Cell[][] getCellsGrid() {
        return cellsGrid;
    }

    /**
     * Returns the generation version
     * @return the generationVersion.
     */
    public int getGenerationVersion() {
        return generationVersion;
    }
}
