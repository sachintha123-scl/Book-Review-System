public class MyLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public boolean contains(T value) {
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data.equals(value)) return true;
            cur = cur.next;
        }
        return false;
    }

    public boolean remove(T value) {
        Node<T> cur = head, prev = null;
        while (cur != null) {
            if (cur.data.equals(value)) {
                if (prev == null) head = cur.next; else prev.next = cur.next;
                if (cur == tail) tail = prev;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> current = head;
            @Override public boolean hasNext() { return current != null; }
            @Override public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException("End of list reached.");
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
