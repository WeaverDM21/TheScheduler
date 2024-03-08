package edu.gcc.comp350.team;

public class Class {
    private static String timeOfDay;
    private String dayOfWeek;
    private String instructor;
    private String location;
    private String department;
    private String courseID;
    private String description;
    private String courseName;
    private String section;

    public Class(String timeOfDay, String dayOfWeek, String instructor, String location,
                 String department, String courseID, String description, String courseName,
                 String section) {
        this.timeOfDay = timeOfDay;
        this.dayOfWeek = dayOfWeek;
        this.instructor = instructor;
        this.location = location;
        this.department = department;
        this.courseID = courseID;
        this.description = description;
        this.courseName = courseName;
        this.section = section;
    }

    public static String getTimeOfDay() {
        return timeOfDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLocation() {
        return location;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getDescription() {
        return description;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSection() {
        return section;
    }
}
