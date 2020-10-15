package com.revature.tier1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;
import static com.revature.tier1.PointsTests.addPoints;

/**
 * prompt:
 * Create a method that checks if 
 * two strings are equal and return 
 * the boolean result.
 */
public class Answer1Tests {

    @Test
    public void test1(){
        assertFalse(CompareStrings.compareStrings("s1", "s2"));
        assertTrue(CompareStrings.compareStrings("hello", new String("hello")));
        assertTrue(CompareStrings.compareStrings("longhorn", new String("longhorn")));
        assertTrue(CompareStrings.compareStrings("gentleman", new String("gentleman")));
        assertFalse(CompareStrings.compareStrings("jekyll", "hyde"));
        assertFalse(CompareStrings.compareStrings("giraffes", "real"));
        addPoints(10);
    }

}
