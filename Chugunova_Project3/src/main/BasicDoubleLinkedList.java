// Sousanna Chugunova
// CMSC 204
// Dr. Thai

package main;

import java.util.*;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
    protected Node head;
    protected Node tail;
    protected int size;

    // Node class definition
    protected class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    // Constructor for BasicDoubleLinkedList
    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adds a new node with data to the front of the list
    public void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Adds a new node with data to the end of the list
    public void addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Converts the list to an ArrayList
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    // Iterator implementation for BasicDoubleLinkedList
    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    // Iterator class for BasicDoubleLinkedList
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node current = head;
        private Node lastReturned = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return (current != null && current.prev != null) || (current == null && tail != null);
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = tail;
            } else {
                current = current.prev;
            }
            lastReturned = current;
            return current.data;
        }

        // Unsupported operations below
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    // Getter for the size of the list
    public int getSize() {
        return size;
    }

    // Getter for the first element of the list
    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    // Getter for the last element of the list
    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    // Removes and returns the last element of the list
    public T retrieveLastElement() {
        if (tail == null) {
            return null;
        }
        T data = tail.data;
        if (tail == head) {
            tail = head = null;
        } else {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
        }
        size--;
        return data;
    }

    // Removes and returns the first element of the list
    public T retrieveFirstElement() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        }
        size--;
        return data;
    }

    // Removes a specific data element using a comparator
    public void remove(T data, Comparator<T> comparator) {
        if (head == null) {
            return;
        }
        Node current = head;
        while (current != null) {
            if (comparator.compare(current.data, data) == 0) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }
}
