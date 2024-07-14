//sousanna chugunova
//CMSC204
//Dr.Thai
package main;

public class CourseDBElement implements Comparable<CourseDBElement> {
    private String courseID;
    private int crn;
    private int credits;
    private String roomNumber;
    private String instructor;

    public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructor) {
        if (credits < 1 || credits > 4) {
            throw new IllegalArgumentException("Credits must be between 1 and 4.");
        }
        this.courseID = courseID.trim();
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber.trim();
        this.instructor = instructor.trim();
    }

    // Default constructor
    public CourseDBElement() {
        // Default values can be set here if needed
    }

    public String getCourseID() {
        return courseID;
    }

    public int getCRN() {
        return crn;
    }

    public int getCredits() {
        return credits;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn);
    }

    @Override
    public String toString() {
        return String.format("Course: %s CRN: %d Credits: %d Instructor: %s Room: %s",
                courseID, crn, credits, instructor, roomNumber);
    }

    public String getID() {
        return courseID;
    }

    public String getRoomNum() {
        return roomNumber;
    }

    public void setCRN(int crn) {
        this.crn = crn;
    }
}

