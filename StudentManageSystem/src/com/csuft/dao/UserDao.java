package com.csuft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.csuft.entity.Student;
import com.csuft.entity.User;
import com.csuft.utils.DBUtil;

public class UserDao {

	/**
	 * 添加信息,实现注册功能
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int insertToRegister(String username, String password) {
		int row = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "insert into user(username, password) value(?, ?)";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, null);
		}
		return row;
	}

	/**
	 * 判断用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public int isUserExist(String username) {
		int count = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) count from user where username = ?";
		ResultSet set = null;
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			set = pstm.executeQuery();
			while (set.next()) {
				count = set.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, set);
		}
		return count;
	}

	/**
	 * 查询用户名和密码实现登录
	 * 
	 * @return
	 */
	public User queryToLogin(String username, String password) {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user where username = ? and password = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		User user = new User();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			set = pstm.executeQuery();
			while (set.next()) {
				user.setId(set.getInt("id"));
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, set);
		}
		return user;
	}

	/**
	 * 添加学生信息
	 * 
	 * @param name
	 * @param password
	 * @param sex
	 * @param age
	 * @param address
	 * @param telephone
	 * @return
	 */
	public int insertToAddStu(String name, String password, String sex, int age, String address, String telephone) {
		int row = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "insert into student(name,password,sex,age,address,telephone) value(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, password);
			pstm.setString(3, sex);
			pstm.setInt(4, age);
			pstm.setString(5, address);
			pstm.setString(6, telephone);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, null);
		}
		return row;
	}

	/**
	 * 查询所有学生信息
	 * 
	 * @return
	 */
	public List<Student> queryAll() {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from student";
		PreparedStatement pstm = null;
		ResultSet set = null;
		List<Student> list = new ArrayList<Student>();
		try {
			pstm = conn.prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				Student stu = new Student();
				stu.setId(set.getInt("id"));
				stu.setName(set.getString("name"));
				stu.setPassword(set.getString("password"));
				stu.setSex(set.getString("sex"));
				stu.setAge(set.getInt("age"));
				stu.setAddress(set.getString("address"));
				stu.setTelephone(set.getString("telephone"));
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, set);
		}
		return list;
	}

	/**
	 * 实现模糊搜索
	 * 
	 * @param message
	 * @return
	 */
	public List<Student> Search(String message) {
		Connection conn = DBUtil.getConnection();
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student " + "where name like ? or sex like ? or age like ? "
				+ "or telephone like ? or address like ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + message + "%");
			pstm.setString(2, "%" + message + "%");
			pstm.setString(3, "%" + message + "%");
			pstm.setString(4, "%" + message + "%");
			pstm.setString(5, "%" + message + "%");
			set = pstm.executeQuery();
			while (set.next()) {
				Student stu = new Student();
				stu.setId(set.getInt("id"));
				stu.setName(set.getString("name"));
				stu.setPassword(set.getString("password"));
				stu.setSex(set.getString("sex"));
				stu.setAge(set.getInt("age"));
				stu.setAddress(set.getString("address"));
				stu.setTelephone(set.getString("telephone"));
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, set);
		}
		return list;
	}

	/**
	 * 修改学生信息
	 * 
	 * @param name
	 * @param password
	 * @param sex
	 * @param age
	 * @param address
	 * @param telephone
	 * @return
	 */
	public int UpdateStudent(String id, String name, String password, String sex, int age, String address, String telephone) {
		int row = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "update student set "
				+ "name = ? , password = ? , sex = ? , age = ? , address = ? , telephone = ? where id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, password);
			pstm.setString(3, sex);
			pstm.setInt(4, age);
			pstm.setString(5, address);
			pstm.setString(6, telephone);
			pstm.setInt(7, Integer.parseInt(id));
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, null);
		}
		return row;
	}

	public Student putToWeb(int id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstm = null;
		String sql = "select * from student where id = ?";
		ResultSet set = null;
		Student stu = new Student();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			set = pstm.executeQuery();
			while (set.next()) {
				stu.setId(set.getInt("id"));
				stu.setName(set.getString("name"));
				stu.setPassword(set.getString("password"));
				stu.setSex(set.getString("sex"));
				stu.setAge(set.getInt("age"));
				stu.setAddress(set.getString("address"));
				stu.setTelephone(set.getString("telephone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, set);
		}
		return stu;
	}
	
	public boolean isNum(String str){
		for(int i = 0; i < str.length(); i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public int deletStu(int id){
		int row = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstm = null;
		String sql = "delete from student where id = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeResource(conn, pstm, null);
		}
		return row;
	}
	
	
	// public static void main(String[] args) {
	// UserDao dao = new UserDao();
	// User user = dao.queryToLogin("min", "456");
	// System.out.println(user.getUsername());
	// List<Student> list = dao.Search("25");
	// for (Student s : list) {
	// System.out.println(s);
	// }
	// int row = dao.UpdateStudent("hello", "123", "女", 26, "海南省",
	// "1248634563");
	// System.out.println(row);
	// }
}
