package logic;

import dp.StateTreeBuilder;
import model.Board;


public class BoardPrinter {

    private static BoardRuler boardRuler = new BoardRuler();


    public static long getTextSize(Board b) {

        long max = boardRuler.getTextSizeForLayout(b);

        for (Board newBoard : StateTreeBuilder.getLevelOfStates(b)) {

            long diffLength = b.getText().length() - newBoard.getText().length() - 1;

            max = Math.max(
                    max,
                    Math.min(b.getWidth() / diffLength, getTextSize(newBoard))
            );
        }


        return max;
    }


}
