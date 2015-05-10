package com.dhbw.mschoech.muehlemenuefuehrung;

/**
 * Created by mschoech on 28.04.2015.
 */
public abstract class FieldPositions {
    float positions[][][];
    short placed [][];

    public float[] compare(int x, int y, boolean toggle){
        return null;
    }
    public void field(int width){}

    public abstract boolean threeInARow();
}
