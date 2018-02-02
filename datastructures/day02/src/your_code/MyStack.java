package your_code;
import ADTs.StackADT;

import java.util.LinkedList;
import java.util.Stack;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private Stack<Integer> MaxStack;

    public MyStack() {
        ll = new LinkedList<>();
        MaxStack = new Stack<>();
    }

    @Override
    public void push(Integer e) {
        if (ll.size() == 0)
        {
            MaxStack.push(e);
        }
        else if (e > MaxStack.peek())
        {
            MaxStack.push(e);
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        int pop = ll.removeFirst();
        if (pop == MaxStack.peek())
        {
            MaxStack.pop();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return MaxStack.peek();
    }
}
