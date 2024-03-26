package edu.gcc.comp350.team;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Search {
    private ArrayList<ArrayList<Class>> database;
    private ArrayList<ArrayList<Class>> curClasses;
    private ArrayList<ArrayList<Class>> newClasses;
    private String curQueryText;
    private ArrayList<FilterAttribute> filters;

    public Search(ArrayList<ArrayList<Class>> db){
        this.filters = new ArrayList<>();
        this.database = db;
        curClasses = new ArrayList<>();
        curClasses.addAll(database);
        newClasses = new ArrayList<>();
    }

    private boolean meetsCriteria(Class c){
        // TODO: Implement
        return false;
    }

    public ArrayList<Class> modifyQuery(String newQueryText){

        return null;
    }

    // use this for dept, prof ... everything but time
    // 2 cases to address - add for first time, change
    public ArrayList<ArrayList<Class>> modifyFilter(FilterAttribute userFilter){

        if(userFilter == null){
            return curClasses;
        }else{
            this.filters.removeIf(
                    filter -> filter.getFilterOption() == userFilter.getFilterOption()
            );
            this.filters.add(userFilter);
        }

        for(ArrayList<Class> cs : curClasses){
            for(Class c : cs){
                if(userFilter.getFilterOption() == FilterAttribute.Option.NAME){
                    if(c.getCourseName().equalsIgnoreCase(userFilter.getStringVal())){
                        this.newClasses.add(cs);
                    }
                }else if(userFilter.getFilterOption() == FilterAttribute.Option.DEPT){
                    if(c.getDepartment().equalsIgnoreCase(userFilter.getStringVal())){
                        this.newClasses.add(cs);
                    }
                }else if(userFilter.getFilterOption() ==  FilterAttribute.Option.INSTRUCTOR){
                    if(c.getInstructor().equalsIgnoreCase(userFilter.getStringVal())){
                        this.newClasses.add(cs);
                    }
                }else{
                    if(c.getCourseID().equalsIgnoreCase(userFilter.getStringVal())){
                        this.newClasses.add(cs);
                    }
                }
            }
        }
        for(ArrayList<Class> c : newClasses){
            System.out.println(c);
        }
        this.curClasses = newClasses;
        newClasses = new ArrayList<>();
        return curClasses;
    }
    // use this for time filter
    public ArrayList<ArrayList<Class>> modifyFilter(FilterAttribute day, FilterAttribute start, FilterAttribute end){
        if(day != null){
            this.filters.removeIf(
                    filter -> filter.getFilterOption() == day.getFilterOption()
            );
            this.filters.add(day);
        }

        if(start != null){
            this.filters.removeIf(
                    filter -> filter.getFilterOption() == start.getFilterOption()
            );
            this.filters.add(start);
        }

        if(end != null){
            this.filters.removeIf(
                    filter -> filter.getFilterOption() == end.getFilterOption()
            );
            this.filters.add(end);
        }

        for(ArrayList<Class> cs : this.curClasses){
            for(Class c : cs){
                if(!day.getStringVal().isEmpty()){
                    if(c.getDaysOfWeek().equals(day.getStringVal()) && c.getBeginTime() >= start.getIntVal() && c.getEndTime() <= end.getIntVal()){
                        this.newClasses.add(cs);
                        break;
                    }
                }else{
                    if(c.getBeginTime() >= start.getIntVal() && c.getEndTime() <= end.getIntVal()){
                        this.newClasses.add(cs);
                        break;
                    }
                }
            }
        }

        this.curClasses = newClasses;
        newClasses = new ArrayList<>();

        for(ArrayList<Class> al : curClasses){
            System.out.println(al);
        }

        return curClasses;
    }


    /*
    example: called like removeFiler(FilterAttribute.Option.DAY)
     */
    public ArrayList<Class> removeFilter(FilterAttribute.Option f){
        for(int i =0; i < filters.size(); i++){
            if(f == filters.get(i).getFilterOption()){
                this.filters.remove(filters.get(i));
                i--;
            }
        }
        return null;
    }

    public ArrayList<FilterAttribute> printFilterAttributes(){
        for(FilterAttribute f : filters){
            System.out.println(f);
        }
        return filters;
    }

    public ArrayList<ArrayList<Class>> printCurrClasses(){
        for(int i = 0; i < curClasses.size(); i++){
            System.out.println((i+1) + " " + curClasses.get(i).get(0));
        }

        return curClasses;
    }


}
