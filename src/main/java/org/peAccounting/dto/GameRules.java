package org.peAccounting.dto;

/**
 * A class includes the game's rules
 */
public class GameRules {

    /**
     * validates the Game rules to @gameCell and according to the rules, return the new cell's state
     * @param gameCell the gameCell which will be validated
     * @param counter the counter of alive neighbours to @gameCell
     * @return return the new gameCell's state.
     */
    public static int validateGameRules(Cell gameCell, int counter) {
        if(gameCell.getState() == 1 && (counter == 2 || counter == 3)) {
            return 1;
        }
        if(gameCell.getState() == 0 && counter == 3) {
            return  1;
        }
        return 0;
    }

}
