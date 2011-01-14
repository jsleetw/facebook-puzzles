package logic;

import java.util.ArrayList;

public class UnitsCalc {

    public static long valueSum(ArrayList<StockKeepingUnit> source) {
        long totalThrown = 0;

        for (StockKeepingUnit unitToThrow : source) {
            totalThrown += unitToThrow.getCost();
        }

        return totalThrown;
    }

    public static long weightSum(ArrayList<StockKeepingUnit> source) {
        long totalThrown = 0;

        for (StockKeepingUnit unitToThrow : source) {
            totalThrown += unitToThrow.getWeight();
        }

        return totalThrown;
    }
}
