package com.dhbw.mschoech.muehlemenuefuehrung;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;


public class Main extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //redgister to App-Close function
        ActivityRegistry.register(this);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setup();
    }


    private void setup(){

        //set uo Buttons and initialize listeners
        butActStart();
        butActOptions();
        butActCredits();
        endDialog();


    }
    public void butActStart(){
        final Button butStart = (Button) findViewById(R.id.buttonFields);
        final Intent intStart = new Intent(this, Start.class);





        butStart.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        startActivity(intStart);
                    }
                }
        );

    }
    public void butActOptions(){
        final Button butOptions = (Button) findViewById(R.id.buttonOptions);
        final Intent intOptions = new Intent(this, Options.class);
        butOptions.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Furznase", Toast.LENGTH_LONG);
                        toast.show();
                        intOptions.putExtra("source", "main");
                        startActivity(intOptions);
                    }
                }
        );

    }
    public void butActCredits(){
        final Button butCredits = (Button) findViewById(R.id.buttonCredits);
        final Intent intCredits = new Intent(this, CreditsBackUp.class);
        butCredits.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        //startActivity(intCredits);
                        Toast toast = Toast.makeText(getApplicationContext(), "Furznase", Toast.LENGTH_LONG);
                        toast.show();
                        showCredits();
                    }
                }
        );
    }
    public void butActBack(){
        final Button butBack = (Button) findViewById(R.id.buttonBack);
        final Intent intent     = new Intent(this, Main.class);

        butBack.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        startActivity(intent);
                    }
                }
        );
    }
    public void endDialog(){
        final ImageButton butEnd = (ImageButton) findViewById(R.id.buttonEnd);
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
        butEnd.setOnClickListener(
                new Button.OnClickListener(){


                    public void onClick(View v) {
                        helpDialog.show();
                    }
                }
        );
    }

    public void showCredits(){
        setContentView(R.layout.activity_credits);
        butActBack();

    }



    //****Generic stuff*************************************//
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
    //*****************************************************//


}
