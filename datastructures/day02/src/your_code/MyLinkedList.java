package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node newN = new Node(c);
        if (tail != null)
        {
            tail.next = newN;
            newN.prev = tail;
        }
        if (head == null)
        {
            head = newN;
        }
        tail = newN;
        size++;
    }

    public void addFirst(Chicken c) {
        Node newN = new Node(c);
        if (size == 0)
        {
            head = newN;
        }
        else
        {
            head.prev = newN;
            newN.next = head;
        }
        head = newN;
        size++;
    }

    public Chicken get(int index){
        Node temp = head;
        for (int i = 0; i < size; i++)
        {
            if (i == index)
            {
                return temp.val;
            }
            else
            {
                temp = temp.next;
            }
        }
        return null;
    }

    public Chicken remove(int index) {
        Chicken element;

        if (index == 0)
        {
            element = removeFirst();
        }
        else if (index == size - 1)
        {
            element = removeLast();
        }
        else
        {
            Node temp = head;
            for (int i = 0; i < size - 1; i++)
            {
                if (i == index)
                {
                    element = get(i);
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    size--;
                    return element;
                }
                else
                {
                    temp = temp.next;
                }
            }
            return null;
        }
        return element;
    }

    public Chicken removeFirst() {
        Node to_remove = head;

        if (size == 0)
        {
            return null;
        }
        else if (size == 1)
        {
            head = null;
            tail = null;
        }
        else
        {
            head = head.next;
            head.prev = null;
        }
        size--;
        return to_remove.val;
    }

    public Chicken removeLast() {
        Node to_remove = tail;

        if (size == 0)
        {
            return null;
        }
        else if (size == 1)
        {
            head = null;
            tail = null;
        }
        else
        {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return to_remove.val;
    }
}