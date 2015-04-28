package com.dhbw.mschoech.muehlemenuefuehrung;

import android.widget.RelativeLayout;

/**
 * Created by mschoech on 27.04.2015.
 */
public class Field1pos extends FieldPositions{
    //short positions[][][];

    public Field1pos() {
        positions  = new float[3][8][2];
        field();
    }


    public void field(){
        float value = 2;
        float changeValue = 41;
        boolean secondRing = true;

        //initialize all X Position Values of Normal Grid
        for( short i = 0; i < 3; i++) {
            for( short j = 0; j < 8; j++) {
                if (j < 2 ){
                    positions[i][j][0] = value;
                    value += changeValue;
                }else if(j < 5 || j == 7 ){
                    positions[i][j][0] = value;
                }else if(j < 7){
                    value -= changeValue;
                    positions[i][j][0] = value;
                }
            }
            if( secondRing) {
                value = 17;
                changeValue = 26;
                secondRing = false;
            }else {
                value = 30;
                changeValue = 13;
            }
        }

        //initialize all Y Position values of Normal Grid
        value = 12;
        changeValue = 32;
        secondRing = true;
        for( short i = 0; i < 3; i++) {
            for( short j = 0; j < 8; j++) {
                if (j < 3 ){
                    positions[i][j][1] = value;
                }else if(j < 5 ){
                    value += changeValue;
                    positions[i][j][1] = value;
                }else if(j < 7){
                    positions[i][j][1] = value;
                }else if(j == 7){
                    value -= changeValue;
                    positions[i][j][1] = value;

                }
            }
            if( secondRing) {
                value = 23;
                changeValue = 21;
                secondRing = false;
            }else {
                value = 33;
                changeValue = 11;
            }
        }

/*
        for( int i = 0; i < 3; i++){
            for ( int j = 0; j < 8; j++){
                System.out.println("positions["+i+"]["+j+"][x]: "+ positions[i][j][0]);
                System.out.println("positions["+i+"]["+j+"][y]: "+ positions[i][j][1]);
            }
        }
*/
    }

    public float[] compare(int xPos, int yPos){
        float[] coordinates = new float[2];
        short range = 6;
        boolean done = false;


        for(short i = 0; i < 3; i++){
            for(short j = 0; j < 8; j++){
                if( (positions[i][j][0]- range) <= xPos && xPos <= (positions[i][j][0]+ range) && (positions[i][j][1]- range) <= yPos && yPos <= (positions[i][j][1]+ range) ){
                    coordinates[0] = positions[i][j][0];
                    coordinates[1] = positions[i][j][1];
                    done = true;
                    break;
                }
            }
            if(done){
                break;
            }
        }
        return coordinates;
    }





}
