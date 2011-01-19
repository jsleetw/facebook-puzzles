import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.rtree.RTree;
import gnu.trove.TIntProcedure;
import logic.Friend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

public class SmallWorld {

    public static void main(String args[]) throws Exception {

        final ArrayList<Friend> friends = new ArrayList<Friend>();

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        while ((strLine = in.readLine()) != null) {
            String[] pieces = strLine.split(" ");

            friends.add(
                    new Friend(
                            pieces[0],
                            Float.parseFloat(pieces[1]),
                            Float.parseFloat(pieces[2]))
            );
        }

        RTree rtree = new RTree();
        Properties p = new Properties();
        p.setProperty("MinNodeEntries", Integer.toString(10));
        p.setProperty("MaxNodeEntries", Integer.toString(20));

        rtree.init(p);



        for (int i = 0; i < friends.size(); i++) {
            Friend friend = friends.get(i);
            rtree.add(friend.getRectangle(), i);
        }

        for (final Friend f : friends) {

            final StringBuffer b = new StringBuffer(f.getId() + " ");

            rtree.nearestN(f.getPoint(), new TIntProcedure() {
                public boolean execute(int i) {
                    if (friends.get(i) == f) {
                        return true;
                    }
                    b.append(friends.get(i).getId() + ",");
                    return true;
                }
            }, 4, Integer.MAX_VALUE);
            System.out.println(b.substring(0, b.length()-1));
        }

//   1  0.0      0.0
//2  10.1     -10.1
//3  -12.2    12.2
//4  38.3     38.3
//5  79.99    179.99




//
//        if (args.length > 1) {
//            System.out.println("Cache disabled!!!");
//        }
//
//        ArrayList<Friend> unitsToThrow = ThrowSelectorEnhanced.whatToThrow(weightToThrow, friends, args.length == 1);
//
//        System.out.println(valueSum(unitsToThrow));
//
//        for(Friend unit : unitsToThrow) {
//            System.out.println("ToThrow: " + unit.getLabel() + " " + unit.getWeight() + " " + unit.getCost() );
//        }

//        System.out.println("\nCache missed: " + ThrowSelector.cacheMiss + " times and hit " + ThrowSelector.cacheHit + " times");
    }
}
