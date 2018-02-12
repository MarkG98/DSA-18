
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */

    @Override

    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++)
        {
            int p = i;
            while (array[p] < array[p - 1])
            {
                swap(array, p - 1, p);
                if (p == 1)
                {
                    break;
                }
                p--;
            }
        }
        return array;
    }
}
