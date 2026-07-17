public class MyQueue<T> {

    private static class QNode<T> {
        T data;
        QNode<T> next;
        QNode(T data) { this.data = data; }
    }

    private QNode<T> front;
    private QNode<T> rear;
    private int size;
    private final int maxCapacity;

    public MyQueue(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public MyQueue() { this(-1); }

    public void enqueue(T value) {
        QNode<T> node = new QNode<>(value);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
        if (maxCapacity > 0 && size > maxCapacity) {
            dequeue();
        }
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty — no activity recorded yet.");
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty.");
        return front.data;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public MyLinkedList<T> toList() {
        MyLinkedList<T> list = new MyLinkedList<>();
        QNode<T> cur = front;
        while (cur != null) { list.addLast(cur.data); cur = cur.next; }
        return list;
    }
}
