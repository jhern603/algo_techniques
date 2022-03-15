package Prog16_02;

import java.util.ArrayList;

import static java.lang.System.out;

public class Prog16_02 {

    int count = 0;

    public static void main(String[] args) {
        new Prog16_02();
    }

    public Prog16_02() {
        int n = 11;
        printPermutations(n);
    }

    public void printPermutations(int n) {
        printPermutations(new int[n], 0);
    }

    private void printPermutations(int[] a, int k) {
        if ( k == a.length ) printArray(a);
        else {
            ArrayList<Integer> Sk = constructCandidateSe(a, k);
            for (int s : Sk) {
                a[k] = s;
                printPermutations(a, k + 1);
            }
        }
    }

    private ArrayList<Integer> constructCandidateSe(int[] a, int k) {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];
        for (int i = 0; i < k; i++) b[a[i]] = true;
        for (int i = 0; i < a.length; i++) if (!b[i]) candidates.add(i);

        return candidates;
    }

    private void printArray(int[] a) {
        out.printf("%4d: ", ++count);
        for (int v : a) out.print(v + " ");
        out.println();
    }
}
