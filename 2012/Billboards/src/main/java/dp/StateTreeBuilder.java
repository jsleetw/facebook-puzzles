package dp;

import model.Board;

import java.util.*;

public class StateTreeBuilder {

    public static List<Board> getLevelOfStates(Board board) {

        List<Board> result = new ArrayList<Board>();

        if (board.getWords().size() <= 1) {
            return result;
        }

        List<String> reversedWords = board.getWords();
        Collections.reverse(reversedWords);

        StringBuilder accumulator = new StringBuilder();

        int i = 0;
        for (String word : reversedWords) {
            i++;
            if (i == board.getWords().size()) {
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
