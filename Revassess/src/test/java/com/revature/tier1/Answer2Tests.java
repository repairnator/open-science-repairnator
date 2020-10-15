package com.revature.tier1;

import static com.revature.tier1.PointsTests.addPoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * prompt:
 * Implement a POJO using the 
 * provided UML class model
 */
public class Answer2Tests {

    @Test
    public void test2() {
        Class<User> userClass;
        List<Field> fields;
        String[] fieldNames = { "id", "firstName", "lastName", "username", "password", "role" };
        userClass = User.class;
        fields = Arrays.asList(userClass.getDeclaredFields());
        assertEquals(fields.size(), fieldNames.length);
        fields.stream().forEach(e -> assertNotEquals(Arrays.asList(fieldNames).indexOf(e.getName()), -1));
        addPoints(20);
    }

}