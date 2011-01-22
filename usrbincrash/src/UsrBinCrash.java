import logic.StockKeepingUnit;
import logic.ThrowSelectorEnhanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static logic.UnitsCalc.*;

public class UsrBinCrash {

    public static void main(String args[]) throws Exception {

        ArrayList<StockKeepingUnit> units = new ArrayList<StockKeepingUnit>();

        BufferedReader in = new BufferedReader(new FileReader(args[0]));


        long weightToThrow = Long.parseLong(in.readLine().replaceAll("\\s", ""));

        String strLine;
        while ((strLine = in.readLine()) != null) {
            String[] pieces = strLine.split("(\\s+|\\W+|\\s+\\t+)");

            units.add(
                    new StockKeepingUnit(
                            pieces[0],
                            Long.parseLong(pieces[1].replaceAll("\\s", "")),
                            Long.parseLong(pieces[2].replaceAll("\\s", "")))
            );
        }
//
//        if (args.length > 1) {
//            System.out.println("Cache disabled!!!");
//        }

        Collections.sort(units, new Comparator<StockKeepingUnit>() {
            public int compare(StockKeepingUnit o1, StockKeepingUnit o2) {
                if (o1.getPricePerWeightUnit() < o2.getPricePerWeightUnit()) {
                    return -1;
                } else if(o1.getPricePerWeightUnit() > o2.getPricePerWeightUnit()) {
                    return 1;
                } else if(o1.getWeight() > o2.getWeight()) {
                    return -1;
                }
                return 0;
            }
        });

//        System.out.println(units);

        ArrayList<StockKeepingUnit> unitsToThrow = ThrowSelectorEnhanced.whatToThrow(weightToThrow, units, args.length == 1);

//        System.out.println(unitsToThrow);
        System.out.println(valueSum(unitsToThrow));
//
//        for(StockKeepingUnit unit : unitsToThrow) {
//            System.out.println("ToThrow: " + unit.getLabel() + " " + unit.getWeight() + " " + unit.getCost() );
//        }

    }
}
