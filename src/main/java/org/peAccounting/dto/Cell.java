package org.peAccounting.dto;

/**
 * It is the GameGrid cell class includes the cell x-coordination, y-coordination
 * and the cell's value which referes if the cell is dead or alive (0, 1).
 */
public class Cell {
    private int yCoordination;

    private int xCoordination;
    private int state;

    public Cell(int yCoordination, int xCoordination, int state) {
        this.yCoordination = yCoordination;
        this.xCoordination = xCoordination;
        this.state = state;
    }

    /**
     * get the x coordination for the cell (colum)
     * @return returns the x-coordination for the cell (column)
     */
    public int getxCoordination() {
        return xCoordination;
    }

    /**
     * get the y coordination for the cell (row)
     * @return returns the y-coordination for the cell (row)
     */
    public int getyCoordination() {
        return yCoordination;
    }

    /**
     *
     * @return the state of the Cell (0 or 1)
     */
    public int getState() {
        return state;
    }

    /**
     * update the cell's state with the new state
     * @param state is the new cell's state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * represents the cell state as a String
     * @return a String of the cell's state
     */
    public String toString() {
        return String.format("%d", this.state);
    }

}
