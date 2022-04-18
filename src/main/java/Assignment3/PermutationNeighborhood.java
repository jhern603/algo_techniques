package Assignment3;

/**
 * @author Jose Hernandez, PID 5712864
 * @author Ziad Malik, PID 6174850
 * @see Prog18_05
 * This Class is the Permutation Neighbor Class that handles all the Locality Functionality of the Solution at hand
 * It will move forward and look to its neighbors for better solutions. If a Better one is found it will move onto that
 * solution and continue
 */
public class PermutationNeighborhood {
    private final int[] p; //permutation
    private final int SIZE; //size of permutation
    private int loc1; //loc1 and loc2 are the locations of
    private int loc2; //p that will be swapped next

    /**
     * Initializes permutation of this object and locations
     * whose values will be swapped.
     *
     * @param a permutation whose neighborhood is to be generated
     */
    public PermutationNeighborhood(int[] a) {
        SIZE = a.length;
        p = new int[SIZE];
        System.arraycopy(a, 0, p, 0, SIZE);
        loc1 = 0;
        loc2 = 1;
    }

    /**
     * pseudo-iterator object to see if the permutation has a neighbor
     *
     * @return boolean value if the object has a neighbor
     */
    public boolean hasNext() {
        return loc1 != SIZE - 1;
    }

    /**
     * Returns next permutation neighbor.
     *
     * @return next permutation neighbor, if one exists; null otherwise
     */
    public int[] next() {
        if (hasNext()) {
            //copy p to a
            int[] a = new int[SIZE];
            System.arraycopy(p, 0, a, 0, SIZE);

            //exchange elements at locations loc1 and loc2
            a[loc1] = p[loc2];
            a[loc2] = p[loc1];
            //advance loc1 and loc2
            if (loc2 == SIZE - 1) {
                loc1++;
                loc2 = loc1 + 1;
            } else
                loc2++;
            return a;
        } else
            return null;
    }
}
