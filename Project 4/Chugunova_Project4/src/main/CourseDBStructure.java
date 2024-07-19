// Sousanna Chugunova
// CMSC204
// Dr. Thai
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CourseDBStructure implements CourseDBStructureInterface {
    private LinkedList<CourseDBElement>[] table; // Array of linked lists to handle collisions
    private int size; // Number of elements in the structure

    @SuppressWarnings("unchecked")
    public CourseDBStructure(int n) {
        // Create a hash table with a size that is the next prime number
        int capacity = findNextPrime((int) Math.ceil(n / 1.5));
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>(); // Initialize each bucket as a linked list
        }
        size = 0; // Initialize size to zero
    }

    @SuppressWarnings("unchecked")
    public CourseDBStructure(String testing, int tableSize) {
        // Testing constructor that initializes with a specified size
        if ("Testing".equals(testing)) {
            table = new LinkedList[tableSize];
            for (int i = 0; i < tableSize; i++) {
                table[i] = new LinkedList<>();
            }
        }
        size = 0; // Initialize size to zero
    }

    @Override
    public int getTableSize() {
        return table.length; // Return the size of the hash table
    }

    @Override
    public void add(CourseDBElement element) {
        // Calculate the hash index for the given CRN
        int index = getHashIndex(element.getCRN());
        // Check if the element already exists
        for (CourseDBElement e : table[index]) {
            if (e.getCRN() == element.getCRN()) {
                return; // Element already exists, do not add
            }
        }
        // Add the element to the linked list
        table[index].add(element);
        size++; // Increment the size
    }

    @Override
    public CourseDBElement get(int crn) throws IOException {
        // Calculate the hash index for the given CRN
        int index = getHashIndex(crn);
        // Search for the course in the linked list
        for (CourseDBElement element : table[index]) {
            if (element.getCRN() == crn) {
                return element; // Return the found element
            }
        }
        throw new IOException("Course not found for CRN: " + crn); // Throw an exception if not found
    }

    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> allElements = new ArrayList<>();
        // Iterate through each linked list and add their string representations
        for (LinkedList<CourseDBElement> list : table) {
            for (CourseDBElement element : list) {
                allElements.add(element.toString());
            }
        }
        return allElements; // Return the list of all course strings
    }

    private int getHashIndex(int crn) {
        // Calculate the hash index using the absolute value of CRN
        return Math.abs(crn) % table.length; // Ensure valid index
    }

    private int findNextPrime(int num) {
        // Find the next prime number greater than or equal to num
        while (!isPrime(num) || (num - 3) % 4 != 0) {
            num++;
        }
        return num; // Return the next prime number
    }

    private boolean isPrime(int num) {
        // Check if a number is prime
        if (num <= 1) return false; // Numbers less than or equal to 1 are not prime
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false; // Found a divisor, not prime
        }
        return true; // Number is prime
    }

    public CourseDBElement[] getAllCourses() {
        List<CourseDBElement> allCourses = new ArrayList<>();
        // Collect all courses from the hash table
        for (LinkedList<CourseDBElement> list : table) {
            allCourses.addAll(list);
        }
        return allCourses.toArray(new CourseDBElement[0]); // Return as an array
    }

    public int getSize() {
        return size; // Return the current size of the structure
    }
}
