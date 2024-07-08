//Sousanna Chugunova
//Dr.Thai
//CMSC204
package notation;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTest {
    public MyQueue<String> stringQ;
    public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    public ArrayList<String> fill = new ArrayList<>();

    // STUDENT: student tests will use the doubleQ
    public MyQueue<Double> doubleQ;
    
    @BeforeEach
    public void setUp() throws Exception {
        stringQ = new MyQueue<>(5);
        stringQ.enqueue(a);
        stringQ.enqueue(b);
        stringQ.enqueue(c);

        doubleQ = new MyQueue<>(5); // Set up doubleQ for student tests
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringQ = null;
        doubleQ = null;
    }

    @Test
    public void testIsEmpty() throws QueueUnderflowException {
        assertFalse(stringQ.isEmpty());
        stringQ.dequeue();
        stringQ.dequeue();
        stringQ.dequeue();
        assertTrue(stringQ.isEmpty());
    }

    @Test
    public void testDequeue() {
        try {
            assertEquals(a, stringQ.dequeue());
            assertEquals(b, stringQ.dequeue());
            assertEquals(c, stringQ.dequeue());
            // Queue is empty, next statement should cause QueueUnderflowException
            stringQ.dequeue();
            fail("This should have caused an QueueUnderflowException");
        } catch (QueueUnderflowException e) {
            // Expected exception
        } catch (Exception e) {
            fail("This should have caused an QueueUnderflowException");
        }
    }

    @Test
    public void testDequeueStudent() {
        // Use the doubleQ for student tests
        try {
            doubleQ.enqueue(1.0);
            doubleQ.enqueue(2.0);
            
            assertEquals(1.0, doubleQ.dequeue());
            assertEquals(2.0, doubleQ.dequeue());
            
            // Queue is empty, next statement should cause QueueUnderflowException
            doubleQ.dequeue();
            fail("This should have caused an QueueUnderflowException");
        } catch (QueueUnderflowException e) {
            // Expected exception
        } catch (Exception e) {
            fail("This should have caused an QueueUnderflowException");
        }
    }

    @Test
    public void testSize() throws QueueOverflowException, QueueUnderflowException {
        assertEquals(3, stringQ.size());
        stringQ.enqueue(d);
        assertEquals(4, stringQ.size());
        stringQ.dequeue();
        stringQ.dequeue();
        assertEquals(2, stringQ.size());
    }

    @Test
    public void testEnqueue() {
        try {
            assertEquals(3, stringQ.size());
            assertTrue(stringQ.enqueue(d));
            assertEquals(4, stringQ.size());
            assertTrue(stringQ.enqueue(e));
            assertEquals(5, stringQ.size());
            // Queue is full, next statement should cause QueueOverflowException
            stringQ.enqueue(f);
            fail("This should have caused an QueueOverflowException");
        } catch (QueueOverflowException e) {
            // Expected exception
        } catch (Exception e) {
            fail("This should have caused an QueueOverflowException");
        }
    }

    @Test
    public void testEnqueueStudent() {
        // Use the doubleQ for student tests
        try {
            assertEquals(0, doubleQ.size()); // Ensure doubleQ is initially empty

            assertTrue(doubleQ.enqueue(1.0));
            assertEquals(1, doubleQ.size());

            assertTrue(doubleQ.enqueue(2.0));
            assertEquals(2, doubleQ.size());

            assertTrue(doubleQ.enqueue(3.0));
            assertEquals(3, doubleQ.size());

            // Add more assertions as needed to verify enqueue functionality

        } catch (QueueOverflowException e) {
            fail("Unexpected QueueOverflowException");
        }
    }

    @Test
    public void testIsFull() throws QueueOverflowException {
        assertFalse(stringQ.isFull());
        stringQ.enqueue(d);
        stringQ.enqueue(e);
        assertTrue(stringQ.isFull());
    }

    @Test
    public void testToString() throws QueueOverflowException {
        assertEquals("abc", stringQ.toString());
        stringQ.enqueue(d);
        assertEquals("abcd", stringQ.toString());
        stringQ.enqueue(e);
        assertEquals("abcde", stringQ.toString());
    }
    @Test
    public void testToStringStudent() throws QueueUnderflowException {
        // Use the doubleQ for student tests
        doubleQ = new MyQueue<>(5);
        try {
            doubleQ.enqueue(1.0);
            doubleQ.enqueue(2.0);
            doubleQ.enqueue(3.0);
            
            String expected = "1.02.03.0"; // Adjust this based on your expected format
            
            assertEquals(expected, doubleQ.toString());
        } catch (QueueOverflowException e) {
            fail("Unexpected QueueOverflowException");
        }
    }

    @Test
    public void testToStringDelimiter() throws QueueOverflowException {
        assertEquals("a%b%c", stringQ.toString("%"));
        stringQ.enqueue(d);
        assertEquals("a&b&c&d", stringQ.toString("&"));
        stringQ.enqueue(e);
        assertEquals("a/b/c/d/e", stringQ.toString("/"));
    }

    @Test
    public void testFill() throws QueueOverflowException, QueueUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        // start with an empty queue
        stringQ = new MyQueue<>(5);
        // fill with an ArrayList
        stringQ.fill(fill);
        assertEquals(3, stringQ.size());
        assertEquals("apple", stringQ.dequeue());
        assertEquals("banana", stringQ.dequeue());
        assertEquals("carrot", stringQ.dequeue());
    }
}
