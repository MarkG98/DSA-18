import java.util.LinkedList;

public class CountingSort {

    /**
<<<<<<< HEAD
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
=======
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
>>>>>>> 6ef5cd060b5c2a2e260f93ca04e6a62dc4bfacbc
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = A[0];
        int array_counter = 0;

        for (int i = 1; i < A.length; i++)
        {
            if (A[i] > max)
            {
                max = A[i];
            }
        }

        // initialize array of "max" number of linked lists

        LinkedList<Integer>[] B = new LinkedList[max + 1];

        for (int j = 0; j < B.length; j++)
        {
            B[j] = new LinkedList<Integer>();
        }

        // fill sort list
        for (int k = 0; k < A.length; k++)
        {
           B[A[k]].add(A[k]);
        }

        for (int f = 0; f < B.length; f++)
        {
            if (B[f].size() != 0)
            {
                for (int l = 0; l < B[f].size(); l++)
                {
                    A[array_counter] = B[f].get(l);
                    array_counter++;
                }
            }
        }
    }

}
