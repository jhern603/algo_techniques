package Prog15_06;

import java.util.Random;

public class Prog15_06 {
    public static void main(String[] args) {
        int x = 1000000000;
            System.out.println(isProbablyPrime(x, x));
    }

    public static boolean isProbablyPrime(int n, int a) {
        if (n < 2) return false;
        if (n == 2) return true;

        Random rand = new Random();

        for (int i = 0; i < a; i++) {
            int x = rand.nextInt(n - 2) + 2;
            if (power(x, n - 1) % n != 1) return false;
        }
        return true;
    }

    public static int power(int x, int n) {
        int pow = 1;

        // loop till `n` become 0
        while (n > 0) {
            // if `n` is odd, multiply the result by `x`
            if ((n & 1) == 1) {
                pow *= x;
            }

            // divide `n` by 2
            n = n >> 1;

            // multiply `x` by itself
            x = x * x;
        }

        // return result
        return pow;
    }
}
