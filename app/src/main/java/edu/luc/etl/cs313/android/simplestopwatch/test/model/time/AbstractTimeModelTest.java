package edu.luc.etl.cs313.android.simplestopwatch.test.model.time;

import android.widget.TextView;

import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_HOUR;
import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_MIN;
import static edu.luc.etl.cs313.android.simplestopwatch.common.Constants.SEC_PER_TICK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.widget.TextView;

import org.junit.Test;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;
import edu.luc.etl.cs313.android.simplestopwatch.test.android.AbstractStopwatchActivityTest;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testInitiallyAtMin(){
        assertEquals(0, model.getTime());
    }

    @Test
    public void testThreeSecondTime() {
        assertTrue(model.getThreeSecondTime() == 3);
    }

    @Test
    public void testGetDisplayedValueEqualsCurrentTime() {
        //FIXME - complete
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrement() {
        model.resetTime();
        final int displaytime = model.getTime();
        model.incTime();
        assertEquals(1, model.getTime());
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrement5() {
        model.resetTime();
        final int displaytime = model.getTime();
        for (int i = 0; i < 5; i ++) {
            model.incTime();
        }
        assertEquals(5, model.getTime());
    }

    @Test
    public void testDecrementRuntimeOne() {
        model.resetTime();
        final int runtime = model.getTime();
        for (int i = 0; i < 5; i ++) {
            model.incTime();
        }
        model.decTime();
        assertEquals(4, model.getTime());
    }

    @Test
    public void testDecrementRuntime5() {
        model.resetTime();
        final int runtime = model.getTime();
        for (int i = 0; i < 5; i ++) {
            model.incTime();
        }
        for (int i = 0; i < 5; i++) {
            model.decTime();
        }
        assertEquals(0, model.getTime());
    }
}
