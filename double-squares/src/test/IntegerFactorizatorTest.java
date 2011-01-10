package test;

import logic.IntegerFactorizator;
import org.junit.*;

import java.util.HashMap;

import static junit.framework.Assert.*;

public class IntegerFactorizatorTest {

    @Test public void checkTrivialFactorization() {

        checkFactorization(1, new int[]{}, new int[]{});
        checkFactorization(2, new int[]{2}, new int[]{1});
        checkFactorization(3, new int[]{3}, new int[]{1});
        checkFactorization(4, new int[]{2}, new int[]{2});
        checkFactorization(5, new int[]{5}, new int[]{1});
        checkFactorization(6, new int[]{3,2}, new int[]{1,1});
        checkFactorization(12, new int[]{3,2}, new int[]{1,2});
        checkFactorization(42, new int[]{7,3,2}, new int[]{1,1,1});
        checkFactorization(144, new int[]{3,2}, new int[]{2,4});
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
}