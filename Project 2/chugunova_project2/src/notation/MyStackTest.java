//Sousanna Chugunova
//Dr.Thai
//CMSC204

package notation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyStackTest {

    private MyStack<String> stringS;
    private String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
    private ArrayList<String> fill = new ArrayList<>();

    // STUDENT: student tests will use the doubleS
    private MyStack<Double> doubleS;

    @BeforeEach
    public void setUp() throws Exception {
        stringS = new MyStack<>(5);
        stringS.push(a);
        stringS.push(b);
        stringS.push(c);

        doubleS = new MyStack<>(5);
    }

    @AfterEach
    public void tearDown() throws Exception {
        stringS = null;
        doubleS = null;
    }

    @Test
    public void testIsEmpty() throws StackUnderflowException {
        assertEquals(false, stringS.isEmpty());
        stringS.pop();
        stringS.pop();
        stringS.pop();
        assertEquals(true, stringS.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        assertEquals(false, stringS.isFull());
        stringS.push(d);
        stringS.push(e);
        assertEquals(true, stringS.isFull());
    }

    @Test
    public void testPop() {
        try {
            assertEquals(c, stringS.pop());
            assertEquals(b, stringS.pop());
            assertEquals(a, stringS.pop());
            // Stack is empty, next statement should cause StackUnderflowException
            stringS.pop();
            fail("This should have caused a StackUnderflowException");
        } catch (StackUnderflowException e) {
            assertTrue(true, "This should have caused a StackUnderflowException");
        } catch (Exception e) {
            assertTrue(false, "Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPopStudent() {
        try {
            doubleS.push(1.0);
            doubleS.push(2.0);
            doubleS.push(3.0);

            assertEquals(3.0, doubleS.pop());
            assertEquals(2.0, doubleS.pop());
            assertEquals(1.0, doubleS.pop());

            assertTrue(doubleS.isEmpty());

        } catch (StackUnderflowException | StackOverflowException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testTop() throws StackUnderflowException, StackOverflowException {
        assertEquals(c, stringS.top());
        stringS.push(d);
        assertEquals(d, stringS.top());
        stringS.pop();
        stringS.pop();
        assertEquals(b, stringS.top());
    }

    @Test
    public void testSize() throws StackOverflowException, StackUnderflowException {
        assertEquals(3, stringS.size());
        stringS.push(d);
        assertEquals(4, stringS.size());
        stringS.pop();
        stringS.pop();
        assertEquals(2, stringS.size());
    }

    @Test
    public void testPush() {
        try {
            assertEquals(3, stringS.size());
            assertTrue(stringS.push(d));
            assertEquals(4, stringS.size());
            assertTrue(stringS.push(e));
            assertEquals(5, stringS.size());
            // Stack is full, next statement should cause StackOverflowException
            stringS.push(f);
            fail("This should have caused a StackOverflowException");
        } catch (StackOverflowException e) {
            assertTrue(true, "This should have caused a StackOverflowException");
        } catch (Exception e) {
            assertTrue(false, "Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testPushStudent() {
        try {
            doubleS.push(1.0);
            doubleS.push(2.0);
            doubleS.push(3.0);

            assertEquals(3, doubleS.size());
            assertEquals(3.0, doubleS.top());

        } catch (StackOverflowException | StackUnderflowException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testToString() throws StackOverflowException {
        assertEquals("a b c", stringS.toString());
        stringS.push(d);
        assertEquals("a b c d", stringS.toString());
        stringS.push(e);
        assertEquals("a b c d e", stringS.toString());
    }

    @Test
    public void testToStringStudent() {
        try {
            MyStack<Double> doubleS = new MyStack<>(5);

            doubleS.push(1.0);
            doubleS.push(2.0);
            doubleS.push(3.0);

            String expected = "1.0 2.0 3.0";
            assertEquals(expected, doubleS.toString());

        } catch (StackOverflowException e) {
            fail("Unexpected StackOverflowException: " + e.getMessage());
        }
    }

    @Test
    public void testToStringDelimiter() throws StackOverflowException {
        assertEquals("a%b%c", stringS.toString("%"));
        stringS.push(d);
        assertEquals("a&b&c&d", stringS.toString("&"));
        stringS.push(e);
        assertEquals("a/b/c/d/e", stringS.toString("/"));
    }

    @Test
    public void testFill() throws StackOverflowException, StackUnderflowException {
        fill.add("apple");
        fill.add("banana");
        fill.add("carrot");
        stringS = new MyStack<>(5);
        stringS.fill(fill);
        assertEquals(3, stringS.size());
        assertEquals("carrot", stringS.pop());
        assertEquals("banana", stringS.pop());
        assertEquals("apple", stringS.pop());
    }
}
