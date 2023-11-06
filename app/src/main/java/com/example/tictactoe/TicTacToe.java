package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity {

    boolean gameActive = true;
    // Player representation:
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings:
    // 0 - X
    // 1 - O
    // 2 - Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            reset(view);
            return;
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.tic_tac_toe_x);
                activePlayer = 1;
                Toast.makeText(this, "O's Turn - Tap to play", Toast.LENGTH_SHORT).show();
                /*TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");*/
            } else if (activePlayer == 1) {
                img.setImageResource(R.drawable.tic_tac_toe_o);
                activePlayer = 0;
                Toast.makeText(this, "X's Turn - Tap to play", Toast.LENGTH_SHORT).show();

               /* TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");*/
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for (int[] winPosition : winPositions) {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2)
            {gameActive=false;
                String winnerStr;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X";
                }
                else{
                    winnerStr = "O";
                }
                TextView status = findViewById(R.id.status);
                status.setText("Congratulations\n"+"   "+winnerStr+" has WON!!");
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "Its a Draw";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }
    public void reset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;

        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("");
        Toast.makeText(this, "X's Turn - Tap to play", Toast.LENGTH_SHORT).show();


    }
}