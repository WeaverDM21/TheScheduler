package edu.gcc.comp350.team;
import java.util.ArrayList;
// I don't like sand. It's coarse and rough and irritating and it gets everywhere.
public class Search {
    private ArrayList<ArrayList<Class>> database;
    private ArrayList<ArrayList<Class>> classes;
    private String curQueryText;
    private ArrayList<FilterAttribute> filters;

    public Search(ArrayList<ArrayList<Class>> db){
        this.filters = new ArrayList<>();
        this.database = db;
        classes = new ArrayList<>();
        for(ArrayList<Class> temp : database){
            classes.add(temp);
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
        if(userFilter == null){
            return classes;
        }else{
            this.filters.removeIf(
                    filter -> filter.getFilterOption() == userFilter.getFilterOption()
            );
            this.filters.add(userFilter);
        }

        boolean courseID = userFilter.getFilterOption() == FilterAttribute.Option.CODE;
        for(ArrayList<Class> cs : database){
            for(Class c : cs){
                if(userFilter.getFilterOption() == FilterAttribute.Option.NAME){

                }else if(userFilter.getFilterOption() == FilterAttribute.Option.DEPT){

                }else if(userFilter.getFilterOption() ==  FilterAttribute.Option.INSTRUCTOR){

                }else if(userFilter.getFilterOption() ==  FilterAttribute.Option.DAY){

                }else if(userFilter.getFilterOption() ==  FilterAttribute.Option.START){

                }else if (userFilter.getFilterOption() ==  FilterAttribute.Option.END){

                }else{ // TODO FilterAttribute.Option.CODE

                }
            }
        }
        return null;
    }
    // use this for time filter
    public ArrayList<Class> modifyFilter(FilterAttribute day, FilterAttribute start, FilterAttribute end){
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

        return null;
    }

    /*
    example: called like removeFiler(FilterAttribute.Option.DAY)
     */
    public ArrayList<Class> removeFilter(FilterAttribute.Option f){
        for(FilterAttribute filter: this.filters){
            if(f == filter.getFilterOption()){
                this.filters.remove(filter);
            }
        }
        return null;
    }


}
