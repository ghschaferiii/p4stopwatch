package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState(); // change to running state
    void toStoppedState();  // change to stop state
    void toWaitingState();  // change to waiting state
    void toAlarmState();    // change to alarm state

    // actions
    // we have two times: display time and 3-second time

    void actionInit();
    void actionReset();

    void actionInc();       // increment the timer
    void actionDec();       // decrement the timer
    int actionGetTime();
    init actionResetTime();

    void actionDecThreeSecondTime();
    void actionResetThreeSecondTime();
    void actionGetThreeSecondTime();

    void actionUpdateView();    // update state view on top of timer
    void actionBeeping();      // start beeping



    // state-dependent UI updates
    void updateUIRuntime();
}
