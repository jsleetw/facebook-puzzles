package logic;

import java.lang.Math.*;

public class WaysCounter {

    public static int countWays(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        int initialValue = (int)Math.ceil(Math.sqrt(n/2));

        int x;
        double diff;
        int count = 0;

        for (x=0; x<initialValue; x++) {
            diff = Math.sqrt(n - x*x);
            if (diff == Math.ceil(diff)) {
                count ++;
            }
        }

        return count;
    }
}
