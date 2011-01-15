package logic;

import java.util.ArrayList;
import java.util.HashMap;
import static logic.UnitsCalc.*;

public class ThrowSelectorEnhanced {

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

        if (weightToThrow <= 0 || source.size() == 0) {
            return result;
        }

        ArrayList<StockKeepingUnit> withoutCurrentItem = new ArrayList<StockKeepingUnit>(source);
        withoutCurrentItem.remove(withoutCurrentItem.get(0));

        ArrayList<StockKeepingUnit> toThrowWithoutCurrent = whatToThrow(weightToThrow, withoutCurrentItem, useCache);
        ArrayList<StockKeepingUnit> toThrowWithCurrent = whatToThrow(weightToThrow - source.get(0).getWeight(), withoutCurrentItem, useCache);

        toThrowWithCurrent.add(source.get(0));

        if (withoutCurrentItem.size() == 0) {
            return toThrowWithCurrent;
        }

//        System.out.println("Current item:");
//        System.out.println("    label: " + source.get(0).getLabel() + " " + source.get(0).getWeight() + " " + source.get(0).getCost() );
//        System.out.println("Left:");
//        for(StockKeepingUnit unit : withoutCurrentItem) {
//            System.out.println("    label: " + unit.getLabel() + " " + unit.getWeight() + " " + unit.getCost() );
//        }
//
//        System.out.println("    s: " +source.size()+ " ntt: "+weightToThrow+" w: " + valueSum(toThrowWithCurrent) + "$ ("+weightSum(toThrowWithCurrent)+"kg) : wo" + valueSum(toThrowWithoutCurrent) + "$ (" + weightSum(toThrowWithoutCurrent) + "kg) ");

        if (valueSum(toThrowWithCurrent) < valueSum(toThrowWithoutCurrent) || weightSum(toThrowWithoutCurrent) < weightToThrow) {
            // Take current
//            System.out.println("    +");
            result = toThrowWithCurrent;
        } else {
//            System.out.println("    -");
            // Skip current
            result = toThrowWithoutCurrent;
        }

        if (useCache) {
            cache.put(cacheKey, result);
        }

        return result;
    }

    public static ArrayList<StockKeepingUnit> whatToThrow(long weightToThrow, ArrayList<StockKeepingUnit> source) {
        return whatToThrow(weightToThrow, source, false);
    }
}
