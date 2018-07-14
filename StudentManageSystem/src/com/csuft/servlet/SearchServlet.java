package com.csuft.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.StudentDao;
import com.csuft.dao.UserDao;
import com.csuft.entity.Student;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String searchName = req.getParameter("searchName");
		req.getSession().setAttribute("searchName", searchName);		
		StudentDao dao = new StudentDao();
		String pageNo = (String) req.getSession().getAttribute("pageNo");
		if(pageNo == null){
			pageNo = "" + 1;
		}
		req.setAttribute("searchValue", searchName);
		UserDao userdao = new UserDao();
		int count = userdao.Search(searchName).size();
		List<Student> searchList = dao.searchLimitForChange(Integer.parseInt(pageNo), 5, searchName);
		req.setAttribute("list", searchList);
		req.setAttribute("count", count%5==0?count/5:count/5+1);
		req.getRequestDispatcher("manage.jsp").forward(req, resp);
	}
}
