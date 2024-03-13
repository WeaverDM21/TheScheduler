package edu.gcc.comp350.team;

public class Filter {

    private String courseName;
    private String courseDepartment;
    private String courseInstructor;

    // eg 2:35 = 1435 (Military Time)
    private int courseTimeStart;
    private int courseTimeEnd;
    // Index 0 is Monday and Index 4 is Friday
    private boolean[] courseDay;
    private String courseCode;

    public Filter() {
        this.courseName = "";
        this.courseDepartment = "";
        this.courseInstructor = "";
        //this.courseTime = "";
        this.courseCode = "";
        courseDay = new boolean[5];

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

//    public String getCourseTime() {
//        return courseTime;
//    }
//
//    public void setCourseTime(String courseTime) {
//        this.courseTime = courseTime;
//    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
