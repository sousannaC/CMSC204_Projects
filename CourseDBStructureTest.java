//sousanna chugunova
//CMSC204
//Dr.Thai
package main;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CourseDBStructureTest {
    CourseDBStructure cds;

    @Before
    public void setUp() throws Exception {
        cds = new CourseDBStructure(10);
    }

    @Test
    public void testAddAndGet() throws IOException {
        CourseDBElement cde1 = new CourseDBElement("CMSC204", 30559, 4, "SC450", "BillyBob Jones");
        cds.add(cde1);
        CourseDBElement cde2 = cds.get(30559);
        assertEquals(cde1.getCourseID(), cde2.getCourseID());
    }

    @Test
    public void testShowAll() {
        CourseDBElement cde1 = new CourseDBElement("CMSC204", 30559, 4, "SC450", "BillyBob Jones");
        CourseDBElement cde2 = new CourseDBElement("CMSC203", 30503, 4, "SC450", "Jill B. Who-Dunit");
        cds.add(cde1);
        cds.add(cde2);
        assertEquals(2, cds.showAll().size());
    }

    @Test
    public void testGetNonExistent() {
        try {
            cds.get(12345);
            fail("Should have thrown IOException");
        } catch (IOException e) {
            assertTrue(true);
        }
    }
}
