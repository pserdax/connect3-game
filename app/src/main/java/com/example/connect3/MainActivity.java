package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    //0=yellow, 1=red;
    int activePlayer =0;

    //game is active or not
    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6},{0,3,6},{1,4,7}, {2,5,8}};




    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameIsActive) {

            gameState[tappedCounter]=activePlayer;


            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
            for(int[] winningPosition: winningPositions){

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){

                    System.out.println(gameState[winningPosition[0]]);

                    //someone has won
                    gameIsActive=false;
                    LinearLayout playAgainWindow = (LinearLayout) findViewById(R.id.playAgainLayout);
                    TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);

                    if(gameState[winningPosition[0]] ==0){

                        String hi ="Sarylar utdy!";
                        winnerMsg.setText(hi);

                    }
                    else{
                        String hi ="Gyzyllar utdy!";
                        winnerMsg.setText(hi);
                    }

                    playAgainWindow.setVisibility(view.VISIBLE);







                }

                else{

                    boolean gameisOver= true;
                    for(int counterState: gameState){
                        if(counterState==2){
                            gameisOver =false;

                        }}
                        if(gameisOver){
                            TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);

                            winnerMsg.setText("Deňe-deň!");
                            LinearLayout playAgainWindow = (LinearLayout) findViewById(R.id.playAgainLayout);
                            playAgainWindow.setVisibility(view.VISIBLE);

                        }
                    }
                }

            }
        }


    public void playAgainBtn(View view){

        gameIsActive = true;
        LinearLayout playAgainWindow = (LinearLayout) findViewById(R.id.playAgainLayout);

        playAgainWindow.setVisibility(view.INVISIBLE);
        //0=yellow, 1=red;
        activePlayer =0;

        for(int i =0; i<gameState.length; i++) {

            gameState[i]=2;
        }
        System.out.println();

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);


        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
