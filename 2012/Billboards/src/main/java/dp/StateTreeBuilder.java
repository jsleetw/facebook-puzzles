package dp;

import model.Board;

import java.util.*;

public class StateTreeBuilder {

    public static List<Board> getLevelOfStates(Board board) {

        List<Board> result = new ArrayList<Board>();

        List<String> reversedWords = board.getWords();
        Collections.reverse(reversedWords);

        StringBuilder accumulator = new StringBuilder();
        
        for (String word : reversedWords) {
            if (board.getWords().indexOf(word) == 0) {
                continue; // Don't move first word
            }

            accumulator.append(word).append(" ");

            result.add(
                    board.copyReduced(board.getText().length() - accumulator.toString().length())
            );
        }

        return result;
    }
}
