package edu.gcc.comp350.team;

import org.junit.jupiter.api.Test;


import java.io.File;
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
    }

    FilterAttribute day2 = new FilterAttribute(FilterAttribute.Option.DAY, "Test");
    FilterAttribute instructor2 = new FilterAttribute(FilterAttribute.Option.INSTRUCTOR, "Test");
    FilterAttribute dept2 = new FilterAttribute(FilterAttribute.Option.DEPT, "Test");
    FilterAttribute name2 = new FilterAttribute(FilterAttribute.Option.NAME, "Test");
    FilterAttribute code2 = new FilterAttribute(FilterAttribute.Option.CODE, "Test");
    FilterAttribute start2 = new FilterAttribute(FilterAttribute.Option.CODE, 800);
    FilterAttribute end2 = new FilterAttribute(FilterAttribute.Option.CODE, 1100);

    @Test
    public void testOneFilter() {
        assertEquals(day2.getStringVal(), "Test");
        assertEquals(instructor2.getStringVal(), "Test");
        assertEquals(dept2.getStringVal(), "Test");
        assertEquals(name2.getStringVal(), "Test");
        assertEquals(code2.getStringVal(), "Test");
        assertEquals(start2.getIntVal(), 800);
        assertEquals(end2.getIntVal(), 1100);
    }

    @Test
    public void modifyFilter() {
        // TODO
    }

//    ArrayList<ArrayList<Class>> database = new ArrayList<>();
//    Main m = new Main();
//    m.generateDB();
//    User us = new User();
//    Schedule sch = new Schedule(database, "Schedule 1");
//    sch.addCourse(5);

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
}
