package design.iterator;


public class IteratorImpl<E> implements Iterator<E> {

    private int size, cursor;

    private Object[] container = new Object[10];

    public IteratorImpl() {
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public E next() {
        E e = (E) container[cursor];
        cursor++;
        return e;
    }

    public void add(E e) {
        if (container.length == size) {
            Object[] newContainer = new Object[size * 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
            return;
        }
        container[size] = e;
        size++;
    }

    public static void main(String[] args) {
        IteratorImpl<Integer> iterator = new IteratorImpl<>();
        iterator.add(1);
        iterator.add(19);
        iterator.add(12);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
