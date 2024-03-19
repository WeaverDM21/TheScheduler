package edu.gcc.comp350.team;
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
        System.out.println("schedule not found");
        return null; // Schedule not found
    }

    public void deleteSchedule(String scheduleName){

    }


}