package com.example.scrabble_gamestate.scrabble;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.Tile;

public class ScrabbleController implements View.OnTouchListener, View.OnClickListener
{

    private TextView ourScore;
    private TextView opponentScore;
    private Spinner menu = null;
    private ImageView theirProfile = null;
    private ImageView ourProfile = null;
    private ScrabbleGameState ourGameState;
    private View selectedView = null;
    private GameHumanPlayer ourPlayer = null;
    private Game ourGame;

    public ScrabbleController(TextView ourPlayerScore,TextView theirPlayerScore,
                              ScrabbleGameState theGameState,
                              Game theGame, GameHumanPlayer aPlayer ) {
        ourScore = ourPlayerScore;
        opponentScore = theirPlayerScore;
        ourGameState = theGameState;
        ourGame = theGame;
        ourPlayer = aPlayer;

        /*theirProfile = theirProfPic;
        ourProfile = ourProfPic; */

    }

    public void setUpdatedState(ScrabbleGameState state) {
        ourGameState = state;
    }

    public void setGame(Game local) {
        ourGame = local;
    }

    @Override
    public void onClick(View button) {

        if(ourGameState == null || ourGame == null){
            return;
        }

        switch (button.getId()) {

            //have a case for each tile button to see if its been clicked
            //set selectedView instance var to thatt button
            //then onTouch handles placing of it?
            case R.id.tileOneButton:
                selectedView = button;
                Log.i("controller", "tile one touched");
                break;

            case R.id.tileTwoButton:
                selectedView = button;
                Log.i("controller", "tile two touched");

                break;

            case R.id.tileThreeButton:
                selectedView = button;
                Log.i("controller", "tile three touched");

                break;

            case R.id.tileFourButton:
                selectedView = button;
                Log.i("controller", "tile four touched");

                break;

            case R.id.tileFiveButton:
                selectedView = button;
                Log.i("controller", "tile five touched");

                break;

            case R.id.tileSixButton:
                selectedView = button;
                Log.i("controller", "tile six touched");

                break;

            case R.id.tileSevenButton:
                selectedView = button;
                Log.i("controller", "tile seven touched");

                break;

                //the next cases check if its a certain action type and then send that action
            case R.id.playButton:
                Log.i("controller", "play button touched");

                PlayWordAction playAction = new PlayWordAction(ourPlayer);
                ourGame.sendAction(playAction);
                if(ourPlayer.getPlayerNum() == 0) {
                    this.ourScore.setText("" + ourGameState.getPlayerZeroScore());
                    this.opponentScore.setText("" + ourGameState.getPlayerOneScore());
                }
                else
                {
                    this.ourScore.setText("" + ourGameState.getPlayerOneScore());
                    this.opponentScore.setText("" + ourGameState.getPlayerZeroScore());
                }
                break;

            case R.id.passImageButton:
                Log.i("controller", "skip button touched");

                SkipTurnAction skipAction = new SkipTurnAction(ourPlayer);
                ourGame.sendAction(skipAction);
                break;

            case R.id.swapTileButtton:
                Log.i("controller", "swap button touched");

                if(selectedView.isSelected() == true) {
                    ExchangeTileAction swapTile = new ExchangeTileAction(ourPlayer);
                    ourGame.sendAction(swapTile);
                    break;
                }
                else
                {
                    break;
                }

            case R.id.shuffleImageButton:
                Log.i("controller", "shuffle button touched");

                ShuffleTileAction shuffleAction = new ShuffleTileAction(ourPlayer);
                ourGame.sendAction(shuffleAction);
                break;

            case R.id.dictionaryButton:
                Log.i("controller", "dictionary button touched");

                CheckDictionaryAction dictionaryAction = new CheckDictionaryAction(ourPlayer);
                ourGame.sendAction(dictionaryAction);
                break;

            default:
                break;

        }
    }
        @Override
        public boolean onTouch (View v, MotionEvent event){
            if(ourGameState == null || ourGame == null){
                return true;
            }

            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();


            if(selectedView == null || v == null){
                return true;
            }

            Log.i("controller", "surface view " + v);

            //figuring out which tile to place
            Tile t = null;
            switch (selectedView.getId()) {

                case R.id.tileOneButton:
                    t = ourGameState.getHand0().get(0);
                    break;

                case R.id.tileTwoButton:
                    t = ourGameState.getHand0().get(1);
                    break;

                case R.id.tileThreeButton:
                    t = ourGameState.getHand0().get(2);
                    break;

                case R.id.tileFourButton:
                    t = ourGameState.getHand0().get(3);
                    break;

                case R.id.tileFiveButton:
                    t = ourGameState.getHand0().get(4);
                    break;

                case R.id.tileSixButton:
                    t = ourGameState.getHand0().get(5);
                    break;

                case R.id.tileSevenButton:
                    t = ourGameState.getHand0().get(6);
                    break;

                default:
                    break;
            }

            //based on location of touch, round to nearest multiple of cell size and put tile there
            xTouch = xTouch/ScrabbleSurfaceView.TILE_WIDTH_AND_HEIGHT;
            yTouch = yTouch/ScrabbleSurfaceView.TILE_WIDTH_AND_HEIGHT;

            PlaceTileAction placeTileAction = new PlaceTileAction(ourPlayer, xTouch, yTouch, t);
            ourGame.sendAction(placeTileAction);

            this.selectedView = null;
            return true;
        }

       /* @Override
        public boolean onDrag (View v, DragEvent event){
            float xTouch = (int) event.getX();
            float yTouch = (int) event.getY();

            int action = event.getAction();

            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                {

                }
                case DragEvent.ACTION_DRAG_ENTERED:
                {

                }
                case DragEvent.ACTION_DRAG_LOCATION:
                {

                }
                case DragEvent.ACTION_DROP:
                {

                }
                case DragEvent.ACTION_DRAG_EXITED:
                {

                }
                case DragEvent.ACTION_DRAG_ENDED:
                {

                }
                default:
                {
                    break;
                }

            }


            return true;
        }*/


}
