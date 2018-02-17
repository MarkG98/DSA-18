import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length == 1)
        {
            return array;
        }
        else if (array.length <= 10)
        {
            InsertionSort IS = new InsertionSort();
            return IS.sort(array);
        }

        int[] left = Arrays.copyOfRange(array,0,array.length/2);
        int[] right = Arrays.copyOfRange(array,array.length/2, array.length);

        left = sort(left);
        right = sort(right);

        return merge(left,right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {

        int[] s = new int[a.length + b.length];

        int x = 0;
        int y = 0;
        int l = 0;

        while (x < a.length && y < b.length)
        {
            if(a[x] <= b[y])
            {
                s[l] = a[x];
                x++;
                l++;
            }
            else
            {
                s[l] = b[y];
                y++;
                l++;
            }
        }

        while (x < a.length)
        {
            s[l] = a[x];
            x++;
            l++;
        }
        while(y < b.length)
        {
            s[l] = b[y];
            y++;
            l++;
        }
        return s;
    }

}
