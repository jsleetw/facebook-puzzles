package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import static logic.UnitsCalc.*;

public class ThrowSelector {

    public static int cacheMiss = 0;
    public static int cacheHit = 0;

    private static HashMap<HashMap<ArrayList<StockKeepingUnit>, Long>, ArrayList<StockKeepingUnit>> cache = new HashMap<HashMap<ArrayList<StockKeepingUnit>, Long>, ArrayList<StockKeepingUnit>>();

    public static ArrayList<StockKeepingUnit> whatToThrow(long weightToThrow, ArrayList<StockKeepingUnit> source, boolean useCache) {

        HashMap<ArrayList<StockKeepingUnit>, Long> cacheKey = new HashMap<ArrayList<StockKeepingUnit>, Long>();
        cacheKey.put(source, weightToThrow);

        if (useCache && cache.containsKey(cacheKey)) {
             cacheHit++;
            return cache.get(cacheKey);
        }

        cacheMiss++;

        ArrayList<StockKeepingUnit> result = new ArrayList<StockKeepingUnit>();

        if (weightToThrow <= 0) {
            return result;
        }

        final HashMap<StockKeepingUnit, ArrayList<StockKeepingUnit>> itemsToThrow = new HashMap<StockKeepingUnit, ArrayList<StockKeepingUnit>>();

        for (StockKeepingUnit item : source) {

            ArrayList<StockKeepingUnit> withoutCurrentItem = new ArrayList<StockKeepingUnit>(source);
            withoutCurrentItem.remove(item);

            itemsToThrow.put(item, whatToThrow(weightToThrow - item.getWeight(), withoutCurrentItem, useCache));
        }

        StockKeepingUnit min = Collections.min(itemsToThrow.keySet(), new Comparator<StockKeepingUnit>() {
            public int compare(StockKeepingUnit o1, StockKeepingUnit o2) {
                if (valueSum(itemsToThrow.get(o1)) + o1.getCost() > valueSum(itemsToThrow.get(o2)) + o2.getCost()) {
                    return 1;
                }

                if (valueSum(itemsToThrow.get(o1)) + o1.getCost() == valueSum(itemsToThrow.get(o2)) + o2.getCost()) {
                    return 0;
                }

                return -1;
            }
        });

        result.add(min);
        result.addAll(itemsToThrow.get(min));

        if (useCache) {
            cache.put(cacheKey, result);
        }

        return result;
    }

    public static ArrayList<StockKeepingUnit> whatToThrow(long weightToThrow, ArrayList<StockKeepingUnit> source) {
        return whatToThrow(weightToThrow, source, true);
    }
}
