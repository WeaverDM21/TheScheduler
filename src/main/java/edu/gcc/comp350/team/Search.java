package edu.gcc.comp350.team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Search {
    private ArrayList<ArrayList<Class>> database;
    private ArrayList<ArrayList<Class>> curClasses;
    private ArrayList<ArrayList<Class>> newClasses;
    private String curQueryText;
    private HashMap<FilterAttribute.Option, FilterAttribute> filters;

    public Search(ArrayList<ArrayList<Class>> db){
        this.filters = new HashMap<>();
        fillFilters();
        this.database = db;
        curClasses = new ArrayList<>();
        curClasses.addAll(database);
        newClasses = new ArrayList<>();
    }

    private void fillFilters(){
        for(FilterAttribute.Option option : FilterAttribute.Option.values())
        {
            if(option == FilterAttribute.Option.START)
            {
                filters.put(option, new FilterAttribute(option, 0));
            }
            else if(option == FilterAttribute.Option.END)
            {
                filters.put(option, new FilterAttribute(option, 2400));
            }
            else
            {
                filters.put(option, new FilterAttribute(option, ""));
            }
        }
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
        FilterAttribute prevFilter = this.filters.get(userFilter.getFilterOption());
        if(!prevFilter.getStringVal().equals("")){
            filters.put(userFilter.getFilterOption(), userFilter);
            removeFilter();
            return curClasses;
        }

        filters.put(userFilter.getFilterOption(), userFilter);
        if(userFilter.getStringVal().equals("")){
            return curClasses;
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
        if(day.getStringVal().equals("") && start.getIntVal() == 0 && end.getIntVal() == 2400) return curClasses;

        FilterAttribute dayTemp = this.filters.get(day.getFilterOption());
        FilterAttribute startTemp = this.filters.get(start.getFilterOption());
        FilterAttribute endTemp = this.filters.get(end.getFilterOption());

        if(!dayTemp.getStringVal().equals("") || startTemp.getIntVal() != 0 || endTemp.getIntVal() != 2400){
            this.filters.put(day.getFilterOption(), day);
            this.filters.put(start.getFilterOption(), start);
            this.filters.put(end.getFilterOption(), end);
            removeFilter();
            return curClasses;
        }

        this.filters.put(day.getFilterOption(), day);
        this.filters.put(start.getFilterOption(), start);
        this.filters.put(end.getFilterOption(), end);

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

    private ArrayList<ArrayList<Class>> removeFilter(){
        for(ArrayList<Class> cs : database){
            boolean fitsAll = true;
            for(Class c : cs){

                for(FilterAttribute f : getActiveFilterAttributes()){
                    if(!c.fits(f)){
                        fitsAll = false;
                        break;
                    }
                }
                if(!fitsAll) break;
            }

            if(fitsAll) {
                newClasses.add(cs);
            }
        }

        curClasses = newClasses;
        newClasses = new ArrayList<>();
        return curClasses;
    }

    /*
    example: called like removeFiler(FilterAttribute.Option.DAY)
     */
    // TODO Go through all the active search filters and take from database those that fit
    public ArrayList<Class> removeFilter(FilterAttribute.Option f){
        if(f != FilterAttribute.Option.START && f != FilterAttribute.Option.END){
            this.filters.put(f, new FilterAttribute(f, ""));
        }else{
            if(f == FilterAttribute.Option.START)
                this.filters.put(f, new FilterAttribute(f, 0));
            else
                this.filters.put(f, new FilterAttribute(f, 2400));
        }
        return null;
    }

    public ArrayList<FilterAttribute> printFilterAttributes(){
        ArrayList<FilterAttribute> returnable = new ArrayList<>();
        for(FilterAttribute f : filters.values()){
            if(!f.isEmpty()){
                System.out.println(f);
                returnable.add(f);
            }
        }
        System.out.println();
        return returnable;
    }

    public ArrayList<FilterAttribute> getActiveFilterAttributes(){
        ArrayList<FilterAttribute> returnable = new ArrayList<>();
        for(FilterAttribute f : filters.values()){
            if(!f.isEmpty()){
                returnable.add(f);
            }
        }
        return returnable;
    }

    public HashMap<FilterAttribute.Option, FilterAttribute> filterList(){
        return filters;
    }

    public ArrayList<ArrayList<Class>> printCurrClasses(){
        for(int i = 0; i < curClasses.size(); i++){
            System.out.println((i+1) + " " + curClasses.get(i).get(0));
        }

        return curClasses;
    }


}
