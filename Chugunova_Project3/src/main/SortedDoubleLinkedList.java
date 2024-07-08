// Sousanna Chugunova
// CMSC 204
// Dr. Thai

package main;

import java.util.*;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    // Constructor to initialize with a comparator for sorting
    public SortedDoubleLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    // Adds a new node with data in sorted order
    public void add(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            Node current = head;
            while (current != null && comparator.compare(data, current.data) > 0) {
                current = current.next;
            }

            if (current == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                newNode.next = current;
                newNode.prev = current.prev;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    // Overrides addToFront method to throw UnsupportedOperationException
    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // Overrides addToEnd method to throw UnsupportedOperationException
    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // Converts the sorted list to an ArrayList
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    // Retrieves the first element of the sorted list
    @Override
    public T retrieveFirstElement() {
        if (head == null) {
            return null;
        }
        return head.data;
    }
}
