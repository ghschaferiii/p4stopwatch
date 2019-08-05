package edu.luc.etl.cs313.android.simplestopwatch.model.time;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.*;
import android.media.AudioManager;
import android.media.ToneGenerator;

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
    public void decThreeSecondTime(){
        threeSecondTime --;
    }

    @Override
    public void resetThreeSecondTime(){
        threeSecondTime = 3;
    }

    @Override
    public int getThreeSecondTime() {
        return threeSecondTime;
    }

    @Override
    public void beeping(){
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }
}