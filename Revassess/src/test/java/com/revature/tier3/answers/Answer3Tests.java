package com.revature.tier3.answers;

import static com.revature.tier3.answers.PointsTests.addPoints;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.revature.config.TestConfig;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * prompt: Create a table structure using the provided ERD, ensure that the
 * ability for auto-incrementing IDs
 */
public class Answer3Tests {

    @Test
    public void test3() {
        try{
        Session sess = TestConfig.getInstance().openSession();
        Transaction tx = sess.beginTransaction();
        assertNotNull(sess.createNativeQuery(
                "insert into app_user (username, password, first_name, last_name, role_id) values ('testing','testing','testing','testing',1"));
        assertNotNull(sess.createNativeQuery(
                "insert into flashcard (question, answer, category_id) values ('testing','testing',2)"));
        assertNotNull(sess.createNativeQuery("insert into study_set (name, owner_id) values ('testing',2)"));
        tx.rollback();
        addPoints(30);
        } catch(Exception e){
            fail();
        }
    }

}