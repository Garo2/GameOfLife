package org.peAccounting.view;

import org.peAccounting.dto.Cell;
import javax.swing.*;
import java.awt.*;

public class CellComponent extends JButton {
    private Cell cell;

    /**
     * The cell component to be used for draw purpose, includes the Cell data.
     * @param cell the Cell object to be represented as a JButton.
     */
    public CellComponent(Cell cell) {
        super();
        this.cell = cell;
        this.setCellCompState(cell);
    }

    /**
     * Change the background color of the button according to the cell's state.
     * If the state of the cell is 1, the background color is set as GRAY which means alive cell,
     * If the State of the cell is 0, the background color is set as WHITE which means dead cell.
     * @param cell will be used to check its state and update the background color.
     */
    public void setCellCompState(Cell cell) {
        this.setCell(cell);
        if (cell.getState() == 1) {
            this.setBackground(Color.GRAY);
            return;
        }
        this.setBackground(Color.WHITE);
    }

    /**
     * update the cell of the component with the new ccell.
     * @param cell the cell to be updated with.
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
