public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {

        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {

        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        if (size >= elems.length)
        {
            Cow[] bigger = new Cow[elems.length * 2];
            System.arraycopy(elems,0, bigger, 0, elems.length);
            elems = bigger;
        }
        if (size < 0.25*elems.length)
        {
            Cow[] smaller = new Cow[elems.length / 2];
            System.arraycopy(elems,0, smaller, 0, elems.length / 2);
            elems = smaller;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < 0 || index >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elems[index];
    }

    // TODO: Runtime: O(n)
    public Cow remove(int index) {
        Cow element = get(index);
        for (int i = index; i < size - 1; i++)
        {
            elems[i] = elems[i + 1];
        }
        size--;
        return element;
    }

    // TODO: Runtime: O(n)
    public void add(int index, Cow c) {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        add(c);
        for (int i = size - 1; i > index; i--)
        {
            elems[i] = elems[i - 1];
        }
        elems[index] = c;
    }
}
