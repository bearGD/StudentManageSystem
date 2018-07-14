package com.csuft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.UserDao;
import com.csuft.utils.SHA256;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		PrintWriter pw = resp.getWriter();
		UserDao dao = new UserDao();
		if (username.isEmpty() || password.isEmpty()) {
			pw.write(
					"<script language='javascript'>alert('用户名和密码不能为空!');window.document.location.href='register.jsp'</script>");
		} else {
			// 验证用户名是否符合要求(汉字、字母、数字的组合)
			if (Pattern.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$", username)) {
				if (dao.isUserExist(username) > 0) {
					pw.write(
							"<script language='javascript'>alert('该用户名已存在!');window.document.location.href='register.jsp'</script>");
				} else {
					String pass = SHA256.sha256(password);
					int count = dao.insertToRegister(username, pass);
					if (count > 0) {
						pw.write(
								"<script language='javascript'>alert('注册成功!');window.document.location.href='login.jsp'</script>");
					} else {
						pw.write(
								"<script language='javascript'>alert('注册失败!请重试!');window.document.location.href='register.jsp'</script>");
					}
				}
			} else {
				pw.write(
						"<script language='javascript'>alert('用户名不可包含其他符号!');window.document.location.href='register.jsp'</script>");
			}
		}
	}

}
