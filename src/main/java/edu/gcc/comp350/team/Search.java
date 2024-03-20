package edu.gcc.comp350.team;
import java.util.ArrayList;
// I don't like sand. It's coarse and rough and irritating and it gets everywhere.
public class Search {
    private ArrayList<ArrayList<Class>> database;
    private ArrayList<Class> classes;
    private String curQueryText;
    private Filter filter;

    public Search(ArrayList<ArrayList<Class>> db){
        this.filter = new Filter();
        this.database = db;
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
    public ArrayList<Class> modifyFilter(FilterAttribute userFilter){
        for(ArrayList<Class> al: database){
            for(Class c: al){
                userFilter.getFilterOption();
            }
        }
        return null;
    }
    // use this for time filter
    public ArrayList<Class> modifyFilter(FilterAttribute day, FilterAttribute start, FilterAttribute end){
        return null;
    }

    /*
    example: called like removeFiler(FilterAttribute.Option.DAY)
     */
    public ArrayList<Class> removeFilter(FilterAttribute.Option f){
        return null;
    }


}
