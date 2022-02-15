package Assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TesterClass {

    public static void main(String[] args) {

        String outputFile = "output.csv";
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            System.exit(1);
        }

        SearchAlgorithms search = new SearchAlgorithms();
        ArrayUtilities util = new ArrayUtilities();

        int sizeOfInput = 150000;
        for (int i = 0; i <= sizeOfInput; i+=10) {
            int[] nums = new int[i];
            util.fillArray(nums, sizeOfInput);
            int searchFor = new Random().nextInt(sizeOfInput);

            search.sequentialSearch(searchFor, nums.length, nums);
            int sequentialComparisons = search.getComparisonCount();

            search.boundarySearch(nums, searchFor, nums.length);
            int boundaryComparisons = search.getComparisonCount();

            search.modifiedSearch(nums, searchFor, nums.length);
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
