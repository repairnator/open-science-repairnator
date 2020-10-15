package com.revature.tier1;

import static com.revature.tier1.PointsTests.addPoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * prompt:
 * Create a method that iterates 
 * over an integer array and returns 
 * their sum.
 */
public class Answer3Tests {

    @Test
    public void test3() {
        int[] arr = { 1, 2, 3 };
        assertNotEquals(0, SumOverArray.IterateAndSum(arr));
        try {
            SumOverArray.IterateAndSum(null);
            assertTrue(true);
        } catch (NullPointerException e) {
            fail();
        }
        int [] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        assertEquals(78, SumOverArray.IterateAndSum(arr2));
        int[] arr3 = { -1, -2, -3, -4, -5, -6, -7, -8, -9 };
		assertEquals(-45, SumOverArray.IterateAndSum(arr3));
		int[] arr4 = { 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 };
		assertEquals(1095, SumOverArray.IterateAndSum(arr4));
        addPoints(30);
    }

}