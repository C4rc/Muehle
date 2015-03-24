package com.dhbw.mschoech.muehlemenuefuehrung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class Main extends ActionBarActivity {

    EditText usrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button butStart   = (Button) findViewById(R.id.buttonStart);
        final Button butOptions = (Button) findViewById(R.id.buttonOptions);
        final Button butCredits = (Button) findViewById(R.id.buttonCredits);

        final Intent intStart   = new Intent(this, Start.class);
        final Intent intOptions = new Intent(this, Options.class);
        final Intent intCredits = new Intent(this, Credits.class);

        butStart.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intStart);
                    }
                }
        );

        butOptions.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intOptions);
                    }
                }
        );

        butCredits.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intCredits);
                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
