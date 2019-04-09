package com.example.scrabble_gamestate.scrabble;

import android.graphics.Paint;
import android.view.SurfaceView;
import android.widget.Switch;

import com.example.scrabble_gamestate.game.GameComputerPlayer;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.util.Tickable;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.scrabble.ScrabbleGameState;
import com.example.scrabble_gamestate.scrabble.ScrabbleLocalGame;


import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A computer-version of a counter-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2013
 */
public class ScrabbleDumbComputerPlayer extends GameComputerPlayer implements Tickable {

        ArrayList<Character> letters;
    String alreadyPlayedLetter = null;
    Tile alreadyPlayedTile = null;
    boolean foundWord = false;
    int player2 = playerNum;

    // String word = read in line from dumbDictionary.txt
    String word = " ";
    ScrabbleGameState latestState = new ScrabbleGameState();

    /**
     * Constructor for objects of class CounterComputerPlayer1
     *
     * @param name the player's name
     */
    public ScrabbleDumbComputerPlayer(String name) {
        // invoke superclass constructor
        super(name);

        // start the timer, ticking 20 times per second
        getTimer().setInterval(50);
        getTimer().start();
    }


    /**
     * callback method--game's state has changed
     *
     * @param info the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof ScrabbleGameState) {
            this.latestState = (ScrabbleGameState) info;
        } else {
            //TODO: should do more here?
            return;
        }

        //half the time, just skip turn instead of playing a word
//        if (Math.random() >= 0.5) {
//            game.sendAction(new SkipTurnAction(this));
//            return;
//        }

//        //the other half of the time, play a word
//        //TODO: implement algorithm
//        for (int column = 1; column < 14; column++) {
//            for (int row = 0; row < 15; row++) {
//                if ((latestState.getBoard()[row][column] == null) &&
//                        (latestState.getBoard()[row - 1][column] == null) &&
//                        (latestState.getBoard()[row + 1][column] == null)) {
//                    if (alreadyPlayedLetter != null) {
//                        for (Tile t : latestState.getHand1() ) {
//                            if (!letters.contains(t.getTileLetter())) {
//
//                                letters.add(t.getTileLetter());
//                            }
//
//                        }
//                        /**
//                         *  TODO: implement switch statement for DumbAI
//                         * use a switch statement to determine which line number constant to use
//                         *             (example: if additionalLetter = = ‘a’ use line number constant for A)
//                         */
//
//
//                        /**while(foundWord == false){
//                         if(TODO: if it's still AI's turn)){
//                         TODO: ask NUXOLL about InputStream
//                         use scanner & print writer
//                         }**/
//
//                    }
//                    for (int i = 0; i < word.length(); i++) {
//                        String sub = word.substring(i);
//                        int numFound = 0;
//                        for (Character s : letters) {
//                            if ((sub.equals(s)) && !(s.equals(alreadyPlayedLetter))) {
//                                numFound++;
//                            }
//
//                        }
//
//                        if (numFound > word.length() - 1) {
//                            foundWord = true;
//                            int alreadyPlayedX = alreadyPlayedTile.getxCoord();
//                            int alreadyPlayedY = alreadyPlayedTile.getyCoord();
//
//                            for (int j = word.length(); j > 0; j++) {
//                                for (Tile t : latestState.getHand1()) {
//                                    //TODO: THIS MAY OR MAY NOT WORK
//                                    if ((t.getTileLetter() == word.charAt(j)) &&
//                                            latestState.getBoard()[alreadyPlayedX - j][alreadyPlayedY] == null) {
//
//                                    }
//
//                                }
//                                ScrabbleGameState.playWord(turnID);
//                            }
//                        }
//                    }
//                }
//            }
//            //TODO: fix this
//            if (latestState.getBoard()[row][column] != null && latestState.getBoard()[row - 1][column] == null && latestState.getBoard()[row + 1][column] == null) {
//                additionalLetter = //value of Tile in row;
//                        alreadyPlayed = //Tile in row;
//            }
//
//        }
    }
/**
 * TODO: ALL DIS
* if we went through the entire board and didn't find a place to play the word still,
 * the AI will have to skip its turn
 * if(foundWord == false)// * use skipTurn() method
 */
}



