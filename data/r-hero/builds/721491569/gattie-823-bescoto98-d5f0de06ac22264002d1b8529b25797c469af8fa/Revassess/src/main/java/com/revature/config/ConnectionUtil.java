package com.revature.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Revature
 *
 *         This is a paceholder class to hold the configurations of your db
 *         connection. You should change the url, username, and password. DO NOT
 *         CHANGE THE MODIFIERS OR THE NAMES OF THE VARIABLES. These are used to
 *         test your db schema.
 */
public class ConnectionUtil {
	//for singleton instance
	private static ConnectionUtil cu;
	
	// add your jdbc url
	public static final String URL = "jdbc:postgresql://javafs.cleuzgwokgxs.us-east-2.rds.amazonaws.com:5432/assessment";
	// add your jdbc username
	public static final String USERNAME = "postgres";
	// add your jdbc password
	public static final String PASSWORD = "rootroot";
	// name of the created stored procedure in tier 3
	public static final String TIER_3_PROCEDURE_NAME = "setid";
	// name of the created sequence in tier 3
	public static final String TIER_3_SEQUENCE_NAME = "setid";

	// implement this method to connect to the db and return the connection object
	public Connection connect() throws SQLException{
		
		try{
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}

		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	}


	//implement this method with a callable statement that calls the absolute value sql function
	public long callAbsoluteValueFunction(long value){
		
		try(Connection conn = connect()){
			
			String sql = "select abs ("+value+") as returnedValue;";
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			long temp = 0;
			
			if(result.next()) {
				temp = result.getLong("returnedValue");
				
			}
			
			return temp;
		}
		catch(SQLException e) {
			e.printStackTrace();
				
		}
//		SELECT Abs(-243.5) AS AbsNum; 
		
		
		return value;
	}
	

	//make the class into a singleton
	private ConnectionUtil(){
		super();
	}

	public static ConnectionUtil getInstance(){
		if(cu == null){
			cu = new ConnectionUtil();
		}
		return cu;
	}
}
