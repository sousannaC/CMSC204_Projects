//sousanna chugunova
//CMSC204
//Dr.Thai
package main;

import org.junit.Test;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CourseDBManager_STUDENT_Test {
    private CourseDBManager dataMgr = new CourseDBManager();

    @Test
    public void testAddDuplicateCourse() {
        dataMgr.add("CMSC203", 30504, 4, "SC450", "Joey Bag-O-Donuts");
        try {
            dataMgr.add("CMSC203", 30504, 4, "SC450", "Joey Bag-O-Donuts"); // Attempt to add duplicate
            fail("Expected IllegalArgumentException for duplicate course");
        } catch (IllegalArgumentException e) {
            assertEquals("A course with CRN 30504 already exists.", e.getMessage());
        }
    }

    @Test
    public void testReadFile() {
        try {
            File inputFile = new File("courses-F23.txt");
            PrintWriter inFile = new PrintWriter(inputFile);
            inFile.println("CMSC203 30504 4 SC450 Joey Bag-O-Donuts");
            inFile.println("CMSC204 30503 4 SC450 Jill B. Who-Dunit");
            inFile.close();

            dataMgr.readFile(inputFile);
            assertEquals("CMSC203", dataMgr.get(30504).getID());
            assertEquals("CMSC204", dataMgr.get(30503).getID());
            assertEquals("SC450", dataMgr.get(30503).getRoomNum());
        } catch (Exception e) {
            fail("Should not have thrown an exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidCourseLogging() {
        try {
            dataMgr.add("CMSC203", 30504, 5, "SC450", "Joey Bag-O-Donuts"); // Invalid credits
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            assertEquals("Credits must be between 1 and 4.", e.getMessage());
        }
    }

    @Test
    public void testShowAll() {
        dataMgr.add("CMSC204", 30559, 4, "SC450", "BillyBob Jones");
        List<String> courses = dataMgr.showAll();
        String expected = "Course: CMSC204 CRN: 30559 Credits: 4 Instructor: BillyBob Jones Room: SC450";
        
        assertEquals(expected, courses.get(0));
    }
}
