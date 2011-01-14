import logic.StockKeepingUnit;
import logic.ThrowSelector;
import logic.ThrowSelectorEnhanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static logic.UnitsCalc.*;

public class UsrBinCrash {

    public static void main(String args[]) throws Exception {

        ArrayList<StockKeepingUnit> units = new ArrayList<StockKeepingUnit>();

        BufferedReader in = new BufferedReader(new FileReader(args[0]));


        long weightToThrow = Long.parseLong(in.readLine());

        String strLine;
        while ((strLine = in.readLine()) != null) {
            String[] pieces = strLine.split(" ");

            units.add(
                    new StockKeepingUnit(
                            pieces[0],
                            Long.parseLong(pieces[1]),
                            Long.parseLong(pieces[2]))
            );
        }

        if (args.length > 1) {
            System.out.println("Cache disabled!!!");
        }

        ArrayList<StockKeepingUnit> unitsToThrow = ThrowSelectorEnhanced.whatToThrow(weightToThrow, units, args.length == 1);

        System.out.println(valueSum(unitsToThrow));

//        for(StockKeepingUnit unit : unitsToThrow) {
//            System.out.println("ToThrow: " + unit.getLabel() + " " + unit.getWeight() + " " + unit.getCost() );
//        }

//        System.out.println("\nCache missed: " + ThrowSelector.cacheMiss + " times and hit " + ThrowSelector.cacheHit + " times");
    }
}
