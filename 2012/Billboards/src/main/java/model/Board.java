package model;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private long width;

    private long height;

    private long linesOccupied = 0;


    private String text;

    public Board(long width, long height) {
        this.width = width;
        this.height = height;
    }

    public Board copyReduced(int size) {
        Board newBoard = new Board(width, height);
        newBoard.linesOccupied = linesOccupied + 1;
        newBoard.text = text.substring(size + 1);
        return newBoard;
    }

    public Board(long width, long height, String text) {
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public long getLinesOccupied() {
        return linesOccupied;
    }

    public String getText() {
        return text;
    }
    
    public List<String> getWords() {
        return Arrays.asList(text.split(" "));
    }
}
