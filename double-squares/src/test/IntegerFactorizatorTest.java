package test;

import logic.IntegerFactorizator;
import org.junit.*;

import java.util.HashMap;

import static junit.framework.Assert.*;

public class IntegerFactorizatorTest {

    @Test public void checkTrivialFactorization() {

        checkFactorization(1, toArray(), toArray());
        checkFactorization(2, toArray(2), toArray(1));
        checkFactorization(3, toArray(3), toArray(1));
        checkFactorization(4, toArray(2), toArray(2));
        checkFactorization(5, toArray(5), toArray(1));
        checkFactorization(6, toArray(3,2), toArray(1,1));
        checkFactorization(12, toArray(3,2), toArray(1,2));
        checkFactorization(42, toArray(7,3,2), toArray(1,1,1));
        checkFactorization(144, toArray(3,2), toArray(2,4));
        checkFactorization(2008000000, toArray(2,251,5), toArray(9,1,6));
    }

    // Helper
    private void checkFactorization(int n, int[] factors, int[] powers) {
        assert factors.length == powers.length;
        HashMap<Integer, Integer> result = IntegerFactorizator.factorize(n);
        assertEquals(factors.length, result.size());

        for (int i=0; i<factors.length; i++) {
            assertEquals(powers[i], (int)result.get(factors[i]));
        }
    }

    private static int[] toArray(int... ints) {
        return ints;
    }
}