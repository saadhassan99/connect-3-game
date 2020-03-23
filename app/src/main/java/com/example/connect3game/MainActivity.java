package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Yellow: 0
    // red: 1

    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8}, {0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    int player = 0;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedCounter] == 2 && gameActive) {
            gamestate[tappedCounter] = player;

            counter.setTranslationY(-1500);
            if (player == 0) {
                player = 1;
                counter.setImageResource(R.drawable.pokeball);
            } else {
                player = 0;
                counter.setImageResource(R.drawable.green);
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(500);

            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[1]] != 2) {
                    //Someone has won!

                    gameActive = false;
                    String winner = "";
                    if (player == 1) {
                        winner = "Red";
                    } else {
                        winner = "Green";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playagainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has Won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playagainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i < gridLayout.getChildCount(); i++) {
            ImageView counters = (ImageView) gridLayout.getChildAt(i);
            counters.setImageDrawable(null);
        }

        Arrays.fill(gamestate, 2);

        gameActive = true;
        player = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
