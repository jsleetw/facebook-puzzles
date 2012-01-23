package logic;

import model.Board;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BoardPrinterTest {

    @Before
    public void clearCache() {
        BoardPrinter.clearCache();
    }

    @Test
    public void shouldDetectIfItIsImpossibleToWriteText() {
        assertEquals(BoardPrinter.getTextSize(new Board(0, 0, "hello")), 0);
    }
    
    @Test
    public void shouldPutInOneLine() {
        assertEquals(1, BoardPrinter.getTextSize(new Board(7, 1, "hello")));
    }

    @Test
    public void shouldPutInOneLineWithSizeLimitedByHeight() {
        assertEquals(2, BoardPrinter.getTextSize(new Board(999, 2, "hello")));
    }

    @Test
    public void shouldPutInOneLineWithSizeLimitedByWidth() {
        assertEquals(2, BoardPrinter.getTextSize(new Board(12, 999, "hello")));
    }

    @Test
    public void shouldDoComplexCase1() {
        assertEquals(3, BoardPrinter.getTextSize(new Board(20, 6, "hacker cup")));
    }

    @Test
    public void shouldDoComplexCase2() {
        assertEquals(10, BoardPrinter.getTextSize(new Board(100, 20, "hacker cup 2013")));
    }

    @Test
    public void shouldDoComplexCase3() {
        assertEquals(2, BoardPrinter.getTextSize(new Board(10, 20, "MUST BE ABLE TO HACK")));
    }

    @Test
    public void shouldDoComplexCase4() {
        assertEquals(8, BoardPrinter.getTextSize(new Board(55, 25, "Can you hack")));
    }

    @Test
    public void shouldDoComplexCase5() {
        assertEquals(7, BoardPrinter.getTextSize(new Board(100, 20, "Hack your way to the cup")));
    }

}
