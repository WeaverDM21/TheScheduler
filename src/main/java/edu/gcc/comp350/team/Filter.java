package org.example;

public class Filter {
    private String courseName;
    private String courseDepartment;
    private String courseInstructor;
    private String courseTime;
    private String courseCode;
    private Search search;

    public Filter(String courseName, String courseDepartment, String courseInstructor,
                  String courseTime, String courseCode) {
        this.courseName = courseName;
        this.courseDepartment = courseDepartment;
        this.courseInstructor = courseInstructor;
        this.courseTime = courseTime;
        this.courseCode = courseCode;
        search = new Search(this);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
