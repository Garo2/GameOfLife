package org.peAccounting.startup;

import org.peAccounting.controller.Controller;
import org.peAccounting.model.GameOfLife;
import org.peAccounting.view.MainView;

/**
 * GameStarter that executes first
 */
public class GameStartup {
    /**
     * Initializes the necessary classes instances to start the game.
     * @param args list of the gridSize, number of Random live cells to generate and
     *             the number of game generation
     * @throws InterruptedException when en exception occurs while using the Thread.sleep().
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            int gridSize = Integer.parseInt(args[0]);
            int numberOfRandomCells =  Integer.parseInt(args[1]);
            int numberOfGenerations =  Integer.parseInt(args[2]);
            GameOfLife gameOfLife = new GameOfLife(gridSize, numberOfRandomCells);
            Controller controller = new Controller(gameOfLife);
            MainView mainView = new MainView(controller);
            mainView.startInterface(numberOfGenerations);
        }catch (NumberFormatException numberFormatException) {
            System.out.println("Illegal type of arguments, Enter integers only");
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.out.println("The arguments are empty, try to add the required arguments");
        }catch (InterruptedException interruptedException) {
            System.out.println("Something went wrong with interrupting the function");
        }catch (Exception e) {
            System.out.println("Something went wrong, The error message is:");
            System.out.println(e.getMessage());
        }
    }
}
