package com.dhbw.mschoech.muehlemenuefuehrung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;


public class Player extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRegistry.register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Data from previous page
        Intent intent = getIntent();
        final String mode = intent.getStringExtra("Mode");
        String retPlayer1 = "Spieler 1";
        String retPlayer2 = "Spieler 2";

        try{
            retPlayer1 = intent.getStringExtra("Player 1");
        }catch (Exception e){}
        try{
            retPlayer2 = intent.getStringExtra("Player 2");
        }catch (Exception e){}

        final Button butBack    = (Button) findViewById(R.id.buttonBack);
        final Button butFields  = (Button) findViewById(R.id.buttonFields);
        final Intent intFields  = new Intent(this, Fields.class);
        final Intent intStart   = new Intent(this, Start.class);

        final EditText player1 = (EditText) findViewById(R.id.inpPlayer1);
        final EditText player2 = (EditText) findViewById(R.id.inpPlayer2);
        final TextView texPlayer2 = (TextView) findViewById(R.id.textPlayer2);

        player1.setText(retPlayer1);
        player2.setText(retPlayer2);

            //mode == 0 means Player vs. Computer
        if (mode.equals("0")){
            player2.setVisibility(View.INVISIBLE);
            texPlayer2.setVisibility(View.INVISIBLE);
        }

        butFields.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        intFields.putExtra("Mode", mode);
                        intFields.putExtra("Player1", player1.getText().toString());
                        intFields.putExtra("Player2", player2.getText().toString());
                        startActivity(intFields);
                    }
                }
        );
        butBack.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        startActivity(intStart);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
