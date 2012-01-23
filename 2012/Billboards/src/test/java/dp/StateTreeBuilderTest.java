package dp;

import model.Board;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class StateTreeBuilderTest {

    @Test
    public void shouldCreateStates() {
        List<Board> levelOfStates = StateTreeBuilder.getLevelOfStates(new Board(10, 10, "one two three"));

        assertEquals(2, levelOfStates.size());

        assertEquals(1, levelOfStates.get(0).getLinesOccupied());
        assertEquals("three", levelOfStates.get(0).getText());

        assertEquals(1, levelOfStates.get(1).getLinesOccupied());
        assertEquals("two three", levelOfStates.get(1).getText());
    }
}
