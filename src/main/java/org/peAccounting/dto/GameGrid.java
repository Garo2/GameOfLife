package org.peAccounting.dto;

public class GameGrid {
    private Cell[][] cellsGrid;

    /**
     * Instantiating a new instance of the game's cells with the passed rows and columns numbers
     * @param gridRowsNum The number of rows in the game
     * @param gridColumnsNum the number of the columns in the game
     */
    public GameGrid(int gridRowsNum, int gridColumnsNum) {
        createCellsGrid(gridRowsNum, gridColumnsNum);
    }

    /**
     * Instantiating a new instance of the game's cells with the passed cells
     * @param cellsGrid the game's cellsGrid
     */
    public GameGrid(Cell[][] cellsGrid) {
        this.cellsGrid = cellsGrid;
    }

    /**
     * Creates the cells of the grid
     * @param gridRowsNum the grid's rows number
     * @param gridColumnsNum the grid's columns number
     */
    private void createCellsGrid(int gridRowsNum, int gridColumnsNum) {
        this.cellsGrid = new Cell[gridRowsNum][gridColumnsNum];
        for (int row=0; row < gridRowsNum; row++) {
            for (int column=0; column < gridRowsNum; column++) {
                this.cellsGrid[row][column] = new Cell(row, column, 0);
            }
        }
    }

    /**
     * Get the grid's cells.
     * @return return the grid's cells
     */
    public Cell[][] getCellsGrid() {
        return cellsGrid;
    }

    /**
     * Gets the coordination of the @cell's neighbours cells
     * @param cell the main cell which neighbour cells will be referred to.
     * @return returns a two-dimensional int array including a set of {row, column} of neighbours cells.
     */
    public static int[][] getNeighboursCellsCoordination(Cell cell) {
        int row = cell.getyCoordination();
        int column = cell.getxCoordination();
        return new int [][]{
                new int[] {row, (column - 1)}, //leftNeighbour
                new int[] {row, (column + 1)}, //rightNeighbour
                new int[] {(row - 1), column}, // uppNeighbour
                new int[] {(row - 1), (column - 1)}, //uppLeftNeighbour
                new int[] {(row - 1), (column + 1)}, //uppRightNeighbour
                new int[] {(row + 1), column}, //downNeighbour
                new int[] {(row + 1), (column - 1)}, //downLeftNeighbour
                new int[] {(row + 1), (column + 1)} //downRightNeighbour
        };
    }

    /**
     * A representation for the GameGrid content which includes the cells list.
     * @return a string including the cell's representation which is the cell's state.
     */
    public String toString() {

        StringBuilder str = new StringBuilder();
        for(int row=0; row< this.cellsGrid.length; row++) {
            for (int column =0; column < this.cellsGrid[0].length; column++) {

                str.append(cellsGrid[row][column]);
                str.append(' ');
            }
            str.append('\n');
        }
        return str.toString();
    }
}
