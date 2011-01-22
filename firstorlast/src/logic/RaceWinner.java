package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static logic.RaceUtils.*;

public class RaceWinner {

    private static HashMap<HashMap<ArrayList<Turn>, Integer>, ArrayList<Turn>> cache = new HashMap<HashMap<ArrayList<Turn>, Integer>, ArrayList<Turn>>();

    public static ArrayList<Turn> findTakeoverTurns(int count, ArrayList<Turn> allTurns) {

        assert allTurns.size() >= count;

        HashMap<ArrayList<Turn>, Integer> cacheKey = new HashMap<ArrayList<Turn>, Integer>();
        cacheKey.put(allTurns, count);

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        if (count == 0) {
            return new ArrayList<Turn>();
        }

        if (allTurns.size() == count) {
            return allTurns;
        }

        ArrayList<Turn> withoutCurrent = new ArrayList<Turn>(allTurns);
        withoutCurrent.remove(0);

        ArrayList<Turn> resultWithoutCurrent = findTakeoverTurns(count, withoutCurrent);

        ArrayList<Turn> result = findTakeoverTurns(count - 1, withoutCurrent);
        result.add(allTurns.get(0));

        ArrayList<Turn> passesWithoutCurrent = new ArrayList<Turn>(allTurns);
        passesWithoutCurrent.removeAll(resultWithoutCurrent);

        ArrayList<Turn> passes = new ArrayList<Turn>(allTurns);
        passes.removeAll(result);


        Fraction winProbabilityWithout = sumProbabilityOfSuccessfulOvertakes(
                resultWithoutCurrent
        ).multiply(sumProbabilityOfSuccessfulPasses(
                passesWithoutCurrent
        ));

        Fraction winProbabilityWith = sumProbabilityOfSuccessfulOvertakes(
                result
        ).multiply(sumProbabilityOfSuccessfulPasses(
                passes
        ));


        if (winProbabilityWithout.value() > winProbabilityWith.value()) {
            cache.put(cacheKey, new ArrayList<Turn>(resultWithoutCurrent));
            return resultWithoutCurrent;
        } else {
            cache.put(cacheKey, new ArrayList<Turn>(result));
            return result;
        }
    }
}
