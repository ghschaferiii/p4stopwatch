package edu.luc.etl.cs313.android.simplestopwatch.test.model.state;

import android.media.AudioManager;
import android.media.ToneGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.OnTickListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.StopwatchStateMachine;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * Testcase superclass for the stopwatch state machine model. Unit-tests the state
 * machine in fast-forward mode by directly triggering successive tick events
 * without the presence of a pseudo-real-time clock. Uses a single unified mock
 * object for all dependencies of the state machine model.
 *
 * @author laufer
 * @see //http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractStopwatchStateMachineTest {

    private StopwatchStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        dependency = null;
    }

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final StopwatchStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.actionInit();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

    /**
     * Verifies that we're initially in the stopped state (and told the listener
     * about it).
     */
    @Test
    public void testPreconditions() {
        assertEquals(R.string.STOPPED, dependency.getState());
    }

    @Test
    public void testActivityScenarioRunAfter3Seconds() {
        model.toWaitingState();
        if (model.actionGetThreeSecondTime() == 0) {
            assertEquals("RUNNING", dependency.getState());
        }
    }

    @Test
    public void testActivityScenarioAlarmatMin() {
        model.toRunningState();
        if (model.actionGetTime() == 0) {
            assertEquals("ALARM", dependency.getState());
        }
    }

    @Test
    public void testActivityScenarioStopAtRunning() {
        model.toRunningState();
        model.onClick();
        assertEquals(R.string.STOPPED, dependency.getState());
    }

    @Test
    public void testActivityScenarioStopAtAlarm() {
        model.toAlarmState();
        model.onClick();
        assertEquals(R.string.STOPPED, dependency.getState());
    }

    /**
     * Checks whether the model has invoked the expected time-keeping
     * methods on the mock object.
     */
    protected void assertTimeEquals(final int t) {
        assertEquals(t, dependency.getTime());
    }
}

/**
 * Manually implemented mock object that unifies the three dependencies of the
 * stopwatch state machine model. The three dependencies correspond to the three
 * interfaces this mock object implements.
 *
 * @author laufer
 */
class UnifiedMockDependency implements TimeModel, ClockModel, StopwatchUIUpdateListener {

    private int timeValue = -1, stateId = -1;

    private int time = 0, threeSecondTime = 3;

    private boolean started = false;

    public int getTime() {
        return timeValue;
    }

    public int getThreeSecondTime() { return threeSecondTime; }

    public int getState() {
        return stateId;
    }

    public boolean isStarted() {
        return started;
    }

    @Override
    public void updateTime(final int timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public void updateState(final int stateId) {
        this.stateId = stateId;
    }

    @Override
    public void setOnTickListener(OnTickListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public void resetTime() {
        time = 0;
    }

    @Override
    public void incTime() {
        time++;
    }

    @Override
    public void decTime() { time--; }

    @Override
    public void decThreeSecondTime() { threeSecondTime--; }

    @Override
    public void resetThreeSecondTime() { threeSecondTime = 3; }

    @Override
    public void beeping(){
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

}
