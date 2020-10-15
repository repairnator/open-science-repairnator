package com.revature.tier6.answers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PointsTests {

    private static int points;

    public static void addPoints(int x){
        points+=x;
    }

    public static int getPoints(){
        return points;
    }

    @Before
    public void print(){
        System.out.println("_points:"+points);
    }


    @Test
    public void testPoints(){
        assertTrue(points>=200);
    }
}