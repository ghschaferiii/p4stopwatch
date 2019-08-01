package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void incTime();
    void decTime();
    int getTime();
    void resetTime();

    void decThreeSecondTime();
    void resetThreeSecondTime();
    int getThreeSecondTime();

    void beeping();

}
