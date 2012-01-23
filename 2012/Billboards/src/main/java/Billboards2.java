
import logic.BoardPrinter;
import model.Board;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Billboards2 {

    private static Logger logger = Logger.getLogger(Billboards.class.getName());

    public static void main(String... args) throws Exception {
        List<String> lines = FileUtils.readLines(new File(args[0]), "UTF-8");
        List<String> results = new ArrayList<String>();



        Integer numberOfCases = new Integer(lines.get(0));

        logger.info("Number of cases: " + numberOfCases);

        for (int i = 1; i<= numberOfCases; i++) {

            BoardPrinter.clearCache();

            logger.info("On case" + i);

            String line = lines.get(i);
            String[] values = line.split(" ");

            Board board = new Board(new Long(values[0]), new Long(values[1]), line.substring(values[0].length() + values[1].length() + 2));

            results.add("Case #" + i + ": " + getSize(board));
        }

        FileUtils.writeLines(new File(args[1]), results);
    }

    private static int getSize(Board board) {

        boolean outOfTable = false;
        int currentFontSize = 0;

        String[][] strings = new String[(int)board.getHeight()][board.getWords().size()];



        int i = 0;
        for (String s : board.getWords()) {
            strings[0][i] = s;
            i++;
        }

        do {

            currentFontSize++;
            logger.info("Font size: " + currentFontSize);

            int lineNumber  = 0;

            for (String[] line : strings) {

                List<String> wordsToMove = new ArrayList<String>();

                int currentLineSize = 0;
                int wordIndex = 0;

                for (String word : line) {
                    if (word == null) {
                        break;
                    }

                    currentLineSize += word.length()* currentFontSize;
                    if (wordIndex != 0)  {
                        currentLineSize += currentFontSize;
                    }

                    if (currentLineSize > board.getWidth()) {
                        wordsToMove.add(word);
                        line[wordIndex] = null;
                    }
                    wordIndex++;
                }


                if (wordIndex > 0) {
                    logger.info("Line: " + lineNumber + " : " + StringUtils.join(line, " "));
                    logger.info("Current line number: " + lineNumber);
                    logger.info("Current line size: " + currentLineSize);
                    logger.info("Words to move: " + wordsToMove.size());
                }

                try {
                    if (!wordsToMove.isEmpty()) {

                        String[] newNextLine = new String[(int) board.getWidth()];
                        int newNextLineIndex = 0;
                        for (String s : wordsToMove) {
                            newNextLine[newNextLineIndex] = s;
                            newNextLineIndex++;
                        }

                        for (String s : strings[lineNumber + 1]) {
                            if (s == null) {
                                break;
                            }
                            newNextLine[newNextLineIndex] = s;
                            newNextLineIndex++;
                        }


                        logger.info("New next line: " + (lineNumber + 1) + " : " + StringUtils.join(newNextLine, " "));

                        strings[lineNumber + 1] = newNextLine;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.warn("Out of table because: " + e.getMessage());
                    return currentFontSize - 1;
                }

                if (getNumberOfBusyLines(strings) * currentFontSize > board.getHeight()) {
                    logger.warn("Out of table because height too large: " + getNumberOfBusyLines(strings) * currentFontSize + "("+getNumberOfBusyLines(strings)+" lines)");
                    outOfTable = true;
                    return currentFontSize - 1;
                }

                lineNumber++;

            }

        } while (!outOfTable);


        // Check width


        // Check height

        return currentFontSize;

    }
    
    private static int getNumberOfBusyLines(String[][] lines) {
        int busyLines = 0;
        for (String[] line : lines) {
            boolean  busy = false;
            for (String words : line) {
                if (words != null) {
                    busy = true;
                    break;
                }
            }
            if (busy) {
                busyLines++;
            }else {
                return busyLines;
            }
            

        }
        return 0;

    }
}
