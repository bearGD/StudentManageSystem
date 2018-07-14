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

@WebServlet("/pageServlet")
public class PageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String pageNo = req.getParameter("pageNo");
		String searchName = (String) req.getSession().getAttribute("searchName");
		req.getSession().setAttribute("pageNo", pageNo);
		if (searchName == null) {
			StudentDao dao = new StudentDao();
			int count = dao.getCount();
			List<Student> list = dao.queryLimitForChange(Integer.parseInt(pageNo), 5);
			req.setAttribute("list", list);
			req.setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);
			req.getRequestDispatcher("manage.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("searchServlet?searchName=" + searchName).forward(req, resp);
		}
	}
}
