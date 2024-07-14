//sousanna chugunova
//CMSC204
//Dr.Thai
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
    private CourseDBStructure cds; // CourseDBStructure instance to store course data

    public CourseDBManager() {
        cds = new CourseDBStructure(20); // Initialize CourseDBStructure with size 20
    }

    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        // Validate credits
        if (credits < 1 || credits > 4) {
            throw new IllegalArgumentException("Credits must be between 1 and 4.");
        }
        
        try {
            // Check if a course with the given CRN already exists
            if (cds.get(crn) != null) {
                throw new IllegalArgumentException("A course with CRN " + crn + " already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Create a new CourseDBElement and add it to the CourseDBStructure
        CourseDBElement cde = new CourseDBElement(id, crn, credits, roomNum, instructor);
        cds.add(cde);
    }

    @Override
    public CourseDBElement get(int crn) {
        try {
            // Retrieve the course with the given CRN from the CourseDBStructure
            return cds.get(crn);
        } catch (IOException e) {
            return null; // Course not found, return null
        }
    }

    @Override
    public void readFile(File input) throws FileNotFoundException {
        @SuppressWarnings("resource")
        Scanner fileScanner = new Scanner(input); // Create a scanner to read the file
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" "); // Split the line into parts
            
            // Check if the line has the required number of parts
            if (parts.length < 5) {
                throw new IllegalArgumentException("Incomplete course data: " + line);
            }

            // Extract course details from the parts
            String id = parts[0];
            int crn = Integer.parseInt(parts[1]);
            int credits = Integer.parseInt(parts[2]);
            String roomNum = parts[3];
            String instructor = parts[4];

            // Add the course to the CourseDBStructure
            add(id, crn, credits, roomNum, instructor);
        }
        fileScanner.close();
    }

    @Override
    public List<String> showAll() {
        List<String> courseList = new ArrayList<>();
        // Retrieve all courses from the CourseDBStructure and add their string representation to the list
        for (CourseDBElement cde : cds.getAllCourses()) {
            courseList.add(cde.toString());
        }
        return courseList; // Return the list of course strings
    }

    public List<CourseDBElement> getInvalidCourses() {
        List<CourseDBElement> invalidCourses = new ArrayList<>();
        // Iterate through all courses and check for invalid CRNs
        for (CourseDBElement cde : cds.getAllCourses()) {
            if (String.valueOf(cde.getCRN()).length() != 5) {
                invalidCourses.add(cde);
                // Print the invalid course details
                System.out.println("Invalid CRN: " + cde.getCRN() + " for course " + cde.getCourseID());
            }
        }
        return invalidCourses; // Return the list of invalid courses
    }

    public int getSize() {
        return cds.getSize(); // Return the size of the CourseDBStructure
    }
}
