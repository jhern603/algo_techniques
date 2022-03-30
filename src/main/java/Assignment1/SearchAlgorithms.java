package Assignment1;

/**
 * @author Jose Hernandez, 5712864 Class containing search algorithms to find integer "n" in an
 *     array of integers
 */
public class SearchAlgorithms {
    int comparisonCount;

    public SearchAlgorithms() {
        this.comparisonCount = 0;
    }

    public int getComparisonCount() {
        return this.comparisonCount;
    }

    /**
     * Modified sequential search that finds and returns the number of occurences of element "x" in
     * an array arr in O(n)
     *
     * @param n number to search for
     * @param len length of array
     * @param arr array
     * @return number of occurences of n in arr
     */
    public int sequentialSearch(int n, int len, int[] arr) {
        int sizeOfBlock = 0;
        this.comparisonCount = 0;
        boolean found = false;
        for (int i = 0; i < len - 1; i++) {
            this.comparisonCount++;
            if (arr[i] == n) {
                found = true;
                sizeOfBlock++;
            }
            this.comparisonCount++;
            if(arr[i + 1] != n && found)
                break;
        }
        return sizeOfBlock;
    }

    /**
     * Modified binary search that finds and returns the number of occurrences of element "x" in an
     * int array list in O(M+logN)
     *
     * @param list array to be searched
     * @param x element to search for
     * @param len length of the array
     * @return number of occurrences of element "x"
     */
    public int modifiedSearch(int[] list, int x, int len) {
        this.comparisonCount = 0;
        int xCount = 1;
        int midpoint = binarySearch(list, 0, len - 1, x);
        if (midpoint == 0) return 0;
        for (int i = midpoint; i > 0; i--) {
            if (list[i - 1] == x) {
                this.comparisonCount++;
                xCount++;
            }
        }
        for (int i = midpoint + 1; i < len; i++) {
            if (list[i] == x) {
                this.comparisonCount++;
                xCount++;
            }
        }
        return xCount;
    }

    /**
     * Determines if search element is in locations [first, last] of given array. (Borrowed from
     * Prog14_01)
     *
     * @param list sorted array
     * @param first first element in the array to be traversed
     * @param x element to search for
     * @return Returns the position in where "x" is found
     */
    private int binarySearch(int[] list, int first, int last, int x) {
        int position;
        comparisonCount++;
        if (first > last) {
            position = 0;
        } else {
            int mid = (first + last) / 2;
            comparisonCount++;
            if (list[mid] == x) {
                position = mid;
            } else {
                comparisonCount++;
                if (x < list[mid]) {
                    position = binarySearch(list, first, mid - 1, x);
                } else {
                    position = binarySearch(list, mid + 1, last, x);
                }
            }
        }
        return position;
    }

    /**
     * Determines the number of occurrences of element "x" in an array by creating subarrays. The
     * algorithm runs in O(log n)
     *
     * @param list array to be searched through
     * @param x element to look for
     * @param len length of array
     * @return The number of occurrences of x
     */
    public int boundarySearch(int[] list, int x, int len) {
        int leftEnd;
        int rightEnd;
        this.comparisonCount = 0;
        leftEnd = locateLeftEnd(list, 0, len - 1, x);
        rightEnd = locateRightEnd(list, 0, len - 1, x);
        return rightEnd - leftEnd;
    }
    /**
     * Finds the leftmost occurrence of x in a list of ints
     *
     * @param list sorted array
     * @param first left index
     * @param last right index
     * @param x element to search for
     * @return Returns the leftmost position in where "x" was first found
     */
    private int locateLeftEnd(int[] list, int first, int last, int x) {
        int loc;
        this.comparisonCount++;
        if (first > last) loc = first;
        else {
            int mid = (first + last) / 2;

            this.comparisonCount++;
            if (x <= list[mid]) loc = locateLeftEnd(list, first, mid - 1, x);
            else loc = locateLeftEnd(list, mid + 1, last, x);
        }
        return loc;
    }

    /**
     * Finds the Rightmost occurrence of x in a list of ints
     *
     * @param list sorted array
     * @param first left index
     * @param last right index
     * @param x element to search for
     * @return Returns the Rightmost position in where "x" was first found
     */
    private int locateRightEnd(int[] list, int first, int last, int x) {
        int loc;
        this.comparisonCount++;
        if (first > last) loc = first;
        else {
            int mid = (first + last) / 2;

            this.comparisonCount++;
            if (x >= list[mid]) loc = locateRightEnd(list, mid + 1, last, x);
            else loc = locateRightEnd(list, first, mid - 1, x);
        }
        return loc;
    }
}
