package edu.gcc.comp350.team;
import java.util.ArrayList;
public class User{
    ArrayList<Schedule> savedSchedules;

    public User(){
        // stoppppppppppp
    }

    /**
     * This method adds the parameter Schedule to the savedSchedules ArrayList
     *
     * @param schedule : an instance of the Schedule class
     */
    public void addSchedule(Schedule schedule){
        savedSchedules.add(schedule);
    }

    public void deleteSchedule(String scheduleName){

    }


}