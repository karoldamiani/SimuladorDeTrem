package utils;

public class StaticQueue<T> {

    private T[] data;
    private int first = 0;
    private int last = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public StaticQueue(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public void enqueue(T element) throws Exception {
        if (isFull()) throw new Exception("Queue Overflow");
        data[last] = element;
        last = (last + 1) % data.length;
        size++;
    }

    public T dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Queue Underflow");
        T element = data[first];
        first = (first + 1) % data.length;
        size--;
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }
}