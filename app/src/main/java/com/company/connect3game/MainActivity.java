package com.company.connect3game;
/** a game in which two players alternately put Xs and Os in compartments of a figure formed by two vertical
 *  lines crossing two horizontal lines and each tries to get a row of three Xs or three Os before the opponent does.
 * @author Felix Ogbonnaya
 * @since 2020-10-02
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //0: Yellow, 1: Red;
    int played = 0;
    boolean isFinished = false;
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPosition = {{0,1,2}, {0,3,6}, {0,4,8}, {1,4,7}, {2,5,8}, {2,4,6}, {3,4,5}, {6,7,8}};
    ImageView nowPlaying;


    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        nowPlaying = findViewById(R.id.nowPlaying);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (!isFinished && gameState[tappedCounter] == 2) {

            played++;
            counter.setTranslationY(-1000);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                nowPlaying.setImageResource(R.drawable.red);

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                nowPlaying.setImageResource(R.drawable.yellow);
            }

            counter.animate().translationYBy(1000);
            winner();
            if(played == 9){
                Button playAgainButton = findViewById(R.id.playAgainButton);
                if (playAgainButton.getVisibility()== View.INVISIBLE){
                    TextView textView = findViewById(R.id.winnerTextView);
                    textView.setText("TIE!!");
                    textView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    played = 0;
                    isFinished = true;
                    nowPlaying.setImageResource(R.drawable.yellow);
                }

            }

        }

    }
    public void winner(){
        for(int[] winningPosition : winningPosition){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[0]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2 ){
                Log.i("INFO", "Somebody have won");

                String winner = "";
                if(activePlayer == 1){
                    winner = "Yellow";
                }else {
                    winner = "Red";
                }

                TextView textView = findViewById(R.id.winnerTextView);
                Button playAgainButton = findViewById(R.id.playAgainButton);
                textView.setText(winner + " has won");

                textView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity.this, winner+ " has won", Toast.LENGTH_LONG).show();
                played = 0;
                isFinished = true;
            }
        }
    }
    public void playAgain(View view){
        TextView textView = findViewById(R.id.winnerTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        textView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        isFinished = false;
        nowPlaying.setImageResource(R.drawable.yellow);
//
        GridLayout gridLayout;
        gridLayout = findViewById(R.id.gridLayout);

        for(int i =0; i< gridLayout.getChildCount(); i++){
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
