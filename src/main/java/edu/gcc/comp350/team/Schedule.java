package edu.gcc.comp350.team;
import java.util.ArrayList;
public class Schedule {
    private ArrayList<ArrayList<Class>> database;
    private String scheduleName;
    private ArrayList<Class> classesInSchedule;
    private int numCourses;
    private int numCredits;


    public Schedule(ArrayList<ArrayList<Class>> db, String scheduleName){
        this.database = db;
        this.scheduleName = scheduleName;
        numCredits = 0;
    }

    // Getter for scheduleName
    public String getScheduleName()
    {
        return scheduleName;
    }

    // this should look up the class in the db - if not found - exception
    // if found, create class object and check for conflicts in current using the Class.hasConflict
    public void addCourse(int index){
        ArrayList<Class> courseToAdd = database.get(index);

        int classCredits = courseToAdd.get(0).getNumCredits();
        numCredits += classCredits;

        // Add the course to the list of classes in the schedule
        classesInSchedule.addAll(courseToAdd);
        numCourses++;
    }

    public void removeCourse(int index){
        // Check if the index is valid
        if(index >= 0 && index < classesInSchedule.size())
        {
            Class courseToRemove = classesInSchedule.get(index);
            int classCredits = courseToRemove.getNumCredits();
            numCredits -= classCredits;

            // Remove the course from the list of classes in the schedule
            classesInSchedule.remove(index);
            numCourses--;
        }
        else
        {
            System.out.println("Invalid index. Course not removed.");
        }
    }

    /**
     * This method saves a Schedule by passing itself into the addSchedule method of User
     *
     * addSchedule will take care of adding this Schedule to its savedSchedules ArrayList
     *
     * @param s : An instance of the User class
     */
    public void saveSchedule(User s){
        s.addSchedule(this);
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

