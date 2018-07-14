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

@WebServlet("/updateServlet2")
public class UpdateServlet2 extends HttpServlet {

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
		String d = req.getParameter("id");
		String name = req.getParameter("b1");
		String password = req.getParameter("b2");
		String sex = req.getParameter("sex");
		String ag = req.getParameter("b4");
		String address = req.getParameter("b5");
		String telephone = req.getParameter("b6");
		PrintWriter pw = resp.getWriter();
		if (name.isEmpty() || password.isEmpty() || sex.isEmpty() || ag.isEmpty() || address.isEmpty()
				|| telephone.isEmpty()) {
			pw.write(
					"<script language='javascript'>alert('修改信息不可为空!');window.document.location.href='manageServlet'</script>");
		} else {
			// 判断年龄输入是否符合要求
			if (Pattern.matches("^(?:[1-9][0-9]?|1[01][0-9]|120)$",ag)) {
				// 判断电话号码是否符合要求
				if (Pattern.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$", telephone)) {
					// 判断密码是否符合要求(6-16位数字和字母的组合)
					if (Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", password)) {
						int age = Integer.parseInt(ag);
						int count = dao.UpdateStudent(d, name, password, sex, age, address, telephone);
						if (count > 0) {
							pw.write(
									"<script language='javascript'>alert('修改信息成功!');window.document.location.href='manageServlet'</script>");
						} else {
							pw.write(
									"<script language='javascript'>alert('修改信息失败!');window.history.go(-1)</script>");
						}
					} else {
						pw.write(
								"<script language='javascript'>alert('修改失败!请输入符合要求的密码!(6-16位数字和字母的组合)');window.history.go(-1)</script>");
					}
				} else {
					pw.write(
							"<script language='javascript'>alert('修改失败!请输入正确的电话号码!');window.history.go(-1)</script>");
				}
			} else {
				pw.write(
						"<script language='javascript'>alert('修改失败!请输入正确的年龄!(1-120)');window.history.go(-1)</script>");
			}
		}
	}
}
