package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;


    @Override
    public void onClick(){
        sm.toStoppedState();
        sm.actionResetThreeSecondTime();
        sm.actionReset();
    }

    @Override
    public void onTick() {
        sm.actionDec();
        if (sm.actionGetTime() == 0){
            sm.toAlarmState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
