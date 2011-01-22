package logic;

import java.math.BigDecimal;

public class Fraction {

    private double numerator;
    private double denominator;

    public Fraction(double numerator, double denominator) {
        assert denominator != 0;
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction(long numerator, long denominator) {
        assert denominator != 0;
        this.numerator = Double.valueOf(numerator);
        this.denominator = Double.valueOf(denominator);
    }

    public long getNumerator() {
        return (long)numerator;
    }

    public long getDenominator() {
        return (long)denominator;
    }

    public Fraction add(Fraction b) {
        return new Fraction(numerator* b.getDenominator()+ b.getNumerator() * denominator, denominator * b.getDenominator());
    }

    public Fraction multiply(Fraction b) {
        return new Fraction(numerator* b.getNumerator(), denominator * b.getDenominator()).reduct();
    }

    public Fraction reduct() {

        if (numerator == 1 || denominator == 1 || numerator == 0) {
            return this;
        }

        double cgf;

        if (numerator > denominator) {
            cgf = calculateCommonGreatestFactor(numerator, denominator);
        } else {
            cgf = calculateCommonGreatestFactor(denominator, numerator);
        }

        return new Fraction(numerator/ cgf, denominator / cgf);
    }

    public double value() {
        return Double.valueOf(numerator) / Double.valueOf(denominator);
    }

    public String toString() {
        return getNumerator() + "/" + getDenominator();
    }

    private static double calculateCommonGreatestFactor(double a, double b) {
        double redundant = a;
        double times = 1;
        while(redundant > b) {
            try {
                redundant = a % (b * times);
            } catch (Exception e) {
                System.out.println("a: " + a + " b: " + b);
            }
            times++;
        }

        if (redundant == 0) {
            return b;
        } else {
            return calculateCommonGreatestFactor(b, redundant);
        }
    }
}


