package com.revature.tier4.answers;

import static com.revature.tier4.answers.PointsTests.addPoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.revature.dao.CrudRepository;
import com.revature.dao.FlashcardRepository;
import com.revature.exception.ResourceNotfoundException;
import com.revature.exception.ResourcePersistenceException;
import com.revature.model.Role;

import org.junit.Test;

/**
 * prompt: Implement the entire class structure indicated in the provided class
 * diagram
 */
public class Answer4Tests {

    @Test
    public void testClasses() {
    	try {
        assertEquals("BASIC_USER", String.valueOf(Role.class .getEnumConstants()[2]));
        assertEquals(ResourceNotfoundException.class.getSuperclass(), Exception.class);
        assertEquals(CrudRepository.class, FlashcardRepository.class.getInterfaces()[0]);
        assertEquals(ResourcePersistenceException.class.getSuperclass(), Exception.class);
        addPoints(40);
    	} catch(Exception e) {
    		fail();
    	}
    }
}