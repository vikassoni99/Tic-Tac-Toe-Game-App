package com.example.user.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0=blue 1=red
    ImageView iga[]={};
    int tap;
    int activePlayer = 0;
    String winner = "";
    int [] gameState ={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    public void dropin(View view){
        ImageView ig = (ImageView) view;
        tap = Integer.parseInt(ig.getTag().toString());
        if(gameState[tap] == 2 && gameActive) {
            gameState[tap] = activePlayer;
            ig.setTranslationY(-1000f);
            if (activePlayer == 0) {
                activePlayer = 1;
                ig.setImageResource(R.drawable.blue);
            } else {
                activePlayer = 0;
                ig.setImageResource(R.drawable.red);
            }
            ig.animate().translationY(0f).setDuration(300);

            for(int [] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2 ){
                    //Someone won
                    gameActive=false;
                        winner = "Red has WON !";
                    if(gameState[winningPosition[0]] == 0){
                        winner = "Blue has WON !";
                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner);

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.animate().translationY(100f).setDuration(300);
                    layout.setVisibility(View.VISIBLE);
                }else{
                     boolean gameIsEnd = true;
                    for(int i : gameState) {
                        if (i == 2) gameIsEnd = false;

                    }if (gameIsEnd)
                    {
                        TextView text = findViewById(R.id.winnerMessage);
                        text.setText("");
                        displayMessage("Its a draw !");
                    }

                }

            }
        }
    }
    public void displayMessage(String message){
        TextView winnerMessage = findViewById(R.id.winnerMessage);
        winnerMessage.setText(message);
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.animate().translationY(100f).setDuration(300);
        layout.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view) {
        //0=blue 1=red
        activePlayer = 0;
        gameActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ImageView iga[] = {findViewById(R.id.imageView10), findViewById(R.id.imageView11), findViewById(R.id.imageView12)
                , findViewById(R.id.imageView13), findViewById(R.id.imageView14), findViewById(R.id.imageView15)
                , findViewById(R.id.imageView16), findViewById(R.id.imageView17), findViewById(R.id.imageView18)};
        for (int i = 0; i < 9; i++) {
            iga[i].setImageResource(0);
        }
        TextView text = findViewById(R.id.winnerMessage);
        text.setText("");
    }

//    public void undo (View view){
//        gameState[tap]=2;
//        if(activePlayer==0){
//            activePlayer +=1 ;
//        }
//        else{
//            activePlayer -=1;
//        }
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
