package your_code;

import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private ArrayList <Integer> ar;


    public MyPriorityQueue() {
        ar = new ArrayList<>();
    }

    public void enqueue(int item) {
        ar.add(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int max_index = 0;
        int max = ar.get(0);
        for (int i = 0; i < ar.size(); i++)
        {
            if (ar.get(i) > max)
            {
                max = ar.get(i);
                max_index = i;
            }
        }
        ar.remove(max_index);
        return max;
    }
}