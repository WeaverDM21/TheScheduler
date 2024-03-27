package edu.gcc.comp350.team;

import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MyClassTest {

//    @Test
//    void addTwo(){
//        assertEquals("", Class.getTimeOfDay());
//    }

    // Testing Filters
    FilterAttribute day = new FilterAttribute(FilterAttribute.Option.DAY, "");
    FilterAttribute instructor = new FilterAttribute(FilterAttribute.Option.INSTRUCTOR, "");
    FilterAttribute dept = new FilterAttribute(FilterAttribute.Option.DEPT, "");
    FilterAttribute name = new FilterAttribute(FilterAttribute.Option.NAME, "");
    FilterAttribute code = new FilterAttribute(FilterAttribute.Option.CODE, "");
    FilterAttribute start = new FilterAttribute(FilterAttribute.Option.CODE, 0);
    FilterAttribute end = new FilterAttribute(FilterAttribute.Option.CODE, 0);
    @Test
    public void testEmptyFilter() {
        assertEquals(day.getStringVal(), "");
        assertEquals(instructor.getStringVal(), "");
        assertEquals(dept.getStringVal(), "");
        assertEquals(name.getStringVal(), "");
        assertEquals(code.getStringVal(), "");
        assertEquals(start.getIntVal(), 0);
        assertEquals(end.getIntVal(), 0);

        // Search with no filter
        Search search = new Search(Main.database);
        assertEquals(search.modifyFilter(null), Main.database);
    }

    FilterAttribute day2 = new FilterAttribute(FilterAttribute.Option.DAY, "Test");
    FilterAttribute instructor2 = new FilterAttribute(FilterAttribute.Option.INSTRUCTOR, "Test");
    FilterAttribute dept2 = new FilterAttribute(FilterAttribute.Option.DEPT, "Test");
    FilterAttribute name2 = new FilterAttribute(FilterAttribute.Option.NAME, "Test");
    FilterAttribute code2 = new FilterAttribute(FilterAttribute.Option.CODE, "Test");
    FilterAttribute start2 = new FilterAttribute(FilterAttribute.Option.START, 800);
    FilterAttribute end2 = new FilterAttribute(FilterAttribute.Option.END, 1100);

    @Test
    public void testOneFilter() throws FileNotFoundException {
        assertEquals(day2.getStringVal(), "Test");
        assertEquals(instructor2.getStringVal(), "Test");
        assertEquals(dept2.getStringVal(), "Test");
        assertEquals(name2.getStringVal(), "Test");
        assertEquals(code2.getStringVal(), "Test");
        assertEquals(start2.getIntVal(), 800);
        assertEquals(end2.getIntVal(), 1100);

        //test search with one filter
        Main.generateDB();
        Search filteredSearch = new Search(Main.database);
        FilterAttribute DEPT = new FilterAttribute(FilterAttribute.Option.DEPT, "COMP");
        ArrayList<ArrayList<Class>> classes = filteredSearch.modifyFilter(DEPT);
        assertTrue(searchTestString(classes, "COMP", "DEPT"));
        assertEquals(filteredSearch.modifyFilter(DEPT).size(), 40);
    }

    @Test
    public void modifyFilter() throws FileNotFoundException {
        start2.setIntVal(900);
        assertEquals(start2.getIntVal(), 900);
        code2.setStringVal("stuff");
        assertEquals(code2.getStringVal(), "stuff");

        Main.generateDB();
        Search filters = new Search(Main.database);
        filters.modifyFilter(start2);
        filters.modifyFilter(code2);
        System.out.println("yes");
        assertEquals(filters.filterList().get(0).getIntVal(), 900);
        assertEquals(filters.filterList().get(1).getStringVal(), "stuff");

        //TODO: Test the actual search with multiple filters
        Search filteredSearch = new Search(Main.database);
        FilterAttribute DEPT = new FilterAttribute(FilterAttribute.Option.DEPT, "COMP");
        FilterAttribute Start = new FilterAttribute(FilterAttribute.Option.START, 800);
        FilterAttribute End = new FilterAttribute(FilterAttribute.Option.END, 1200);
        FilterAttribute Day = new FilterAttribute(FilterAttribute.Option.DAY, "");
        filteredSearch.modifyFilter(DEPT);
        ArrayList<ArrayList<Class>> classes = filteredSearch.modifyFilter(Day, Start, End);

        assertTrue(searchTestString(classes, "COMP", "DEPT"));
        assertTrue(searchTestInt(classes, 800, 1200, "START"));
        assertEquals(filteredSearch.modifyFilter(DEPT).size(), 14);

    }


    // Add Course
    @Test
    void testAddFirstCourseToSchedule() {

//        schedule.addCourse(0);
//        assertEquals(1, schedule.getNumCourses());
//        assertEquals(3, schedule.getNumCredits());
    }

    @Test
    void testAddAnotherCourseToSchedule() {

//        schedule.addCourse(0);
//        assertEquals(1, schedule.getNumCourses());
//        assertEquals(3, schedule.getNumCredits());
    }

    @Test
    void testRemoveCourseFromSchedule() {

//        schedule.removeCourse(0);
//        assertEquals(1, schedule.getNumCourses());
//        assertEquals(3, schedule.getNumCredits());
    }

    @Test
    void testOverlapofCourses() {

    }

    boolean searchTestString(ArrayList<ArrayList<Class>> classes, String filter, String type) {
        for (ArrayList<Class> c : classes) {
            for (Class x: c) {
                if (type.equals("DEPT")) {
                    if (!x.getDepartment().equals(filter)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean searchTestInt(ArrayList<ArrayList<Class>> classes, int start, int end, String type) {
        for (ArrayList<Class> c : classes) {
            for (Class x: c) {
                if (x.getBeginTime() < start || x.getEndTime() > end) {
                    return false;
                }
            }
        }
        return true;
    }
}
