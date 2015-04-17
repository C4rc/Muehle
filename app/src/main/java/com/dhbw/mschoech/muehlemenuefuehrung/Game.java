package com.dhbw.mschoech.muehlemenuefuehrung;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhbw.mschoech.muehlemenuefuehrung.util.ActivityRegistry;



public class Game extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityRegistry.register(this);

        //display appropriate Field
        Intent i = getIntent();
        final String field = i.getStringExtra("Field");
        displayBackground(field);

        RelativeLayout layout = (RelativeLayout)(this.findViewById(R.id.layoutGame));
        //layout.addView(new MyView(this));

        start(i, layout);
    }

    private void start(Intent i, final RelativeLayout layout){
        final ImageButton butHome = (ImageButton) findViewById(R.id.buttonHome);
        final ImageButton butOptions = (ImageButton) findViewById(R.id.buttonOptions);
        final ImageButton butUndo = (ImageButton) findViewById(R.id.buttonUndo);
        final ImageButton butRedo = (ImageButton) findViewById(R.id.buttonRedo);
        final ImageButton butEnd = (ImageButton) findViewById(R.id.buttonEnd);

        final Intent intHome = new Intent(this, Main.class);
        final Intent intOptions = new Intent(this, Options.class);

        //create Button and dialog to end App
        endDialog(butEnd);

        //get data from previous page
        String player1 = i.getStringExtra("Player 1");
        String player2 = i.getStringExtra("Player 2");
        final String field = i.getStringExtra("Field");
        TextView texPlayer = (TextView) findViewById(R.id.textPlayerTurn);
        texPlayer.setText(player1);


/*************************************************/
        final MyView vi = new MyView(Game.this);
        butUndo.setOnClickListener(
                new Button.OnClickListener() {
                    int x, y = 100;
                    public void onClick(View v) {
                        vi.setxPos((x < vi.getWidth() )? (x += 50) : (x = 0));
                        vi.setyPos((y < vi.getheight()) ? (y += 50) : (y = 0));
                        layout.removeView(vi);
                        layout.addView(vi);


                    }
                }
        );
        layout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {
                        // Perform tasks here
                        int x = (int)m.getX();
                        int y = (int)m.getY();
                        int width = vi.getWidth();
                        int height = vi.getHeight();
                        if (x < (width / 3)){
                            vi.setxPos((vi.getwidth() / 4) - 30);
                        }else if ((width / 3) <= x && x < (width / 3 * 4) ){
                            vi.setxPos((vi.getwidth() / 2) - 30);
                        }else if ((width / 3 * 4) <= x){
                            vi.setxPos((vi.getwidth() / 3 * 4) - 30);
                        }

                        //vi.setxPos((int)m.getX() - 30);
                        vi.setyPos((int)m.getY() - 30);
                        layout.removeView(vi);
                        layout.addView(vi);
                        return true;
                    }
                }
        );
/************************************************/
        //buttons, may be outsourced later
        butHome.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        startActivity(intHome);
                    }
                }
        );

        butOptions.setOnClickListener(
                new Button.OnClickListener(){

                    public void onClick(View v) {
                        intOptions.putExtra("source", "game");
                        intOptions.putExtra("Field", field);
                        startActivity(intOptions);
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

    public void displayBackground(String field){
        //static value

        switch (field) {
            case "field1": setContentView(R.layout.activity_game_field1);
                break;
            case "field2": setContentView(R.layout.activity_game_field2);
                break;
            case "field3": setContentView(R.layout.activity_game_field3);
                break;
            case "field4": setContentView(R.layout.activity_game_field4);
                break;

        }

    }

    private void endDialog(ImageButton butEnd){
        //end dialog
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

    private class MyView extends View {
        int xPos = 0;
        int yPos = 0;
        int width;
        int height;


        public MyView(Context context) {
            super(context);

        }
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            setwidth(canvas.getWidth());
            setheight(canvas.getHeight());
            canvas.drawColor(Color.TRANSPARENT);
            Bitmap token = BitmapFactory.decodeResource(getResources(), R.drawable.spielstein_dunkel);
            int targetWidth  = token.getWidth() /20;
            int targetHeight = token.getHeight() / 20;
            Bitmap tok = Bitmap.createScaledBitmap(token, targetWidth, targetHeight, true);
            token.recycle();

            canvas.drawBitmap(tok, xPos, yPos, new Paint());

        }


        public int getxPos() {
            return xPos;
        }

        public void setxPos(int xPos) {
            this.xPos = xPos;
        }
        public int getyPos() {
            return yPos;
        }

        public void setyPos(int yPos) {
            this.yPos = yPos;
        }

        public void setwidth(int width) {
            this.width = width;
        }

        public void setheight(int height) {
            this.height = height;
        }

        public int getwidth() {
            return width;
        }

        public int getheight() {
            return height;
        }
    }


}
