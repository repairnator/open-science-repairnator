package com.revature.tier4.answers;

import static com.revature.tier4.answers.PointsTests.addPoints;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import com.revature.config.ConnectionUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * prompt: Establish a connection to a deployed database using JDBC.
 */
public class Answer1Tests {

	private ConnectionUtil cu;

	@Before
	public void setup() {
		cu = ConnectionUtil.getInstance();
	}

	@Test
	public void testConnection() throws SQLException {
		try {
			assertNotNull(cu.connect());
			assertTrue(cu.connect().isValid(5));
			addPoints(10);
		} catch (Exception e) {
			fail();
		}
	}
}