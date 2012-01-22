package logic;

import model.Board;

import static java.lang.Math.min;

public class BoardRuler {

    public long getTextSizeForLayout(Board b) {
        return min(
                b.getHeight() / (1 + b.getLinesOccupied()),
                b.getWidth() / b.getText().length()
        );
    }

}
