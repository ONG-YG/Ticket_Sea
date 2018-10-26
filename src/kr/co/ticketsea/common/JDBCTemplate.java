package kr.co.ticketsea.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
		public static Connection getConnection() {
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
													"ts_test","ticketsea");
				conn.setAutoCommit(false); //오토커밋 해제
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
		public static void close(Connection conn) {	
			try {
				if(conn != null && !conn.isClosed()) {
					// conn이 null이 아니거나
					// conn이 이미 종료된 상태가 아니라면
					// close 작업을 해주어라!
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		public static void close(Statement stmt) {	
			try {
				if(stmt != null && !stmt.isClosed()) {
					// stmt이 null이 아니거나
					// stmt이 이미 종료된 상태가 아니라면
					// close 작업을 해주어라!
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(ResultSet rset) {	
			
			try {
				if(rset != null && !rset.isClosed()) {
					// rset이 null이 아니거나
					// rset이 이미 종료된 상태가 아니라면
					// close 작업을 해주어라!
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		public static void commit(Connection conn) {	
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void rollback(Connection conn) {	
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}

