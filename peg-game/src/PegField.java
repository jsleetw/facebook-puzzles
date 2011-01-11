import java.util.Arrays;

public class PegField {

    private final String WALL = "W";
    private final String PEG = "P";
    private final String HOLE = ".";

    private int rows;
    private int cols;

    private String[][] field;

    public PegField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        field = new String[rows][cols];
        buildField();
    }

    public PegField(String[][] field) {
        this.rows = field.length;
        this.cols = field[0].length;
        this.field = field;
    }

    public PegField cloneWithoutLastRow() {
        String[][] clonedField = Arrays.copyOfRange(this.field, 0, this.field.length-1);
        return new PegField(clonedField);
    }

    public PegField cloneWithoutFirstRow() {
        String[][] clonedField = Arrays.copyOfRange(this.field, 1, this.field.length);
        return new PegField(clonedField);
    }

    public void dropPeg(int row, int col) {
        System.out.println("Dropping peg: " + row + " " + col);
        int actualCol = 0;
        for (int c = 0; c < field[row].length; c++) {
            if (field[row][c] != PEG) {
                continue;
            }

            if (actualCol == col) {
                field[row][c] = HOLE;
                return;
            }

            actualCol++;
        }
    }

    private void buildField() {
        for(int r=0; r < this.rows; r++) {
            String edgeElement = (r % 2 == 0) ? PEG : WALL;
            field[r][0] = edgeElement;
            field[r][this.cols-1] = edgeElement;

            for(int c=1; c < this.cols-1; c++) {
                field[r][c] = (field[r][c-1] != PEG) ? PEG : HOLE;
            }
        }
    }

    public void drawField() {
        for (String[] row : field) {
            for(String point : row) {
                System.out.print(point);
            }
            System.out.println("");
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public String point(int r, int c) {
        return field[r][c];
    }

    public boolean isHole(int r, int c) {
        return point(r, c).equals(HOLE);
    }

    public boolean isWall(int r, int c) {
        return point(r, c).equals(WALL);
    }

    public boolean isPeg(int r, int c) {
        return point(r, c).equals(PEG);
    }
}
