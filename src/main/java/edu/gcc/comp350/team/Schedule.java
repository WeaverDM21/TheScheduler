package edu.gcc.comp350.team;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Schedule {
    private ArrayList<ArrayList<Class>> database;
    private String scheduleName;
    private ArrayList<Class> classesInSchedule;
    private int numCourses;
    private int numCredits;


    public Schedule(ArrayList<ArrayList<Class>> db){
        this.database = db;
        this.scheduleName = "";
        this.numCredits = 0;
    }

    public Schedule(ArrayList<ArrayList<Class>> db, String scheduleName){
        this.database = db;
        this.scheduleName = scheduleName;
        numCredits = 0;
    }

    // Getter for scheduleName
    public String getScheduleName()
    {
        System.out.println("Schedule Name is: " + scheduleName);
        return scheduleName;
    }

    public void setScheduleName(String name){
        this.scheduleName = name;
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
        // TODO Saved Schedules Folder
        s.addSchedule(this);
    }

    public void downloadSchedule(){

    }

    /**
     * This method takes a GCC major as a String input and prints the recommended schedule
     * for that major to the console. It uses preformatted txt files in the Recommended_Schedules folder.
     * @param major : major of the recommended schedule to display
     * @throws IOException
     */
    public void showRecSchedule(String major) throws IOException {
        // Formats the pathname to the necessary PDF file based on major
        String pathName = "src/Recommended_Schedules/" + major + ".txt";

        // Loading an existing document
        File file = new File(pathName);

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;

        // Loop until end of file is reached
        while ((st = br.readLine()) != null)
            // Print the string
            System.out.println(st);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < classesInSchedule.size(); i++){
            sb.append((i+1) + " " + classesInSchedule.get(i).toString());
        }

        return sb.toString();
    }

    public ArrayList<Class> getClassesInSchedule(){
        return classesInSchedule;
    }

    // for Console purposes -- just to see what is in Schedule for Sprint 1
    public void printSchedule(){

    }
}

