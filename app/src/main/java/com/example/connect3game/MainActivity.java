
package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    //cross = 0 & zero=1 & empty =2
    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningSate = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;

    public static boolean my_contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            counter.setTranslationY(-1500);

            gameState[tappedCounter]=activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.zero);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).setDuration(700);

            for (int[] winningPosition : winningSate) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2) {

                    gameActive = false;

                    String winner;

                    if (activePlayer == 1) {

                        winner = "Cross";

                    } else {

                        winner = "Zero";

                    }
                    TextView winnerText = (TextView)findViewById(R.id.winnerTextView);
                    Button playAgain = (Button)findViewById(R.id.playAgainButton);
                    winnerText.setText(winner + " has won");
                    winnerText.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }

            if(!my_contains(gameState, 2) && gameActive){
                //If so, and gameover is false, then its a tie.
                gameActive=false;
                String winner="there is no winner ";

                TextView winnerText = (TextView)findViewById(R.id.winnerTextView);
                Button playAgain = (Button)findViewById(R.id.playAgainButton);
                winnerText.setText(winner);
                winnerText.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }

        }
    }

    public void playAgain(View view){
        TextView winnerText = (TextView)findViewById(R.id.winnerTextView);

        Button playAgain = (Button)findViewById(R.id.playAgainButton);

        winnerText.setVisibility(View.INVISIBLE);

        playAgain.setVisibility(View.INVISIBLE);

        GridLayout mygridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<mygridLayout.getChildCount(); i++)
        {
            ((ImageView) mygridLayout.getChildAt(i)).setImageResource(0);
        }

        activePlayer = 0;

        for(int i=0;i<gameState.length;i++){

            gameState[i]=2;

        }

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}