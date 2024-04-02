package edu.gcc.comp350.team;

import java.util.Arrays;

public class Class {
    private String courseID; // eg COMP350A -- will be unique
    private String courseName;
    private int numCredits;
    private String daysOfWeek;
    private int beginTime; // Military Time
    private int endTime; // Military Time
    private String instructor;
    private String department;
    private int indexInDB;
    public boolean fits;

//    private String description;


    public Class(String courseID, String courseName, int numCredits,
                 String daysOfWeek, int beginTime, int endTime,
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

    public String getDaysOfWeek(){
        return daysOfWeek;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourseID() {
        return courseID;
    }

    public boolean fits(FilterAttribute f){
        if (f.getFilterOption() == FilterAttribute.Option.START)
        {
            return this.beginTime >= f.getIntVal();
        }
        if (f.getFilterOption() == FilterAttribute.Option.END)
        {
            return this.endTime <= f.getIntVal();
        }
        if (f.getFilterOption() == FilterAttribute.Option.NAME)
        {
            return this.courseName.equals(f.getStringVal());
        }
        if (f.getFilterOption() == FilterAttribute.Option.DEPT)
        {
            return this.department.equals(f.getStringVal());
        }
        if (f.getFilterOption() == FilterAttribute.Option.INSTRUCTOR)
        {
            return this.instructor.equals(f.getStringVal());
        }
        if (f.getFilterOption() == FilterAttribute.Option.DAY)
        {
            return this.daysOfWeek.equals(f.getStringVal());
        }
        if (f.getFilterOption() == FilterAttribute.Option.CODE)
        {
            return this.courseID.equals(f.getStringVal());
        }
        return false;
    }

    @Override
    public String toString() {

        return courseID + " " + courseName + " " + daysOfWeek + " " +
                convertTime(beginTime) + "-" + convertTime(endTime) + " " + instructor;
    }

    public String convertTime(int time){
        StringBuilder returnable = new StringBuilder();
        if(time<1200){
            returnable.append(time / 100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time % 100);
            returnable.append(" AM");
            return returnable.toString();
        }else if(time < 1300){
            returnable.append(time/100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time%100);
            returnable.append(" PM");
            return returnable.toString();
        }

        time -= 1200;
        returnable.append(time / 100);
        returnable.append(":");

        if(time % 100 < 10) returnable.append("0");

        returnable.append(time % 100);
        returnable.append(" PM");

        return returnable.toString();
    }

    public String getCourseName() {
        return courseName;
    }


    public boolean hasConflict(Class other){
        boolean sameDay = false;
        for(int i = 0; i < other.daysOfWeek.length(); i++){
            for(int j = 0; j < this.daysOfWeek.length(); j++){
                if(daysOfWeek.charAt(j) == other.daysOfWeek.charAt(i)){
                    sameDay = true;
                    break;
                }
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
