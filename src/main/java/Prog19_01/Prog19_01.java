package Prog19_01;

import java.util.Arrays;
import java.util.Random;

public class Prog19_01 {
    public Prog19_01() {
        for (int i = 0; i < 10; i++) {
            int[] a = new int[6];
            randomPermutation(a);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        new Prog19_01();
    }

    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        Random r = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int randLoc = r.nextInt(i + 1);
            if (randLoc != i) {
                int temp = a[i];
                a[i] = a[randLoc];
                a[randLoc] = temp;
            }
        }
    }
}
