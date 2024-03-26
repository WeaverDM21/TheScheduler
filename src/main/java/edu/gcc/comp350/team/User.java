package edu.gcc.comp350.team;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class User{
    ArrayList<Schedule> savedSchedules;

    public User(){
        savedSchedules = new ArrayList<>();
    }

    /**
     * This method adds the parameter Schedule to the savedSchedules ArrayList
     *
     * @param schedule : an instance of the Schedule class
     */
    public void addSchedule(Schedule schedule){
        savedSchedules.add(schedule);
    }

    public ArrayList<Schedule> getSavedSchedules(){
        return savedSchedules;
    }
    /**
     * This method retrieves a saved schedule based on its name.
     *
     * @param scheduleName : the name of the schedule to retrieve
     * @return Schedule : the retrieved schedule, or null if not found
     */
    public Schedule getSchedule(String scheduleName)
    {
        for(Schedule schedule : savedSchedules)
        {
            if(schedule.getScheduleName().equals(scheduleName))
            {
                return schedule;
            }
        }
        return null; // Schedule not found
    }
    public void loadSavedSchedules() throws IOException {
        // Load SavedSchedules.txt
        File file = new File("src/SavedSchedules.txt");

        // BufferedReader to read through file
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable to hold each line
        String line;

        // Loop until end of file is reached
        while ((line = br.readLine()) != null){
            //TODO: Load in saved schedules
        }

        // Close br
        br.close();
    }

    public void loadSavedSchedules(ArrayList<ArrayList<Class>> database) throws IOException {
        // Load SavedSchedules.txt
        File file = new File("src/SavedSchedules.txt");

        // BufferedReader to read through file
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable to hold each line
        String line;

        // Loop until end of file is reached
        while ((line = br.readLine()) != null){
            String[] lineInfo = line.split(",");
            Schedule s = new Schedule(database, lineInfo[0]);
            for (int i = 1; i < lineInfo.length; i++){
                s.addCourse(Integer.valueOf(lineInfo[i]));
            }
            savedSchedules.add(s);
        }

        // Close br
        br.close();
    }

    public void deleteSchedule(String scheduleName){

    }

    //for testing purposes
    public ArrayList<Schedule> getSavedSchedules(){
        return savedSchedules;
    }


}