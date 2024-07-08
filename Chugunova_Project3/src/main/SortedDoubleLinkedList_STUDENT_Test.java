// Sousanna Chugunova
// CMSC 204
// Dr. Thai

package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class SortedDoubleLinkedList_STUDENT_Test {

    // Test for add method in SortedDoubleLinkedList
    @Test
    public void testAdd() {
        Comparator<String> comparator = String::compareTo;
        SortedDoubleLinkedList<String> list = new SortedDoubleLinkedList<>(comparator);
        list.add("B");
        list.add("A");
        list.add("C");

        ArrayList<String> arrayList = list.toArrayList();
        assertEquals(3, arrayList.size()); // Check size of ArrayList
        assertEquals("A", arrayList.get(0)); // Check first element
        assertEquals("B", arrayList.get(1)); // Check second element
        assertEquals("C", arrayList.get(2)); // Check third element
    }

    // Test for unsupported addToFront method in SortedDoubleLinkedList
    @Test
    public void testUnsupportedAddToFront() {
        Comparator<String> comparator = String::compareTo;
        SortedDoubleLinkedList<String> list = new SortedDoubleLinkedList<>(comparator);

        assertThrows(UnsupportedOperationException.class, () -> {
            list.addToFront("A");
        });
    }

    // Test for unsupported addToEnd method in SortedDoubleLinkedList
    @Test
    public void testUnsupportedAddToEnd() {
        Comparator<String> comparator = String::compareTo;
        SortedDoubleLinkedList<String> list = new SortedDoubleLinkedList<>(comparator);

        assertThrows(UnsupportedOperationException.class, () -> {
            list.addToEnd("A");
        });
    }
}
