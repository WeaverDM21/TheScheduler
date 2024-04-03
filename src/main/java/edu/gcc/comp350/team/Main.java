package edu.gcc.comp350.team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        run();
    }

    private static void run() throws Exception{
        generateDB();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Grove City Course Scheduler!");

        s = new Search(database);
        u = new User();
        u.loadSavedSchedules(database);

        openSchedule(input);

        while(true){
            System.out.println("Type:\n" +
                    "1. to change schedules\n" +
                    "2. to update the name of the schedule\n" +
                    "3. to see the current schedule\n" +
                    "4. to update search filters\n" +
                    "5. to add a class to the schedule\n" +
                    "6. to remove a class from the schedule\n" +
                    "7. to see a recommended course path for your major\n" +
                    "8. to save your schedule and exit the program\n" +
                    "9. to exit the program without saving");

            int choice = 0;
            try{
                choice = input.nextInt();
                input.nextLine();
            }catch (Exception e){
                input.nextLine();
            }

            switch(choice){
                case 1:
                    System.out.println("Do you want to continue without saving the current schedule?");

                    while(true) {
                        System.out.println("Type exactly YES to continue without saving. Type exactly SAVE to continue and save.");
                        String response = input.nextLine();
                        if(response.equalsIgnoreCase("YES")){
                            openSchedule(input);
                            break;
                        }else if(response.equalsIgnoreCase("SAVE")){
                            currentSchedule.saveSchedule(u);
                            openSchedule(input);
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("What would you like the name of the schedule to be? Enter a number to cancel updating the name.");
                    String name = input.nextLine();

                    try{
                        Integer.parseInt(name);
                        break;
                    }catch (NumberFormatException e){
                        currentSchedule.setScheduleName(name);
                    }

                case 3:
                    System.out.println(currentSchedule);
                    break;

                case 4:
                    while(true){
                        int searchInput = 0;
                        while(true){
                            System.out.println("Type 1 to add a search filter, 2 to remove a search filter, 3 to cancel");
                            try{
                                searchInput = input.nextInt();
                                input.nextLine();
                                if(searchInput >=1 && searchInput <= 3){
                                    break;
                                }
                            }catch (Exception e){
                                System.out.println("Invalid input.");
                            }
                        }

                        if(searchInput == 3) break;

                        switch (searchInput){
                            case 1:
                                System.out.println("Type a negative number to add no search filters\n" +
                                        "Type 1 to search a class by name\n" +
                                        "Type 2 to search classes by department\n" +
                                        "Type 3 to search classes by instructor\n" +
                                        "Type 4 to search classes by course code\n" +
                                        "Type 5 to search classes by day or time of the week\n");
                                System.out.println("If you make a mistake, just redo the same filter option.");
                                int searchValue = 0;
                                try{
                                    searchValue = input.nextInt();
                                    input.nextLine();
                                }catch (Exception e){
                                    input.nextLine();
                                }

                                if(searchValue < 0) break;

                                findSearchFilters(searchValue, input);

                                break;

                            case 2:
                                System.out.println("All search filters active: ");
                                ArrayList<FilterAttribute> filters = s.printFilterAttributes();
                                int remove = 0;
                                while(true){
                                    System.out.println("Are there any search filters you want to remove? 1 if yes, 2 if no.");
                                    try{
                                        remove = input.nextInt();
                                        if(remove == 1 || remove == 2) break;
                                        else System.out.println("Invalid input.");
                                    }catch (Exception e){
                                        System.out.println("Invalid input.");
                                    }
                                }

                                while(remove == 1){
                                    if(filters.isEmpty()) {
                                        System.out.println("No filters remaining");
                                        break;
                                    }

                                    for(int i = 0; i < filters.size(); i++){
                                        System.out.println((i+1) + " " + filters.get(i).getFilterOption());
                                    }

                                    System.out.println("Which filter value do you want to remove? Select the number from the list above.");
                                    int removeVal = 0;

                                    while(true){
                                        try{
                                            removeVal = input.nextInt();
                                            if(removeVal > 0 && removeVal <= filters.size()) break;
                                            else System.out.println("Invalid input.");
                                        }catch (Exception e){
                                            System.out.println("Invalid input.");
                                        }
                                    }

                                    s.removeFilter(filters.get(removeVal-1).getFilterOption());

                                    System.out.println("All search filters active: ");
                                    filters = s.printFilterAttributes();

                                    System.out.println("Are there more filters you want to remove? 1 if yes, 2 if no.");

                                    while(true){
                                        try{
                                            remove = input.nextInt();
                                            if(remove == 1 || remove == 2) break;
                                            else System.out.println("Invalid input.");
                                        }catch (Exception e){
                                            System.out.println("Invalid input.");
                                        }
                                    }
                                }
                                break;

                        }
                    }

                    break;

                case 5:

                    System.out.println("Here are the courses that fit your current search filters.");
                    ArrayList<ArrayList<Class>> results = s.printCurrClasses();

                    System.out.println();
                    System.out.println("Select the number next to the class that you want to add to your schedule or enter -1 to add none.");

                    int desiredClass;
                    boolean skip = false;

                    while(true){
                        try{
                            desiredClass = input.nextInt();
                            if(desiredClass == -1) {
                                skip = true;
                                break;
                            }
                            if(desiredClass > 0 && desiredClass <= results.size()) break;
                            else System.out.println("Invalid input.");
                        }catch(Exception e){
                            System.out.println("Invalid input.");
                        }
                    }
                    if(skip) continue;

                    if(currentSchedule.addCourse(results.get(desiredClass-1).get(0).getIndexInDB())){
                        System.out.println("Course added.");
                    }
                    break;

                case 6:
                    if(currentSchedule.getClassesInSchedule().isEmpty()){
                        System.out.println("There are no classes in the schedule!");
                        continue;
                    }
                    System.out.println("The current schedule is below ");
                    currentSchedule.printForRemove();

                    System.out.println("Select the number next to the class you want to remove");

                    int classToRemove = 0;
                    while(true){
                        try{
                            classToRemove = input.nextInt();
                            if(classToRemove > 0 && classToRemove <= currentSchedule.getClassesInSchedule().size()){
                                currentSchedule.removeCourse(classToRemove-1);
                                break;
                            }else{
                                System.out.println("Invalid input");
                            }
                        }catch (Exception e){
                            System.out.println("Invalid input");
                        }
                    }

                    break;
                case 7:
                    // TODO generate a list of majors to select from and give the option to

                    break;

                case 8:
                    System.out.println("Are you sure you want to exit and save? Type 1 for yes and 2 for no.");
                    int exitSave = 0;
                    while(true){
                        try{
                            exitSave = input.nextInt();
                            if(exitSave == 1){
                                System.out.println("Exiting");
                                currentSchedule.saveSchedule(u);
                                return;
                            }else if (exitSave == 2){
                                break;
                            }else{
                                System.out.println("Invalid input.");
                            }
                        }catch (Exception e){
                            System.out.println("invalid input ");
                        }
                    }
                    break;

                case 9:
                    System.out.println("Are you sure you want to exit without saving? Type 1 for yes and 2 for no.");
                    int exit = 0;
                    while(true){
                        try{
                            exit = input.nextInt();
                            if(exit == 1){
                                System.out.println("Exiting");
                                return;
                            }else if (exit == 2){
                                break;
                            }else{
                                System.out.println("Invalid input.");
                            }
                        }catch (Exception e){
                            System.out.println("invalid input ");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid input. ");
            }
        }
    }

    private static void findSearchFilters(int searchValue, Scanner input){
        switch(searchValue){
            case 1:
                System.out.println("Please input the name of the class...");
                String name = input.nextLine();
                s.modifyFilter(new FilterAttribute(FilterAttribute.Option.NAME, name));
                break;

            case 2:
                System.out.println("Please input the department you want to search by...");
                String department = input.nextLine();
                s.modifyFilter(new FilterAttribute(FilterAttribute.Option.DEPT, department));
                break;

            case 3:
                System.out.println("Please input the instructor you want to search by");
                String instructor = input.nextLine();
                s.modifyFilter(new FilterAttribute(FilterAttribute.Option.INSTRUCTOR, instructor));
                break;

            case 4:
                System.out.println("Please input the course code you want to search by");
                String code = input.nextLine();
                s.modifyFilter(new FilterAttribute(FilterAttribute.Option.CODE, code));
                break;

            case 5:
                System.out.println("Please input the days of the week in the form MWF, TR, ...");
                System.out.println("If you want to ignore day of week, enter 'no'");
                String days = input.nextLine().toUpperCase();
                if(days.equals("NO")){
                    days = "";
                }

                int start;
                int end;
                while(true){
                    System.out.println("Please input the start time for your search in military time. \n");
                    System.out.println("If you don't want a start time, put in 'no'.");
                    String tempTime = input.nextLine();
                    if(tempTime.equalsIgnoreCase("no")){
                        start = 0;
                        break;
                    }

                    try{
                        start = Integer.parseInt(tempTime);
                        break;
                    }catch (Exception e){
                        System.out.println("No colons or AM/PM, just a number");
                        System.out.println("If an explanation of military time is needed put in y, if not put in whatever");
                        if(input.next().equals("y")){
                            System.out.println("Midnight is 0 and Noon is 1200.");
                            System.out.println("Every time in the am is in the hundreds.\n1:00 am = 100\n5:30 am = 530... ");
                            System.out.println("Every time in the pm is 1200 plus the time that it would be in am.\n1:00 pm = 1300\n9:32 pm = 2132");
                        }
                    }
                }

                while(true){
                    System.out.println("Please input the end time for your search in military time. ");
                    System.out.println("If you don't want a end time, put in 'no'.");
                    String tempTime = input.next();
                    if(tempTime.equalsIgnoreCase("no")){
                        end = 2400;
                        break;
                    }

                    try{
                        end = Integer.parseInt(tempTime);
                        break;
                    }catch (Exception e){
                        System.out.println("No colons or AM/PM, just a number");
                        System.out.println("If an explanation of military time is needed put in y, if not put in whatever");
                        if(input.next().equals("y")){
                            System.out.println("Midnight is 0 and Noon is 1200.");
                            System.out.println("Every time in the am is in the hundreds.\n1:00 am = 100\n5:30 am = 530... ");
                            System.out.println("Every time in the pm is 1200 plus the time that it would be in am.\n1:00 pm = 1300\n9:32 pm = 2132");
                        }
                    }
                }

                s.modifyFilter(
                        new FilterAttribute(FilterAttribute.Option.DAY, days),
                        new FilterAttribute(FilterAttribute.Option.START, start),
                        new FilterAttribute(FilterAttribute.Option.END, end)
                );
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
    private static void openSchedule(Scanner input){
        while (true) {
            System.out.println("Type 1 to create a new schedule or 2 to open an old one");
            int choice = 0;

            while(true){
                try {
                    choice = input.nextInt();
                    input.nextLine();

                    if(choice == 1 || choice == 2) break;
                    else System.out.println("Invalid input");
                } catch (Exception e) {
                    input.nextLine();
                }
            }



            // If they chose one they want a new schedule
            if (choice == 1) {
                currentSchedule = new Schedule(database);
                System.out.println("What would you like the name of this schedule to be?");

                currentSchedule.setScheduleName(input.nextLine());
                break;
            } else if (choice == 2) {
                ArrayList<Schedule> temp = u.getSavedSchedules();

                // If temp is empty, there are no saved schedules
                if (temp.isEmpty()) {
                    System.out.println("No saved schedules. Opening a blank one.");
                    currentSchedule = new Schedule(database);

                    System.out.println("What would you like the name of the new schedule to be?");
                    String name = input.nextLine();
                    currentSchedule.setScheduleName(name.trim());
                    break;
                }

                // If temp is not empty, then they must select a saved schedule
                while (true) {
                    // Provide the saved schedules names
                    System.out.println("Please select the number next to the desired schedule");
                    for (int i = 0; i < temp.size(); i++) {
                        System.out.println((i + 1) + ". " + temp.get(i).getScheduleName());
                    }

                    // They select a valid number
                    int desiredSchedule = 0;
                    while (true) {
                        try {
                            desiredSchedule = input.nextInt();
                            input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Please select a valid schedule");
                        }
                    }

                    currentSchedule = u.getSchedule(
                            temp.get(desiredSchedule - 1).getScheduleName()
                    );
                    break;

                }
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
    public static void generateDB() throws FileNotFoundException {
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
