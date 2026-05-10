package utils;

public class StaticStack<T> {

    private T[] data;
    private int top = -1;

    @SuppressWarnings("unchecked")
    public StaticStack(int size) {
        data = (T[]) new Object[size];
    }

    public void push(T element) throws Exception {
        if (isFull()) throw new Exception("Stack Overflow");
        data[++top] = element;
    }

    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("Stack Underflow");
        return data[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == data.length - 1;
    }
}