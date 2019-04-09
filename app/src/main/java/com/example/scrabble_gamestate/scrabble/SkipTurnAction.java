package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Changes the current turn to the other player without the current player playing a word when
 * the skip button is pressed.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class SkipTurnAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SkipTurnAction(GamePlayer player) {
        //if skip button is pressed the player whose turn it is should be skipped
        //skip player1 turn
        //
        //if(button is pressed)
        //{
        //  int turn = state.getTurn();
        //  if(turn == 0)
        //  {
        //      state.setTurn(1);
        //  }
        //  else
        //  {
        //      state.setTurn(0);
        //  }
        //  return true;
        //}
        //return true;
        super(player);
    }
}
