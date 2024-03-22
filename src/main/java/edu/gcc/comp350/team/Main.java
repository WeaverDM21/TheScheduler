package edu.gcc.comp350.team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Search s;
    static User u;
    static Schedule currentSchedule;

    // Arraylist of Arraylist to be able to combine calculus type courses (keep the classes separate
    // but show they are the same course by sharing an arraylist)
    // In simplest terms, solves the problem of the edge case of calc
    static ArrayList<ArrayList<Class>> database = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        generateDB();
//        for(ArrayList<Class> cs : database){
//            System.out.println(cs);
//        }
        //s = new Search(database);

        // Testing savedSchedule
        User us = new User();
//        Schedule sch = new Schedule(database, "Schedule 1");
//        sch.addCourse(5);
//        sch.addCourse(9);
//        sch.saveSchedule(us);
//        sch.addCourse(20);
//        sch.saveSchedule(us);
//        Schedule sch2 = new Schedule(database, "Schedule 2");
//        sch2.addCourse(11);
//        sch2.addCourse(18);
//        sch2.addCourse(90);
//        sch2.saveSchedule(us);
//        sch2.printSchedule();

        //Testing loadSavedSchedules
        us.loadSavedSchedules(database);
        ArrayList<Schedule> s = us.getSavedSchedules();
        for (Schedule s1 : s){
            s1.printSchedule();
        }


//        FilterAttribute instructor = new FilterAttribute(FilterAttribute.Option.INSTRUCTOR, "Shane Brower");
//        s.modifyFilter(instructor);
//        System.out.println();
        // talk to user and they add a filter example
//        // add instructor
//
//        // date example
//        FilterAttribute day = new FilterAttribute(FilterAttribute.Option.DAY, "");
//
//        FilterAttribute start = new FilterAttribute(FilterAttribute.Option.START, 800);
//        FilterAttribute end = new FilterAttribute(FilterAttribute.Option.END, 915);
//        s.modifyFilter(day, start, end);
    }

    private static void run(){

    }

    private static void generateDB() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/classesList/classes.txt"));

        while(input.hasNextLine()){
            String line = input.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            // Gather all the course info about course ID
            String department = lineScanner.next();
            String classNumber = lineScanner.next();
            String section = lineScanner.next();
            String courseID = department + classNumber + section;

            // Get the course name
            String courseName = lineScanner.next();

            // Either the number of credits is a positive number or the class gives no credits
            int numCredits;
            if(lineScanner.hasNextInt()) numCredits = lineScanner.nextInt();
            else numCredits = 0;

            // Days will designate by Monday being index 0 and Friday being index 4
            // If true then the class is held on that day
            String days = "";
            for(int i = 0; i<5; i++){
                days += lineScanner.next();
            }

            String time = lineScanner.next();

            int startTime = -1; // If time is negative, there was no given time for the class

            if(!time.equals("")) {
                boolean isPM = time.endsWith("PM"); // Remember if the string has PM at the end

                // Set a scanner to find the integer values for time
                String temp = time.substring(0, time.indexOf(" "));
                Scanner timeScanner = new Scanner(temp);
                timeScanner.useDelimiter(":");

                int hour = timeScanner.nextInt();
                int minute = timeScanner.nextInt();

                // Start creating military time
                if(isPM && hour != 12) hour += 12;

                hour *= 100;
                startTime = hour + minute;
            }

            time = lineScanner.next();
            int endTime = -1; // If the time is negative no time was given for the class

            // Process is the same as above for endtime
            if(!time.isEmpty()) {
                boolean isPM = time.endsWith("PM");

                Scanner timeScanner = new Scanner(time.substring(0,time.indexOf(" ")));
                timeScanner.useDelimiter(":");

                int hour = timeScanner.nextInt();
                int minute = timeScanner.nextInt();

                if(isPM && hour != 12) hour += 12;

                hour *= 100;
                endTime = hour + minute;
            }

            String lastName = lineScanner.next();
            String firstName = lineScanner.next();
            String fullName = firstName + " " + lastName;

            if(section.equals("O")){
               days = "Online Class";
               startTime = 800;
               endTime = 2359;
            }
            Class tempClass = new Class(courseID, courseName, numCredits, days, startTime, endTime,
                    fullName, department);

            // Check edge condition
            if(!database.isEmpty()){
                // If the same class as above, then add them to the shared arraylist
                if(database.get(database.size()-1).get(0).isSameClass(tempClass)){
                    tempClass.setIndexInDB(database.size()-1);
                    database.get(database.size()-1).add(tempClass);
                }else{ // Add a new arraylist to the db
                    ArrayList<Class> temp = new ArrayList<>();
                    temp.add(tempClass);
                    database.add(temp);
                    tempClass.setIndexInDB(database.size()-1);
                }
            }else{
                // If the list is empty, we have to add the first value
                ArrayList<Class> temp = new ArrayList<>();
                temp.add(tempClass);
                database.add(temp);
                tempClass.setIndexInDB(0);
            }
        }

    }


    private static void switchMode(){

    }
}
