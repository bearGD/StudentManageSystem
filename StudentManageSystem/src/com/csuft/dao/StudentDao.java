package com.csuft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.csuft.entity.Student;
import com.csuft.utils.DBUtil;

/**
 * 学生Dao层,封装了对学生表的相关操作
 * 
 * @author 小组
 *
 */
public class StudentDao {

	/**
	 * 查询所有学生信息,并控制查询信息的数量和数据库中起始位置
	 * 
	 * @param index
	 *            数据库信息开始查询的位置
	 * @param size
	 *            查询的数量
	 * @return 返回查询到的List集合
	 */
	public List<Student> queryLimit(int index, int size) {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from student limit ?,?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		List<Student> list = new ArrayList<Student>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, index);
			pstm.setInt(2, size);
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

	public List<Student> queryLimitForChange(int index, int size) {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from student limit ?,?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		List<Student> list = new ArrayList<Student>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (index - 1) * size);
			pstm.setInt(2, size);
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

	public List<Student> searchLimit(int index, int count, String message) {
		Connection conn = DBUtil.getConnection();
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where name like ? or sex like ? or age like ? "
				+ "or telephone like ? or address like ? limit ?,?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + message + "%");
			pstm.setString(2, "%" + message + "%");
			pstm.setString(3, "%" + message + "%");
			pstm.setString(4, "%" + message + "%");
			pstm.setString(5, "%" + message + "%");
			pstm.setInt(6, index);
			pstm.setInt(7, count);
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

	public List<Student> searchLimitForChange(int index, int count, String message) {
		Connection conn = DBUtil.getConnection();
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where name like ? or sex like ? or age like ? "
				+ "or telephone like ? or address like ? limit ?,?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + message + "%");
			pstm.setString(2, "%" + message + "%");
			pstm.setString(3, "%" + message + "%");
			pstm.setString(4, "%" + message + "%");
			pstm.setString(5, "%" + message + "%");
			pstm.setInt(6, (index - 1) * count);
			pstm.setInt(7, count);
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
	 * 获取student表学生信息总数
	 * 
	 * @return 返回学生信息的数量
	 */
	public int getCount() {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) count from student";
		PreparedStatement pstm = null;
		int row = 0;
		ResultSet set = null;
		try {
			pstm = conn.prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				row = set.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, pstm, null);
		}
		return row;
	}
}
