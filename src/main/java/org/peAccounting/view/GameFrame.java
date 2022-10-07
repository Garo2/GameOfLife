package org.peAccounting.view;

import org.peAccounting.dto.Cell;
import org.peAccounting.dto.GenerationDTO;
import javax.swing.*;
import java.awt.*;

/**
 * GameFrame represents the Game and will include the cells components
 */
public class GameFrame extends JFrame {

    /**
     * Configure the GameFrame and initialize it with required data and configurations
     * @param width the GameFrame's width
     * @param height the GameFrame's height
     */
    public GameFrame(int width, int height) {
        super.setTitle("Game of Life");
        super.setSize(width, height);
        Dimension center = Toolkit.getDefaultToolkit().getScreenSize();
        super.setLocation(center.width/4, center.height/4);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        super.setVisible(true);
    }

    /**
     * Initialize the GameFrame and add the components to the frame to be painted in each update.
     * @param firstGenerationDTO includes the cellsGrid and generationVersion.
     */
    public void initializeGameFrame(GenerationDTO firstGenerationDTO) {
        Cell[][] firstCellsGrid = firstGenerationDTO.getCellsGrid();
        CellComponent[][] cellsComponents = new CellComponent[firstCellsGrid.length][firstCellsGrid[0].length];
        JLabel generationVersionComponent = versionComponentConfiguration(firstGenerationDTO.getGenerationVersion());
        super.add(generationVersionComponent);
        for (int row = 0; row < cellsComponents.length; row++) {
            for (int column = 0; column < cellsComponents[0].length; column++) {
                cellsComponents[row][column] = cellComponentConfiguration(firstCellsGrid[row][column]);
                super.add(cellsComponents[row][column]);
            }
        }
    }

    /**
     * Configure the generationVersion component.
     * @param generationVersion the generationVersion
     * @return JLabel of the generationVersion
     */
    private JLabel versionComponentConfiguration(int generationVersion) {
        int yStart = 0;
        int xStart = 10;
        int yEnd = 20;
        int xEnd = 200;
        String layerText = "Generation version:";
        JLabel generationVersionLabel = new JLabel();
        generationVersionLabel.setBounds(xStart, yStart, xEnd, yEnd);
        generationVersionLabel.setText(String.format("%s %d", layerText, generationVersion));
        return generationVersionLabel;
    }

    /**
     * Configure the Cell component with required configuration and data.
     * @param cellGrid the cell to be initialized as a component.
     * @return the configured CellComponent.
     */
    private CellComponent cellComponentConfiguration(Cell cellGrid) {
        int row = cellGrid.getyCoordination();
        int column = cellGrid.getxCoordination();
        int yStart = (row * 10) + 30;
        int xStart = (column * 10) + 10;
        int yEnd = ((row + 1) * 10) + 10;
        int xEnd = ((column + 1) * 10) + 10;
        CellComponent currentCellComp = new CellComponent(cellGrid);
        currentCellComp.setBounds(xStart, yStart, xEnd, yEnd);
        currentCellComp.setEnabled(false);
        return currentCellComp;
    }

    /**
     * Update the components of the Cells and their states with the new ones.
     * @param generationDTO the new generationsDTO to update the CellComponents with
     */
    public void updateComponents(GenerationDTO generationDTO) {
        Cell[][] cellsGrid = generationDTO.getCellsGrid();
        Component[] frameCells = this.getContentPane().getComponents();
        JLabel generationVersionLabel = (JLabel) frameCells[0];
        generationVersionLabel.setText(String.format("Generation version: %d", generationDTO.getGenerationVersion()));
        int i = 1;
        for (int row =0; row < cellsGrid.length; row++) {
            for (int column = 0; column < cellsGrid[0].length; column++) {
                CellComponent cellComp = (CellComponent) frameCells[i];
                cellComp.setCellCompState(cellsGrid[row][column]);
                i++;
            }
        }
    }
}