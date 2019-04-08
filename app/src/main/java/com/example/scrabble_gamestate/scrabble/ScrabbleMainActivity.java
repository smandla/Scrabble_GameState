package com.example.scrabble_gamestate.scrabble;


import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.LocalGame;
import com.example.scrabble_gamestate.game.config.GameConfig;
import com.example.scrabble_gamestate.game.config.GamePlayerType;

import java.util.ArrayList;

import com.example.scrabble_gamestate.game.config.GamePlayerType;

import java.util.ArrayList;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ScrabbleMainActivity extends GameMainActivity {

    private static final int PORT_NUMBER = 2234;



    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        //two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new ScrabbleHumanPlayer(name);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new ScrabbleDumbComputerPlayer(name);
            }
        });

        // smart computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new ScrabbleSmartComputerPlayer(name);
            }
        });

        // Add the default players
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Scrabble", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Dumb AI", 1); // player 2: a computer player
        defaultConfig.addPlayer("Smart AI", 2); // player 3: a smart computer player
        return defaultConfig;
    }

    /**
     * create a local game
     *
     * @return    the local game, a scrabble game
     */
    public LocalGame createLocalGame(){
        return new ScrabbleLocalGame();
    }

}
