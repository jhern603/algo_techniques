package Prog15_07;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Prog15_07 {
    int count;

    public Prog15_07() {
        count = 0;
        Scanner in = new java.util.Scanner(System.in);
        String s;

        //        do {
        //            System.out.println("Enter Number:");
        //            s = in.nextLine();

        BigInteger rand =
                randomBigInteger(new BigInteger("170141183460469231731687303715884105727"));
        //            if (!s.equals("q")) {
        //                BigInteger n = new BigInteger(s);
        boolean result = isProbablyPrime(rand, 10000);
        System.out.println(
                rand
                        + " is probably prime: "
                        + result
                        + "\nnumber of "
                        + "checks: "
                        + count);
        //            }
        //        } while (!s.equals("q"));
    }

    public static void main(String[] args) {
        new Prog15_07();
    }

    BigInteger randomBigInteger(BigInteger value) {
        int bitLength = value.bitLength();
        return (new BigInteger(bitLength, new Random()).mod(value));
    }

    private BigInteger exp(BigInteger b, BigInteger x, BigInteger m) {
        return b.modPow(x, m);
    }

    public boolean isProbablyPrime(BigInteger n, int iterations) {
        BigInteger ONE = BigInteger.ONE;
        BigInteger TWO = BigInteger.valueOf(2);
        if (n.compareTo(ONE) <= 0) return false;
        if (n.compareTo(TWO) == 0) return true;

        for (int i = 0; i < iterations; i++) {
            count++;
            BigInteger a = randomBigInteger(n.subtract(TWO));
            a = a.add(TWO);
            if (exp(a, n.subtract(ONE), n).compareTo(ONE) != 0) return false;
        }
        return true;
    }
}
