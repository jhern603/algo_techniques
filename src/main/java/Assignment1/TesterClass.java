package Assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TesterClass {

    public static void main(String[] args) {

        String outputFile = "output-large-block.csv";
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.exit(1);
        }

        SearchAlgorithms search = new SearchAlgorithms();
        ArrayUtilities util = new ArrayUtilities();

        for (int i = 0; i <= 10000; i++) {
            int[] nums = new int[i+10];
            util.fillArray(nums, 20);
            int searchFor = new Random().nextInt(20);

            // O(n)
            System.out.println(search.sequentialSearch(searchFor, nums.length, nums));
            int sequentialComparisons = search.getComparisonCount();

            // O(M+log(n))
            System.out.println(search.boundarySearch(nums, searchFor, nums.length));
            int boundaryComparisons = search.getComparisonCount();

            // O(log(n))
            System.out.println(search.modifiedSearch(nums, searchFor, nums.length));
            int modifiedComparisons = search.getComparisonCount();

            output.println(
                    i
                            + ","
                            + sequentialComparisons
                            + ","
                            + modifiedComparisons
                            + ","
                            + boundaryComparisons);
        }

        output.close();
    }
}
