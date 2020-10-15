package com.revature.tier3.answers;

/**
 * prompt: 
 * Implement a stored procedure that takes 
 * three integers (i,j and k) and finds how
 * many numbers between i and j (inclusively)
 * are divisible by k. 
 * Example: proc(12, 18, 3) -> returns 3
 * 12/3 = 4
 * 13/3 = 4.333...
 * 14/3 = 4.667...
 * 15/3 = 5
 * 16/3 = 5.333...
 * 17/3 = 5.667...
 * 18/3 = 6
 * So there are 3 numbers in the inclusive range from 
 * 12 to 18 that are divisible by 3.
 */
public class Answer4Tests {


    // @Test
    // public void test4() {
    //   try (Connection conn = DriverManager.getConnection(ConnectionUtil.URL, ConnectionUtil.USERNAME, ConnectionUtil.PASSWORD)) {
    // 	  assertEquals(4, callFun(conn, 3, 18, 4));
    // 	  assertEquals(8, callFun(conn, 12, 54, 6));
    // 	  assertEquals(1, callFun(conn, 9, 30, 27));
    //   } catch(Exception e){
    //       fail();
    //   }
    //   addPoints(40);
    // }
    
    // private int callFun(Connection conn, int i, int j, int k) throws SQLException {
    // 	 String sql = "{ call " + ConnectionUtil.TIER_3_PROCEDURE_NAME + "(?, ?, ?) }";
    //      CallableStatement cs = conn.prepareCall(sql);
    //      cs.setInt(1, i);
    //      cs.setInt(2, j);
    //      cs.setInt(3, k);
    //      ResultSet rs = cs.executeQuery();
    //      while(rs.next()) {
    //     	 return rs.getInt(1);
    //      }
    // 	return -1;
    // }


  }