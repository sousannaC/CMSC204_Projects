package notation;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a generic queue.
 * 
 * @param <T> the data type of elements in the queue
 */
public class MyQueue<T> implements QueueInterface<T> {
    private List<T> queue;
    private int maxSize;

    // Default size of the queue
    private static final int DEFAULT_SIZE = 10;

    /**
     * Default constructor. Uses the default size.
     */
    public MyQueue() {
        this.queue = new ArrayList<>(DEFAULT_SIZE);
        this.maxSize = DEFAULT_SIZE;
    }

    /**
     * Constructor that takes an int as the size of the queue.
     * 
     * @param size the size of the queue
     */
    public MyQueue(int size) {
        this.queue = new ArrayList<>(size);
        this.maxSize = size;
    }

    /**
     * Determines if Queue is empty.
     * 
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Determines if the Queue is full.
     * 
     * @return true if Queue is full, false if not
     */
    @Override
    public boolean isFull() {
        return queue.size() == maxSize;
    }

    /**
     * Deletes and returns the element at the front of the Queue.
     * 
     * @return the element at the front of the Queue
     * @throws QueueUnderflowException if queue is empty
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty. Cannot dequeue.");
        }
        return queue.remove(0);
    }

    /**
     * Returns the number of elements in the Queue.
     * 
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * Adds an element to the end of the Queue.
     * 
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful
     * @throws QueueOverflowException if queue is full
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException("Queue is full. Cannot enqueue.");
        }
        return queue.add(e);
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the
     * string is the front of the queue.
     * 
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : queue) {
            sb.append(element.toString());
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the
     * string is the front of the queue. Place the delimiter between all elements of the Queue.
     * 
     * @param delimiter the delimiter to separate elements
     * @return string representation of the Queue with elements separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            sb.append(queue.get(i).toString());
            if (i < queue.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    /**
     * Fills the Queue with the elements of the ArrayList. First element in the
     * ArrayList is the first element in the Queue. YOU MUST MAKE A COPY OF LIST
     * AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the list reference within
     * your Queue, you will be allowing direct access to the data of your Queue
     * causing a possible security breach.
     * 
     * @param list elements to be added to the Queue
     * @throws QueueOverflowException if queue is full
     */
    @Override
    public void fill(ArrayList<T> list) throws QueueOverflowException {
        for (T element : list) {
            enqueue(element);
        }
    }
}
