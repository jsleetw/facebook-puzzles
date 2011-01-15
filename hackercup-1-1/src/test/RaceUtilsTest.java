package test;

import logic.Fraction;
import logic.RaceUtils;
import logic.Turn;
import org.junit.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;

import static org.mockito.Mockito.*;

public class RaceUtilsTest {

    @Test public void checkSumCalculation() {

        ArrayList<Turn> turns = new ArrayList<Turn>();
        turns.add(new Turn(3, 7));
        turns.add(new Turn(5, 7));

        Fraction f = RaceUtils.sumProbabilityOfSuccessfulOvertakes(turns);

        assertEquals(8,(int) f.getNumerator());
        assertEquals(15, (int)f.getDenominator());
    }
}