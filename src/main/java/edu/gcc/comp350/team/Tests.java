package edu.gcc.comp350.team;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    // Testing Filters
    @Test
    public void testEmptyFilter() {
        assertEquals(new Filter(), "");
    }
}
