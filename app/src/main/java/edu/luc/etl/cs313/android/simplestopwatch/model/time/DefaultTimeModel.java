package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private int time = 0;
    private int threeSecondTime = 3;


    @Override
    public void incTime() {
        time = (time + SEC_PER_TICK) % SEC_PER_HOUR;
    }

    @Override
    public void decTime() {
        time = (time - SEC_PER_TICK) % SEC_PER_HOUR;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void resetTime(){
        time = 0;
    }

    @Override
    public decSecondTime


}