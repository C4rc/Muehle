package com.dhbw.mschoech.muehlemenuefuehrung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;


public class Game extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActivityRegistry.register(this);

        final ImageButton butHome = (ImageButton) findViewById(R.id.buttonHome);
        final ImageButton butOptions = (ImageButton) findViewById(R.id.buttonOptions);
        final ImageButton butUndo = (ImageButton) findViewById(R.id.buttonUndo);
        final ImageButton butRedo = (ImageButton) findViewById(R.id.buttonRedo);
        final ImageButton butEnd = (ImageButton) findViewById(R.id.buttonEnd);

        final Intent intHome = new Intent(this, Main.class);
        final Intent intOptions = new Intent(this, Options.class);

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle(getString(R.string.strEndPopUp));
        helpBuilder.setMessage(getString(R.string.strEndQuestionPopUp));
        helpBuilder.setNegativeButton(getString(R.string.strNoPopUp),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });
        helpBuilder.setPositiveButton(getString(R.string.strYesPopUp),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ActivityRegistry.finishAll();
                    }
                });

        final AlertDialog helpDialog = helpBuilder.create();
        Intent i = getIntent();
        String player1 = i.getStringExtra("Player 1");
        String player2 = i.getStringExtra("Player 2");

        //static value
        TextView texPlayer = (TextView) findViewById(R.id.textPlayerTurn);
        texPlayer.setText(player1);


        butHome.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intHome);
                    }
                }
        );

        butOptions.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        intOptions.putExtra("source", "game");
                        startActivity(intOptions);
                    }
                }
        );

        butEnd.setOnClickListener(
            new Button.OnClickListener(){
                public void onClick(View v) {
                    helpDialog.show();
                }
            }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
