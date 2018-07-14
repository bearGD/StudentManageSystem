package com.csuft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.UserDao;
import com.csuft.entity.User;
import com.csuft.utils.SHA256;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

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
		String username = req.getParameter("username");
		String password = SHA256.sha256(req.getParameter("password"));
		UserDao dao = new UserDao();
		User user = dao.queryToLogin(username, password);
		PrintWriter pw = resp.getWriter();
		if (username.isEmpty()) {
			pw.write(
					"<script language='javascript'>alert('登录失败!用户名不能为空!');window.document.location.href='login.jsp'</script>");
		} else {
			if (user.getId() != 0) {
				resp.sendRedirect("manageServlet");
			} else {
				pw.write(
						"<script language='javascript'>alert('登录失败!');window.document.location.href='login.jsp'</script>");
			}

		}
	}
}
