package com.revature.tier3.answers;

import static com.revature.tier3.answers.PointsTests.addPoints;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.revature.config.TestConfig;

import org.hibernate.Session;
import org.junit.Test;

/**
 * prompt: Add an AWS RDS Instance jdbc url and credentials to the configuration
 * file (vendor can be either Oracle or PostgreSQL)
 */
public class Answer1Tests {

    /**
     * tests the connection to the db instance to ensure there is one
     */
    @Test
    public void test1() {
        try{
        Session sess = TestConfig.getInstance().openSession();
        assertTrue(sess.isConnected());
        addPoints(10);
        } catch(Exception e){
            fail();
        }
    }
}