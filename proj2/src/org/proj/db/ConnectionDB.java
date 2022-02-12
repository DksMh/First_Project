package org.proj.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	public static Connection getConnection() {
		String protocol = "jdbc:mariadb://";
		String ip = "127.0.0.1";
		String port = "3306";
		String db = "projdata"; 
		String url = String.format("%s%s:%s/%s",protocol,ip,port,db);
		String user = "root";
		String password = "1234";
		
		try {
			// 클래스파일 확장자 빼야됨.
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found...");
		} catch (SQLException e) {
			System.out.println("SQL exeption ...");
		}
		return null;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
