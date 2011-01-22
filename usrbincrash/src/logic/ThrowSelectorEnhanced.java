package logic;

import java.util.ArrayList;
import java.util.HashMap;
import static logic.UnitsCalc.*;

public class ThrowSelectorEnhanced {

    public static int cacheMiss = 0;
    public static int cacheHit = 0;

    private static HashMap<HashMap<Long, ArrayList<StockKeepingUnit>>, ArrayList<StockKeepingUnit>> cache = new HashMap<HashMap<Long, ArrayList<StockKeepingUnit>>, ArrayList<StockKeepingUnit>>();

    public static ArrayList<StockKeepingUnit> whatToThrow(long weightToThrow, ArrayList<StockKeepingUnit> source, boolean useCache) {

        useCache = false;

        HashMap<Long, ArrayList<StockKeepingUnit>> cacheKey = new HashMap<Long, ArrayList<StockKeepingUnit>>();
        cacheKey.put(new Long(weightToThrow), source);

        if (useCache && cache.containsKey(cacheKey)) {
             cacheHit++;
            return new ArrayList<StockKeepingUnit>(cache.get(cacheKey));
        }

        cacheMiss++;

        ArrayList<StockKeepingUnit> result = new ArrayList<StockKeepingUnit>();

        if (weightToThrow <= 0 || source.size() == 0) {
            return result;
        }

        long amountOfLessValWeCanSafelyThrow = Math.max((weightToThrow - weightSum(source)) / source.get(0).getWeight(), 0);

        for (long i = 0; i < amountOfLessValWeCanSafelyThrow; i++) {
            result.add(source.get(0));
        }


        weightToThrow -= amountOfLessValWeCanSafelyThrow * source.get(0).getWeight();

        if (weightToThrow <= 0) {
//            if (useCache) {
//                cache.put(cacheKey, new ArrayList<StockKeepingUnit>(result));
//            }
            return result;
        }

        ArrayList<StockKeepingUnit> withoutCurrentItem = new ArrayList<StockKeepingUnit>(source);
        withoutCurrentItem.remove(withoutCurrentItem.get(0));

        ArrayList<StockKeepingUnit> toThrowWithoutCurrent = whatToThrow(weightToThrow, withoutCurrentItem, useCache);
        ArrayList<StockKeepingUnit> toThrowWithCurrent = whatToThrow(weightToThrow - source.get(0).getWeight(), source, useCache);

        toThrowWithCurrent.add(source.get(0));

        if (withoutCurrentItem.size() == 0) {
            result.addAll(toThrowWithCurrent);
//            if (useCache) {
//                cache.put(cacheKey, new ArrayList<StockKeepingUnit>(result));
//            }
            return result;
        }

        if (valueSum(toThrowWithCurrent) < valueSum(toThrowWithoutCurrent) || weightSum(toThrowWithoutCurrent) < weightToThrow) {
            // Take current
            result.addAll(toThrowWithCurrent);
        } else {
            // Skip current
            result = toThrowWithoutCurrent;
        }

        if (useCache) {
            cache.put(cacheKey, new ArrayList<StockKeepingUnit>(result));
        }

        return result;
    }

    public static ArrayList<StockKeepingUnit> whatToThrow(long weightToThrow, ArrayList<StockKeepingUnit> source) {
        return whatToThrow(weightToThrow, source, false);
    }
}
