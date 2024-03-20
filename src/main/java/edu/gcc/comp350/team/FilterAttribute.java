package edu.gcc.comp350.team;

public class FilterAttribute {
    public enum Option {
        NAME,
        DEPT,
        INSTRUCTOR,
        DAY,
        START,
        END,
        CODE
    }
    private int intVal;


    private String stringVal;
    private Option filterOption;

    public FilterAttribute(Option a, int ival) {
        this.filterOption = a;
        intVal = ival;
    }
    public FilterAttribute(Option a, String sval) {
        this.filterOption = a;
        stringVal = sval;
    }

    public Option getFilterOption(){
        return filterOption;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public String getStringVal() {
        return stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }
}
