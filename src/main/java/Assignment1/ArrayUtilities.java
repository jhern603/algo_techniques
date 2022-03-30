package Assignment1;

public class ArrayUtilities{

/** Fills an array with random values and sorts the array after being filled
 *
 * @param list list to be filled and sorted
 */
public void fillArray( int[] list, int bound){
  java.util.Random r = new java.util.Random();
  for ( int i = 0; i < list.length; i++ ) {
    list[ i ] = r.nextInt(bound);
  }

  java.util.Arrays.sort(list);
}

/** Prints the given array
 *
 * @param list array to print
 */
public void printArray( int[] list ){
  System.out.print( "[" );
  for ( int j : list ) {
    System.out.print( j + "," );
  }
  System.out.println("]");
}

/** Helper method that actually performs a quicksort
 *
 * @param list array to be sorted
 * @param begin beginning index of a subarray
 * @param end ending index of a subarray
 */
private void quicksort(int[] list, int begin, int end) {
  int temp;
  int pivot = findPivotLocation(begin, end);
  // swap list[pivot] and list[end]
  temp = list[pivot];
  list[pivot] = list[end];
  list[end] = temp;
  pivot = end;
  int i = begin, j = end - 1;
  boolean iterationCompleted = false;
  while (!iterationCompleted) {
    for (; list[i] < list[pivot]; i++)
      ;
    for (; (j >= 0) && (list[pivot] < list[j]); j--)
      ;
    if (i < j) {
      // swap list[i] and list[j]
      temp = list[i];
      list[i++] = list[j];
      list[j--] = temp;
    } else iterationCompleted = true;
  }
  // swap list[i] and list[pivot]
  temp = list[i];
  list[i] = list[pivot];
  list[pivot] = temp;
  if (begin < i - 1) quicksort(list, begin, i - 1);
  if (i + 1 < end) quicksort(list, i + 1, end);
}

/** Find the pivot location of an array
 *
 * @param b beginning index of a subarray
 * @param e ending index of a subarray
 * @return pivot location
 */
private int findPivotLocation(int b, int e) {
  return (b + e) / 2;
}

}
