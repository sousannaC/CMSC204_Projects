package notation;

import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
    private ArrayList<T> stack;
    private int maxSize;
    private int top;

    // Default constructor
    public MyStack() {
        this(DEFAULT_SIZE); // Assuming DEFAULT_SIZE is defined
    }

    // Constructor with size parameter
    public MyStack(int size) {
        this.stack = new ArrayList<>(size);
        this.maxSize = size;
        this.top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stack.remove(top--);
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        return stack.get(top);
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        stack.add(++top, e);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(stack.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(stack.get(i)).append(delimiter);
        }
        return sb.toString().replaceAll(delimiter + "$", "");
    }

    @Override
    public void fill(ArrayList<T> list) throws StackOverflowException {
        for (T element : list) {
            push(element);
        }
    }
}
