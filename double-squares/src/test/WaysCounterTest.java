package test;

import logic.WaysCounter;
import org.junit.*;

import static junit.framework.Assert.*;

import static org.mockito.Mockito.*;

public class WaysCounterTest {

    @Test public void checkCountingWays() {
        assertEquals(0, WaysCounter.countWays(3));
        assertEquals(1, WaysCounter.countWays(10));
        assertEquals(2, WaysCounter.countWays(25));
        assertEquals(1, WaysCounter.countWays(0));
        assertEquals(1, WaysCounter.countWays(1));
    }
}