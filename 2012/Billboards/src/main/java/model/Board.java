package model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {


    private static Logger logger = Logger.getLogger(Board.class.getName());

    private long width;

    private long height;

    private long linesOccupied = 0;
    
    private List<String> words;


    private String text;

    public Board(long width, long height) {
        this.width = width;
        this.height = height;
    }

    public Board copyReduced(int size) {
        Board newBoard = new Board(width, height);
        newBoard.linesOccupied = linesOccupied + 1;
        newBoard.text = text.substring(size + 1);
        newBoard.words = Arrays.asList(newBoard.text.split(" "));
        logger.debug("Copying board: '"+newBoard.text+"' " + size);
        return newBoard;
    }

    public Board(long width, long height, String text) {
        logger.info("Creating board: '"+text+"' ");
        this.width = width;
        this.height = height;
        this.text = text;
        this.words = Arrays.asList(text.split(" "));
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
        return this.words;
    }
}
