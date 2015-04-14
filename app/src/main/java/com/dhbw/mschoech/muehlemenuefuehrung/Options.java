package com.dhbw.mschoech.muehlemenuefuehrung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;


public class Options extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRegistry.register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //do shit
        start();


    }

    private void start(){

        Intent intent = getIntent();
        final String source = intent.getStringExtra("source");
        final String field = intent.getStringExtra("Field");
        final Button butBack = (Button) findViewById(R.id.buttonBack);
        System.out.println(source);
        System.out.println(field);
        if( source.equals("main")){
            intent = new Intent(this, Main.class);
        }else if (source.equals("game")) {
            intent = new Intent(this, Game.class);
            intent.putExtra("Field", field);
        }
        final Intent finalIntent = intent;
        butBack.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(finalIntent);
                    }
                }
        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
