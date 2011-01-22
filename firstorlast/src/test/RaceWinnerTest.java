package test;

import logic.RaceUtils;
import logic.RaceWinner;
import logic.Turn;
import org.junit.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class RaceWinnerTest {

    private ArrayList<Turn> turnsToOvertake;

    @Test public void checkCorrectTurnsSelection() {
        ArrayList<Turn> turns = new ArrayList<Turn>();
        //2 2 118 80 400 316
        turns.add(new Turn(118, 80));
        turns.add(new Turn(400, 316));

        turnsToOvertake = RaceWinner.findTakeoverTurns(1, turns);

        assertEquals(1, turnsToOvertake.size());
        assertEquals(turns.get(0), turnsToOvertake.get(0));

        turns.clear();

        //3 3 339 250 301 199 109 34
        turns.add(new Turn(339, 250));
        turns.add(new Turn(301, 199));
        turns.add(new Turn(109, 34));

        turnsToOvertake = RaceWinner.findTakeoverTurns(2, turns);
        assertEquals(2, turnsToOvertake.size());
        assertTrue(turnsToOvertake.contains(turns.get(1)));
        assertTrue(turnsToOvertake.contains(turns.get(2)));


        turns.clear();
        //3 5 10 2 5 3 10 4 3 3 9 7

        turns.add(new Turn(10, 2));
        turns.add(new Turn(5, 3));
        turns.add(new Turn(10, 4));
        turns.add(new Turn(3, 3));
        turns.add(new Turn(9, 7));
        turnsToOvertake = RaceWinner.findTakeoverTurns(2, turns);

        assertEquals("54/175", RaceUtils.getTotalProbability(
                            turns,
                            turnsToOvertake
                    ).reduct().toString());
    }
}