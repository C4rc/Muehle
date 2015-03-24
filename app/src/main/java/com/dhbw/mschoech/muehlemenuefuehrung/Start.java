package com.dhbw.mschoech.muehlemenuefuehrung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Start extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Button butPlayer = (Button) findViewById(R.id.buttonPlayer);
        final Button butComputer = (Button) findViewById(R.id.buttonComputer);
        final Button butBack = (Button) findViewById(R.id.buttonBack);
        final Intent intMain     = new Intent(this, Main.class);
        final Intent intPlayer     = new Intent(this, Player.class);

        butPlayer.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        intPlayer.putExtra("Mode", "1");
                        startActivity(intPlayer);
                    }
                }
        );

        butComputer.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        intPlayer.putExtra("Mode", "0");
                        startActivity(intPlayer);
                    }
                }
        );

        butBack.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intMain);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
