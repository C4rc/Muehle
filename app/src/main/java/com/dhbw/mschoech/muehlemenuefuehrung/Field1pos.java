package com.dhbw.mschoech.muehlemenuefuehrung;

/**
 * Created by mschoech on 27.04.2015.
 */
public class Field1pos extends FieldPositions{
    float changeValFirst = 222;
    float changeValSecond = 147;
    float changeValThird = 77;

    public Field1pos() {
        positions  = new float[3][8][2];
        placed = new short[3][8];
        field();
    }


    public void field(){
        //start value, "value"
        float value = 61;        //2
        float changeValue = changeValFirst; //41
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
                value = 136;        //17
                changeValue = changeValSecond;   //26
                secondRing = false;
            }else {
                value = 206;         //30
                changeValue = changeValThird;   //13
            }
        }

        //initialize all Y Position values of Normal Grid
        value = 61; //12;
        changeValue = changeValFirst; //32;
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
                value = 133; //23;
                changeValue = changeValSecond; //21;
                secondRing = false;
            }else {
                value = 203; //33;
                changeValue = changeValThird; //11;
            }
        }

        for( int i = 0; i < 3; i++){
            for ( int j = 0; j < 8; j++){
                //System.out.println("positions["+i+"]["+j+"]: "+ positions[i][j][0] + "," + positions[i][j][1]);
                placed[i][j] = 0;
            }
        }

    }

    public float[] compare(int xPos, int yPos, boolean toggle){
        float[] coordinates = new float[2];
        short range = 25;
        boolean done = false;

        for(short i = 0; i < 3; i++){
            for(short j = 0; j < 8; j++){
                if( (positions[i][j][0]- range) <= xPos && xPos <= (positions[i][j][0]+ range) && (positions[i][j][1]- range) <= yPos && yPos <= (positions[i][j][1]+ range) && this.placed[i][j] == 0){
                    coordinates[0] = positions[i][j][0];
                    coordinates[1] = positions[i][j][1];

                    if (toggle){
                        this.placed[i][j] = 1;
                    }
                    else{
                        this.placed[i][j] = 2;
                    }

                    System.out.println("X: " + coordinates[0] + " Y: " + coordinates[1]);
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

    public boolean threeInARow(){
        for(short i = 0; i < 3; i++) {
            for (short j = 0; j < 7; j+=2) {
                short x = (short) (j+2);
                if (x==8){
                    x=0;
                }
                if (this.placed[i][j] != 0 && this.placed[i][j] == this.placed[i][j+1] && this.placed[i][j] == this.placed[i][x]){
                    return true;
                }

            }
        }

        for (short j = 1; j<8; j+=2){
            if (this.placed[0][j] != 0 && this.placed[0][j] == this.placed[1][j] && this.placed[0][j] == this.placed[2][j]){
                return true;
            }
        }

        return false;
    }

}
