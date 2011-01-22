package test;

import logic.Fraction;
import logic.RaceWinner;
import org.junit.*;

import java.math.BigDecimal;

import static junit.framework.Assert.*;

public class FractionTest {

    private Fraction a;
    private Fraction b;
    private Fraction c;
    private Fraction d;

    @Before public void setup() {
        a = new Fraction(5, 7);
        b = new Fraction(3, 8);
        c = new Fraction(80, 35);
        d = new Fraction(120, 60);
    }

    @Test public void addFractions() {
        Fraction result = a.add(b);
        assertEquals(61, result.getNumerator());
        assertEquals(56, result.getDenominator());
    }

    @Test public void multiplicationFractions() {
        Fraction result = a.multiply(b);
        assertEquals(15, result.getNumerator());
        assertEquals(56, result.getDenominator());
    }

    @Test public void reductionFractions() {
        Fraction result = c.reduct();
        assertEquals(16, (int)result.getNumerator());
        assertEquals(7, (int)result.getDenominator());
    }
}