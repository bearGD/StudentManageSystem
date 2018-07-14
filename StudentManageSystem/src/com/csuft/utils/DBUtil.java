package com.csuft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private final static String URL = "jdbc:mysql://localhost:3306/manage?characterEncoding=utf-8";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";

	/**
	 * 加载驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return 返回连接对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param pstm
	 * @param set
	 */
	public static void closeResource(Connection conn, PreparedStatement pstm, ResultSet set){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstm != null){
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(set != null){
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
