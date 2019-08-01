package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class WaitingState implements StopwatchState {

    public WaitingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onClick() {
        if (sm.actionGetTime() < 99){
            sm.actionInc();
            sm.ac
        }
        else{
            sm.updateUIWaittime();
        }
    }


    @Override
    public void onTick() {
        sm.actionDecThreeSecondTime();
        if (sm.actionGetThreeSecondTime() == 0){
            sm.actionBeeping();
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.WAITING;
    }
}
