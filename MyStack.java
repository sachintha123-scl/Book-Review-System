public class MyStack<T> {

    private static class SNode<T> {
        T data;
        SNode<T> next;
        SNode(T data) { this.data = data; }
    }

    private SNode<T> top;
    private int size;

    public void push(T value) {
        SNode<T> node = new SNode<>(value);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty — no navigation history yet.");
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty.");
        return top.data;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public MyLinkedList<T> toList() {
        MyLinkedList<T> list = new MyLinkedList<>();
        SNode<T> cur = top;
        while (cur != null) { list.addLast(cur.data); cur = cur.next; }
        return list;
    }
}
