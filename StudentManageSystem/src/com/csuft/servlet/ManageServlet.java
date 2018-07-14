package com.csuft.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.StudentDao;
import com.csuft.entity.Student;

@WebServlet("/manageServlet")
public class ManageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		req.getSession().setAttribute("searchName", "");
		StudentDao stuDao = new StudentDao();
		List<Student> list = stuDao.queryLimit(0, 5);
		int count = stuDao.getCount();
		req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("count", count%5==0?count/5:count/5+1);
		resp.sendRedirect("manage.jsp");
	}
}
