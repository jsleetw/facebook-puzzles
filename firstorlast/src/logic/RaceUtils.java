package logic;

import java.util.ArrayList;

public class RaceUtils {

    public static Fraction sumProbabilityOfSuccessfulOvertakes(ArrayList<Turn> source) {
        Fraction sum = new Fraction(1D, 1D);

        for (Turn turn : source) {
            sum = sum.multiply(
                new Fraction(1, 1).add(new Fraction(-1, turn.getBadOvertakeProbability()))
            );
        }

        return sum;
    }

    public static Fraction sumProbabilityOfSuccessfulPasses(ArrayList<Turn> source) {
         Fraction sum = new Fraction(1, 1);

        for (Turn turn : source) {
            sum = sum.multiply(
                new Fraction(1, 1).add(new Fraction(-1L, turn.getBadPassProbability()))
            );
        }

        return sum;
    }

    public static Fraction getTotalProbability(ArrayList<Turn> source, ArrayList<Turn> chosenTurns) {
        source.removeAll(chosenTurns);
        return sumProbabilityOfSuccessfulOvertakes(chosenTurns).multiply(sumProbabilityOfSuccessfulPasses(source));
    }
}
