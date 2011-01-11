import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class PegGame {

    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());
//
//        int i = 0;

        while ((strLine = in.readLine()) != null) {

            String[] data = strLine.split(" ");
            int rowsCount = Integer.parseInt(data[0]);
            int columnsCount = Integer.parseInt(data[1])*2 -1;

            PegField field = new PegField(rowsCount,columnsCount);


            int droppedPegsCount = Integer.parseInt(data[3]);
            for (int pegNumber = 1; pegNumber <= droppedPegsCount; pegNumber++) {
                int droppedPegRow = Integer.parseInt(data[3 + (pegNumber*2 - 1)]);
                int droppedPegCol = Integer.parseInt(data[3 + (pegNumber*2)]);
                field.dropPeg(droppedPegRow, droppedPegCol);
            }

            field.drawField();

            int goalHole = Integer.parseInt(data[2]);
            PegCase pegCase = new PegCase(field, goalHole*2 + 1);
            pegCase.findOptimalEntryPoint();


            System.out.println("-----------");

            System.out.println("Optimal solution: " + pegCase.optimalEntryPoint + " with probability: " + pegCase.optimalProbability);

            System.out.println("-----------");

            System.exit(0);


        }

    }
}
