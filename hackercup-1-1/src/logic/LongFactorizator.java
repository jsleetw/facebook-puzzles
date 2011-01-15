package logic;

import java.util.HashMap;
import static java.lang.Math.*;

// TODO: cache found prime numbers and factorisation results
public class LongFactorizator {

    private static HashMap<Long, HashMap<Long, Long>> cache;

    public static HashMap<Long, Long> factorize(long n) {

        HashMap<Long, Long> factors = new HashMap<Long, Long>();

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
            factors.put(Long.valueOf(n), 1L);
        }

        return factors;
    }

    private static void mergeFactors(HashMap<Long, Long> factors, HashMap<Long, Long> addition) {

        for(Long subfactor : addition.keySet()) {
            if (factors.containsKey(subfactor)) {
                factors.put(subfactor, factors.get(subfactor) + addition.get(subfactor));
            } else {
                factors.put(subfactor, addition.get(subfactor));
            }
        }
    }

}
