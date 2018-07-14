package com.csuft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.UserDao;
import com.csuft.entity.Student;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		UserDao dao = new UserDao();
		String id = req.getParameter("id");
		Student stu = dao.putToWeb(Integer.parseInt(id));
		req.setAttribute("id", stu.getId());
		req.setAttribute("name", stu.getName());
		req.setAttribute("password", stu.getPassword());
		req.setAttribute("sex", stu.getSex());
		req.setAttribute("age", stu.getAge());
		req.setAttribute("address", stu.getAddress());
		req.setAttribute("telephone", stu.getTelephone());
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}
}
