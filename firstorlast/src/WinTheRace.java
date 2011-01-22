import logic.RaceUtils;
import logic.RaceWinner;
import logic.Turn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class WinTheRace {


    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;

        int linesNumber = Integer.parseInt(in.readLine());

        while ((strLine = in.readLine()) != null) {
            String[] data = strLine.split(" ");
            int carsCount = Integer.parseInt(data[0]);
            int turnsCount = Integer.parseInt(data[1]);

            ArrayList<Turn> turns = new ArrayList<Turn>();

            for (int turnNumber = 1; turnNumber <= turnsCount; turnNumber++) {
                int crashTakeoverProb = Integer.parseInt(data[1 + (turnNumber*2 - 1)]);
                int crashPassProb = Integer.parseInt(data[1 + (turnNumber*2)]);
                turns.add(new Turn(crashTakeoverProb, crashPassProb));
            }

            System.out.println(
                    RaceUtils.getTotalProbability(
                            turns,
                            RaceWinner.findTakeoverTurns(carsCount - 1, turns)
                    ).reduct().toString()
            );

        }

    }
}
