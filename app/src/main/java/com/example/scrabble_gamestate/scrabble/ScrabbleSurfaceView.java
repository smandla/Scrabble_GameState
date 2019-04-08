package com.example.scrabble_gamestate.scrabble;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.Tile;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ScrabbleSurfaceView extends SurfaceView {

    public static final int TILE_WIDTH_AND_HEIGHT = 62; //each tile or cell is 62x62 dp
    public static final int BOARD_SIZE = 15; //board is a 15x15 grid
    private ScrabbleGameState ourState = new ScrabbleGameState();
    private Tile[][] aBoard;//way to get the board into this
    private Bitmap[] tileLetters = new Bitmap[26];//bitmap of all tile letters
    private Game ourGame;
    /**
     * Constructor
     */
    public ScrabbleSurfaceView(Context context) {

        super(context);
        setState(ourState);
        init();
    }

    /**
     * Constructor
     */
    public ScrabbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Constructor
     */
    public ScrabbleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * A helper method
     */
    private void init() {
        //puts the android ID's of every tile in an array
        int[] idToAndroidId= {R.drawable.tile_a, R.drawable.tile_b, R.drawable.tile_c,
                R.drawable.tile_d, R.drawable.tile_e, R.drawable.tile_f, R.drawable.tile_g,
                R.drawable.tile_h, R.drawable.tile_i, R.drawable.tile_j, R.drawable.tile_k,
                R.drawable.tile_l, R.drawable.tile_m, R.drawable.tile_n, R.drawable.tile_o,
                R.drawable.tile_p, R.drawable.tile_q, R.drawable.tile_r, R.drawable.tile_s,
                R.drawable.tile_t, R.drawable.tile_u, R.drawable.tile_v, R.drawable.tile_w,
                R.drawable.tile_x, R.drawable.tile_y, R.drawable.tile_z};
        for(int i = 0; i < 26; i++)//creates new bitmap for each of the tiles in the array
        {
            Bitmap tileLettersCellOrig = BitmapFactory.decodeResource(getResources(),
                    idToAndroidId[i]);
              tileLetters[i] = Bitmap.createScaledBitmap(tileLettersCellOrig, TILE_WIDTH_AND_HEIGHT,
                    TILE_WIDTH_AND_HEIGHT, false);
        }
        setWillNotDraw(false);
    }

    /**
     * Overrides SurfaceView's onDraw method
     * @param: Canvas  The Canvas object to draw on.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFFFFF00);


        //make and resize the images for cells without bonuses
        Bitmap noBonusCellOrig = BitmapFactory.decodeResource(getResources(), R.drawable.nobonus);
        Bitmap noBonusCell = Bitmap.createScaledBitmap(noBonusCellOrig, TILE_WIDTH_AND_HEIGHT,
                TILE_WIDTH_AND_HEIGHT, false);

        Bitmap doubleLetterCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.doubleletter);
        Bitmap doubleLetterCell = Bitmap.createScaledBitmap(doubleLetterCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap doubleWordCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.doubleword);
        Bitmap doubleWordCell = Bitmap.createScaledBitmap(doubleWordCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap tripleLetterCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.tripleletter);
        Bitmap tripleLetterCell = Bitmap.createScaledBitmap(tripleLetterCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap tripleWordCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.tripleword);
        Bitmap tripleWordCell = Bitmap.createScaledBitmap(tripleWordCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        //start off by drawing a board of cells without bonuses
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                float x = row * TILE_WIDTH_AND_HEIGHT;
                float y = col * TILE_WIDTH_AND_HEIGHT;
                canvas.drawBitmap(noBonusCell,x,y,null);
            }
        }

        //adding in double letter bonuses
        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,
                null);

        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,
                null);

        canvas.drawBitmap(doubleLetterCell,0*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,7*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,14*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,
                null);

        canvas.drawBitmap(doubleLetterCell,2*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,12*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,
                null);

        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,
                null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,
                null);

        canvas.drawBitmap(doubleLetterCell,2*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,12*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,0*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,7*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,14*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);

        //adding in double word bonuses
        canvas.drawBitmap(doubleWordCell,1*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,13*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,2*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,12*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,3*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,11*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,4*TILE_WIDTH_AND_HEIGHT,4*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,10*TILE_WIDTH_AND_HEIGHT,4*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,7*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,4*TILE_WIDTH_AND_HEIGHT,10*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,9*TILE_WIDTH_AND_HEIGHT,10*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,3*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,11*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,2*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,12*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,1*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,13*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);

        //adding in triple letter bonuses
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,1*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,13*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,1*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,13*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);

        //adding in triple word bonuses
        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,7*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,7*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);

        aBoard = ourState.getBoard();//gets the board from the current game state
        for(int x = 0; x <15; x++ )//checks thru all x coords in board
        {
            for( int y = 0; y < 15; y++)//checks thru all y coords in board
            {
                if(aBoard[x][y] == null)//don't put stuff on the board if its empty
                {
                    //do nothing
                }
                else
                {
                    //changes board into an array of ints based on char
                    int indexIntoArray = aBoard[x][y].getTileLetter()-'a';
                    //draws a new Bitmap for the place in the index
                    canvas.drawBitmap( tileLetters[indexIntoArray],
                            x*TILE_WIDTH_AND_HEIGHT,y*TILE_WIDTH_AND_HEIGHT,null);
                }
            }
        }//note: we used Sydney's boyfriend Andrew for help with some of the array logic here

    } //onDraw
    public void setState(ScrabbleGameState state)//setter for game state
    {
        this.ourState = state;
    }
    public void setGame(Game g)//setter for game
    {
        this.ourGame = g;
    }
}

