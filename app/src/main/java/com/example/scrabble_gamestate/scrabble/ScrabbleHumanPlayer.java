package com.example.scrabble_gamestate.scrabble;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;

/**
 *This is the human player for the Scrabble Game. It makes sure the current player gets the info
 * that they need to see, and updates the GUI for that player in accordance with the actions the
 * human player takes.
 *
 *  @author Sydney Wells
 *  @author Sarah Bunger
 *  @author Kavya Mandla
 *  @author Meredith Marcinko
 *  @version February 2019
 */
public class ScrabbleHumanPlayer extends GameHumanPlayer {

    /* instance variables */

    // The TextView the displays the current counter value
    private TextView counterValueTextView;

    private TextView ourScore;
    private TextView opponentScore;

    private ScrabbleController theController;

    //buttons for various actions
    private ImageButton swapTileButton;
    private ImageButton skipButton;
    private ImageButton shuffleTileButton;
    private ImageButton dictionaryButton;
    private ImageButton playButton;

    //buttons that make up a player's hand
    private ImageButton tileOneButton;
    private ImageButton tileTwoButton;
    private ImageButton tileThreeButton;
    private ImageButton tileFourButton;
    private ImageButton tileFiveButton;
    private ImageButton tileSixButton;
    private ImageButton tileSevenButton;
    // the most recent game state, as given to us by the ScrabbleLocalGame
    private ScrabbleGameState state;

    // the android activity that we are running
    private GameMainActivity myActivity;
    //surface view on which we're changing the board
    private ScrabbleSurfaceView surface;

    ScrabbleGameState latestState = new ScrabbleGameState();

    //TODO for after alpha, deal with the unusual case where players can't get rid of their letters
    //bool skipped  start it out as false; everytime they skip, check to see if true (if so,
    // forfeit); pop up yes/no
    //dialog asking if they actually want to forfeit, then send a quitgameaction instead

    /**
     * constructor
     * @param name
     * 		the player's name
     */
    public ScrabbleHumanPlayer(String name) {
        super(name);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    @Override
    protected void initAfterReady() {
        surface.setGame(game);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * displays the current player's hand to that player
     */
    protected void updateDisplay() {

        // set the text in the appropriate widget
        //counterValueTextView.setText("" + state.getCounter());

        ImageButton[] buttons = {tileOneButton, tileTwoButton, tileThreeButton, tileFourButton,
                tileFiveButton, tileSixButton, tileSevenButton};
        //updates the image resources in the hand to match the value in the tile array
        //TODO after alpha, need to deal with the possibility that human isn't player one
        //make each image button look like the tiles in the human player's hand
        for (Tile t: state.getHand1()) {
            int androidId = t.getAndroidId();
            int index = state.getHand1().indexOf(t);
            buttons[index].setImageResource(androidId);
        }
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a ScrabbleGameState message
        /**if (!(info instanceof ScrabbleGameState)) return;
        else if( surface == null)
        {
            return;
        }*/
        if (info instanceof ScrabbleGameState) {
            this.latestState = (ScrabbleGameState) info;
        } else {
            //TODO: should do more here?
            return;
        }

        // update our state; then update the display
        this.state = (ScrabbleGameState) info;

        theController.setUpdatedState(this.state);
        updateDisplay();

        surface.setState((ScrabbleGameState) info);//makes sure the state isn't null
        surface.invalidate();//updates the gui view

    }//note: we used Meredith's Uno game from last semester as an example and reference

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        surface = myActivity.findViewById(R.id.surfaceView);

        if (state != null) {
            receiveInfo(state);
        }

        //state.loadDictionary( myActivity.getApplicationContext()); /

        ourScore = activity.findViewById(R.id.playerScore);
        opponentScore = activity.findViewById(R.id.opponentScore);

        theController = new ScrabbleController(ourScore, opponentScore, state, game, this );

        //sets the listeners for the gameplay buttons
        swapTileButton = activity.findViewById(R.id.swapTileButtton);
        swapTileButton.setOnClickListener(theController);

        skipButton = activity.findViewById(R.id.passImageButton);
        skipButton.setOnClickListener(theController);

        shuffleTileButton = activity.findViewById(R.id.shuffleImageButton);
        shuffleTileButton.setOnClickListener(theController);

        dictionaryButton = activity.findViewById(R.id.dictionaryButton);
        dictionaryButton.setOnClickListener(theController);

        playButton = activity.findViewById(R.id.playButton);
        playButton.setOnClickListener(theController);

        //sets the listeners for the player hand tiles for onClick
        tileOneButton = activity.findViewById(R.id.tileOneButton);
        tileOneButton.setOnClickListener(theController);

        tileTwoButton = activity.findViewById(R.id.tileTwoButton);
        tileTwoButton.setOnClickListener(theController);

        tileThreeButton = activity.findViewById(R.id.tileThreeButton);
        tileThreeButton.setOnClickListener(theController);

        tileFourButton = activity.findViewById(R.id.tileFourButton);
        tileFourButton.setOnClickListener(theController);

        tileFiveButton = activity.findViewById(R.id.tileFiveButton);
        tileFiveButton.setOnClickListener(theController);

        tileSixButton = activity.findViewById(R.id.tileSixButton);
        tileSixButton.setOnClickListener(theController);

        tileSevenButton = activity.findViewById(R.id.tileSevenButton);
        tileSevenButton.setOnClickListener(theController);

        // remember the field that we update to display the counter's value
        /*this.counterValueTextView =
                (TextView) activity.findViewById(R.id.counterValueTextView);*/

        surface = myActivity.findViewById(R.id.surfaceView);
        surface.setOnTouchListener(theController);
        //activity.setContentView(R.layout.activity_main);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
//        if (state != null) {
//            receiveInfo(state);
//        }
    }

}// class CounterHumanPlayer


