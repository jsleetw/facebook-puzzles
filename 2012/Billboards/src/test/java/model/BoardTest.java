package model;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BoardTest {

    @Test
    public void shouldReadWordsIntoOneLine() {
        List<String> words = new Board(10, 10, "hacker cup 2013").getWords();

        assertEquals(3, words.size());
        assertEquals("hacker", words.get(0));
        assertEquals("cup", words.get(1));
        assertEquals("2013", words.get(2));
    }

    @Test
    public void shouldCopyBoardWithoutSomeWords() {

        Board board = new Board(10, 10, "hacker cup 2013");
        Board board1 = board.copyReduced("hacker cup".length());

        assertEquals(1, board1.getLinesOccupied());
        assertEquals(board1.getText(), "2013");
    }
}

