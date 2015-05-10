package com.dhbw.mschoech.muehlemenuefuehrung;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mschoech on 10.05.2015.
 */
public class GameFuncs extends Game {

    public static void gamestate_set(final RelativeLayout gameLayout, MotionEvent m, short tokenCounter, final FieldPositions fieldPositions, final TextView texPlayer, final TextView texStep, final Token[] ligToken, final Token[] darToken, final String player1, final String player2) {
        float[] coordinates;

    // Perform tasks here

        short posx = (short) m.getX();
        short posy = (short) m.getY();

        coordinates = fieldPositions.compare(posx, posy, toggle);
        if (coordinates[0] != 0.0) {
            if (toggle) {
                token = ligToken[tokenCounter];
                texPlayer.setText(player2);

            } else {
                token = darToken[tokenCounter];
                texPlayer.setText(player1);
            }

            token.setxPos(coordinates[0] - 50);
            token.setyPos(coordinates[1] - 50);

            gameLayout.removeView(token);
            gameLayout.addView(token);

            if (tokenCounter < amountToken - 1 && !toggle) {
                texPlayer.setText("I " + tokenCounter);
                tokenCounter++;
            } else if (tokenCounter == amountToken - 1 && !toggle) {
                texStep.setText("jetzt eigentlich spielen");
                Game.play = 1;
            }

            toggle = !toggle;

        }
    }

    public static void gamestate_play(final RelativeLayout gameLayout, MotionEvent m, final FieldPositions fieldPositions, final TextView texPlayer, TextView texStep, final Token[] ligToken, final Token[] darToken, final String player1, final String player2){
        texStep.setText("ich spiele");
    }
}
