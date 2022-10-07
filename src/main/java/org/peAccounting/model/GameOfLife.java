package org.peAccounting.model;


import org.peAccounting.dto.Cell;
import org.peAccounting.dto.GameGrid;
import org.peAccounting.dto.GameRules;
import org.peAccounting.dto.Generation;
import org.peAccounting.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

/**
 * The game of Life main class that handles all functionalities from initializing new game cells,
 * evloving the generation of the game, creating the game grid,
 * and other necessary related functionalities.
 */
public class GameOfLife {

    private final List<Generation> generations;
    private final int numberOfRandomCells;
    private final int gridRowsNumber;
    private final int gridColumnsNumber;

    /**
     * Creates a new instance of the Life, and it's necessary data
     * @param gridSize the grid Size which is the rows and columns number
     * @param numberOfRandomCells the number of the randomized cells to update it's state to alive.
     */
    public GameOfLife(int gridSize, int numberOfRandomCells) {
        this.generations = new ArrayList<>();
        this.gridRowsNumber = gridSize;
        this.gridColumnsNumber = gridSize;
        this.numberOfRandomCells = numberOfRandomCells;
    }

    /**
     * Adds a new game generation to the "generations" List.
     * @param newGeneration the new Generation to be added to the list.
     */
    private void addNewGeneration(Generation newGeneration) {
        this.generations.add(newGeneration);
    }

    /**
     * Updates the gameGrid with the randomized cells to let the game start with alive cells.
     * @param gameGrid The gameGrid that will be updated with the randomized cells
     */
    private void randomizeGameGrid(GameGrid gameGrid) {
        Cell[][] cellsGrid = gameGrid.getCellsGrid();
        int gridRows = cellsGrid.length; // rows
        Cell[] randomizedCoordination = this.randomizeGameGridCoordination(this.numberOfRandomCells, 0, gridRows);
        updateGameGridCells(cellsGrid, randomizedCoordination);
    }

    /**
     * Updates the @cellsGrid which are the game's cells with
     * the @randomizedCoordination which includes alive cells (cells with state = 1)
     * @param cellsGrid the cellsGrid that will be updated
     * @param randomizedCoordination the randomized alive cells which will be placed in the cellsGrid
     *                               according to the Cells coordination.
     */
    private void updateGameGridCells(Cell[][] cellsGrid, Cell[] randomizedCoordination) {
        for(int randCoordinationIndex = 0; randCoordinationIndex < randomizedCoordination.length; randCoordinationIndex++) {
            int cellYCoordination = randomizedCoordination[randCoordinationIndex].getyCoordination();
            int cellXCoordination = randomizedCoordination[randCoordinationIndex].getxCoordination();
            int cellState = randomizedCoordination[randCoordinationIndex].getState();
            cellsGrid[cellYCoordination][cellXCoordination].setState(cellState);
        }
    }

    /**
     * Fills the @randomizedChosenCoordination list with the randomized
     * cells @numberOfRandomCoordination times
     * @param numberOfRandomCoordination the number of the random generated coordination for cells
     * @param lowerBound the least possible value to be generated (included)
     * @param upperBound the greatest possible value to be generated (excluded)
     * @return return a filled Cells list with the randomized cells coordination
     */
    private Cell[] randomizeGameGridCoordination(int numberOfRandomCoordination, int lowerBound, int upperBound) {
        Cell[] randomizedChosenCoordination = new Cell[numberOfRandomCoordination];
        for (int randCord=0; randCord < numberOfRandomCoordination; randCord++) {
            randomizedChosenCoordination[randCord] = randomizeGameGridCoordination(lowerBound, upperBound);
        }
        return randomizedChosenCoordination;
    }

    /**
     * Randomize the x and y-coordination to decide which cells be turned into live in the game-grid cells
     * @param lowerBound the least possible value to be generated (included)
     * @param upperBound the greatest possible value to be generated (excluded)
     * @return returns a Cell with the randomized generated coordination and with state 1.
     */
    private Cell randomizeGameGridCoordination(int lowerBound, int upperBound) {
       int yCoordination = Utilities.generateRandomInBound(lowerBound, upperBound);
       int xCoordination = Utilities.generateRandomInBound(lowerBound, upperBound);
       return new Cell(yCoordination, xCoordination, 1);
    }

    /**
     *Creates the GameGrid with dimensions according to rows number and columns number and
     * initialize a new generation of the gameGrid following the game rules.
     */
    public void initializeGeneration() {
        GameGrid firstGameGrid = new GameGrid(this.gridRowsNumber, this.gridColumnsNumber);
        this.randomizeGameGrid(firstGameGrid);
        Generation firstGeneration = new Generation(firstGameGrid, 0);
        addNewGeneration(firstGeneration);
    }

    /**
     * returns the specified generation with the @generationIndex from generation List
     * @param generationIndex the generation index of the desired generation
     * @return returns a generation of the generation list.
     */
    public Generation getGeneration(int generationIndex) {
        return this.generations.get(generationIndex);
    }

    /**
     * @return the latest added generation to the generations list
     */
    public Generation getLastGeneration() {
        return this.getGeneration(this.generations.size()-1);
    }

    /**
     * Evolves the next generation according to the game rules by updating the cells states.
     * @param generation the current generation to be evolved.
     * @return the new evolved generation.
     */
    private Generation evolveGeneration(Generation generation) {
        Cell[][] originalGridCells = generation.getGameGrid().getCellsGrid();
        Cell[][] copiedGridCells = createCellsDeepCopy(originalGridCells);
        for(int row = 0; row < originalGridCells.length; row++) {
            for(int column = 0; column < originalGridCells[row].length; column++) {
                int newState = validateCellState(originalGridCells, originalGridCells[row][column]);
                copiedGridCells[row][column].setState(newState);
            }
        }
        GameGrid newGameGrid = new GameGrid(copiedGridCells);
        return new Generation(newGameGrid, (generation.getGenerationVersion()+1));
    }

    /**
     * validates if the passed cell will be alive or dead for the next generation.
     * According to the game rules, the returned value will be the state of the cell for the
     * next generation.
     * @param originalGridCells the current game grid that will be used to validate if the @cell
     *                          will be dead or alive cell for the next generation.
     * @param cell the validated cell
     * @return the value of the cell's state.
     */
    private int validateCellState(Cell[][] originalGridCells, Cell cell) {
        int[][] neighboursCellsCoordination = GameGrid.getNeighboursCellsCoordination(cell);
        int aliveNeighboursCellsCount = countAliveNeighboursCells(originalGridCells, neighboursCellsCoordination);
        return GameRules.validateGameRules(cell, aliveNeighboursCellsCount);
    }

    /**
     * Counts the number of the existed / valid alive (have state 1) neighbour cells according to the @nCells list
     * @param originalGameCells The original GameGrid cells
     * @param nCells the coordination of the neighbour cells for the cell when the function is called.
     * @return the number of the alive cells around the desired cell
     */
    public int countAliveNeighboursCells(Cell[][] originalGameCells, int[][] nCells) {
        int counter = 0;
        for(int n = 0; n < nCells.length; n++) {
            int gridRowCount = originalGameCells.length;
            int gridColumnCount = originalGameCells[0].length;
            boolean check =checkN(nCells[n], gridRowCount, gridColumnCount);
            if (check) {
                Cell cell = originalGameCells[nCells[n][0]][nCells[n][1]];
                if(cell.getState() == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Checks the validity of the cell coordination
     * @param N a list of x and y coordination of the cell
     * @param gridRowCount the number of the rows in the gameGrid
     * @param gridColumnCount the number of the columns in the gameGrid
     * @return true if the coordination are valid and can be found in the gameGrid.
     *         false if the coordination are out of bound.
     */
    public boolean checkN(int[] N, int gridRowCount, int gridColumnCount) {
        int nRow = N[0];
        int nColumn = N[1];
        if(nRow >= 0 && nColumn >= 0) {
            if(nRow < gridRowCount && nColumn < gridColumnCount) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a deep copy of a list.
     * @param gameCellsLis the list which will be deep copied from
     * @return a new deep copy list of the @gameCellsLis
     */
    private Cell[][] createCellsDeepCopy(Cell[][] gameCellsLis) {
        Cell[][] deepCopiedList = new Cell[gameCellsLis.length][gameCellsLis[0].length];
        for (int row =0; row < gameCellsLis.length; row++) {
            for (int column=0; column<gameCellsLis[row].length; column++) {
                deepCopiedList[row][column] = new Cell(gameCellsLis[row][column].getyCoordination(),
                        gameCellsLis[row][column].getxCoordination(),
                        gameCellsLis[row][column].getState());
            }
        }
        return deepCopiedList;
    }

    /**
     * Evolves the current generation of the gameGrid to the new generation
     * @return the evolved generation
     */
    private Generation evolveCurrentGeneration() {
        return evolveGeneration(this.getLastGeneration());
    }

    /**
     * Produce the next generation of the game and add the new generation to the generations list.
     */
    public void produceNextGeneration() {
       Generation nextGeneration =  evolveCurrentGeneration();
       addNewGeneration(nextGeneration);
    }
}
