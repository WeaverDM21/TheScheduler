package edu.gcc.comp350.team;

import java.util.Arrays;

public class Class {
    private String courseID; // eg COMP350A -- will be unique
    private String courseName;
    private int numCredits;
    private boolean[] daysOfWeek;
    private int beginTime; // Military Time
    private int endTime; // Military Time
    private String instructor;
    private String department;
    private int indexInDB;

//    private String description;


    public Class(String courseID, String courseName, int numCredits,
                 boolean[] daysOfWeek, int beginTime, int endTime,
                 String instructor, String department) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numCredits = numCredits;
        this.daysOfWeek = daysOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.department = department;
        int indexInDB = -1;
    }

    public int getIndexInDB() {
        return indexInDB;
    }

    public void setIndexInDB(int n){
        indexInDB = n;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {
        return "Class{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", numCredits=" + numCredits +
                ", daysOfWeek=" + Arrays.toString(daysOfWeek) +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", instructor='" + instructor + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }


    public boolean hasConflict(Class other){
        boolean sameDay = false;
        for(int i = 0; i < 5; i++){
            if(this.daysOfWeek[i] && other.daysOfWeek[i]){
                sameDay = true;
                break;
            }
        }

        boolean timeOverlap = false;

        if(this.beginTime == other.beginTime) timeOverlap = true;
        else if(this.beginTime > other.beginTime && this.endTime <= other.endTime){
            timeOverlap = true;
        }else if(other.beginTime > this.beginTime && other.endTime <= this.endTime) {
            timeOverlap = true;
        }

        return sameDay && timeOverlap;
    }

    public boolean isSameClass(Class other){
        return this.courseID.equals(other.courseID) && this.instructor.equals(other.instructor);
    }
}
