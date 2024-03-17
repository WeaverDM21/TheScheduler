package edu.gcc.comp350.team;
import java.util.ArrayList;
public class Schedule {
    private ArrayList<ArrayList<Class>> database;
    private String scheduleName;
    private int numCourses;
    private int numCredits;


    public Schedule(ArrayList<ArrayList<Class>> db){
        this.database = db;
    }

    // this should look up the class in the db - if not found - exception
    // if found, create class object and chek for conflicts in current using the Class.hasConflict
    public void addCourse(String courseID){

    }

    public void removeCourse(String courseID){

    }

    public void invalidClasstime(){

    }

    public void saveSchedule(){

    }

    public void downloadSchedule(){

    }

    // for console purposes -- nicely formatted string
    public String showRecSchedule(){
        return null;
    }
}

