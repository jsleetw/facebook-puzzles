package logic;

import java.util.HashMap;
import static java.lang.Math.*;

// TODO: cache found prime numbers and factorisation results
public class IntegerFactorizator {

    private static HashMap<Integer, HashMap<Integer, Integer>> cache;

    public static HashMap<Integer, Integer> factorize(int n) {

        HashMap<Integer, Integer> factors = new HashMap<Integer, Integer>();

        if (n <= 1) {
            return factors;
        }

        for (int i = (int)floor(sqrt(n)); i >= 2; i--) {
            if (n % i == 0) {
                mergeFactors(factors, factorize(i));
                mergeFactors(factors, factorize((int)(n / i)));
                return factors;
            }
        }

        if (factors.isEmpty()) { // It's a prime number
            factors.put(n, 1);
        }

        return factors;
    }

    private static void mergeFactors(HashMap<Integer, Integer> factors, HashMap<Integer, Integer> addition) {

        for(Integer subfactor : addition.keySet()) {
            if (factors.containsKey(subfactor)) {
                factors.put(subfactor, factors.get(subfactor) + addition.get(subfactor));
            } else {
                factors.put(subfactor, addition.get(subfactor));
            }
        }
    }

}
