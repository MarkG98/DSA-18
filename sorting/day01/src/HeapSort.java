public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int childIndex = i;

        if (size > leftChild(i)) {
            if (heap[leftChild(i)] > heap[childIndex])
            {
                childIndex = leftChild(i);
            }
        }
        if (size > rightChild(i)) {
            if (heap[rightChild(i)] > heap[childIndex])
            {
                childIndex = rightChild(i);
            }
        }
        if (childIndex != i)
        {
            swap(heap,i,childIndex);
            sink(childIndex);
        }

    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--)
        {
            swap(array, 0,i);
            size--;
            sink(0);
        }

        return array;
    }
}
