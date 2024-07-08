// Sousanna Chugunova
// CMSC 204
// Dr. Thai

package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class BasicDoubleLinkedList_STUDENT_Test {

    // Test for addToFront method
    @Test
    public void testAddToFront() {
        BasicDoubleLinkedList<String> list = new BasicDoubleLinkedList<>();
        list.addToFront("A");
        assertEquals(1, list.getSize()); // Check size after adding
        assertEquals("A", list.getFirst()); // Check first element
    }

    // Test for addToEnd method
    @Test
    public void testAddToEnd() {
        BasicDoubleLinkedList<String> list = new BasicDoubleLinkedList<>();
        list.addToEnd("B");
        assertEquals(1, list.getSize()); // Check size after adding
        assertEquals("B", list.getLast()); // Check last element
    }

    // Test for toArrayList method
    @Test
    public void testToArrayList() {
        BasicDoubleLinkedList<String> list = new BasicDoubleLinkedList<>();
        list.addToFront("A");
        list.addToEnd("B");
        ArrayList<String> arrayList = list.toArrayList();
        assertEquals(2, arrayList.size()); // Check size of ArrayList
        assertEquals("A", arrayList.get(0)); // Check first element
        assertEquals("B", arrayList.get(1)); // Check second element
    }
}

