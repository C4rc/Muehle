package com.dhbw.mschoech.muehlemenuefuehrung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;


public class Game extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActivityRegistry.register(this);
        final Button butHome = (Button) findViewById(R.id.buttonHome);
        final Button butOptions = (Button) findViewById(R.id.buttonOptions);
        final Button butUndo = (Button) findViewById(R.id.buttonUndo);
        final Button butRedo = (Button) findViewById(R.id.buttonRedo);
        final Button butEnd = (Button) findViewById(R.id.buttonEnd);

        final Intent intHome = new Intent(this, Main.class);
        final Intent intOptions = new Intent(this, Options.class);

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
                        switch (v.getId()) {
                            case R.id.buttonEnd:
                                ActivityRegistry.finishAll();
                                break;
                        }
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
