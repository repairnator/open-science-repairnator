package com.revature.tier4.answers;

import static com.revature.tier4.answers.PointsTests.addPoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.config.ConnectionUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * prompt: implement the ConnectionConfig class's callAbsoluteValueFunction
 * method with a callable statement to call the absolute value scalar function.
 */
public class Answer2Tests {
	private ConnectionUtil cu, mockedConnectionUtil;
	private CallableStatement mockedCallableStatement;
	private Connection mockedConnection;

	@Before
	public void setup() {
		cu = ConnectionUtil.getInstance();
		mockedCallableStatement = mock(CallableStatement.class);
		mockedConnection = mock(Connection.class);
		mockedConnectionUtil = mock(ConnectionUtil.class);
	}

	@Test
	public void test2() throws SQLException {
		try {
			// test the returned value
			assertEquals(100, cu.callAbsoluteValueFunction(-100));
			assertEquals(60, cu.callAbsoluteValueFunction(60));

			// test that the method uses a callable statement
			when(mockedConnectionUtil.connect()).thenReturn(mockedConnection);
			when(mockedConnection.prepareCall(anyString())).thenReturn(mockedCallableStatement);
			when(mockedConnectionUtil.callAbsoluteValueFunction(anyLong())).thenCallRealMethod();
			mockedConnectionUtil.callAbsoluteValueFunction(10);
			verify(mockedCallableStatement).execute();
			addPoints(20);
		} catch (Exception e) {
			fail();
		}
	}
}