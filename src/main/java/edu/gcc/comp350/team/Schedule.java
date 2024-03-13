package edu.gcc.comp350.team;
import java.util.ArrayList;
public class Schedule {
    private ArrayList<ArrayList<Class>> database;
    private String scheduleName;
    private ArrayList<Class> classesInSchedule;
    private int numCourses;
    private int numCredits;


    public Schedule(ArrayList<ArrayList<Class>> db){
        this.database = db;
    }

    // this should look up the class in the db - if not found - exception
    // if found, create class object and chek for conflicts in current using the Class.hasConflict
    public void addCourse(Class c){
    }

    public void removeCourse(String courseID){

    }

    public void saveSchedule(){

    }

    public void downloadSchedule(){

    }

    // for Gui purposes -- nicely formatted string
    public void showRecSchedule(){

    }

    // for Console purposes -- just to see what is in Schedule for Sprint 1
    public void printSchedule(){

    }
}

