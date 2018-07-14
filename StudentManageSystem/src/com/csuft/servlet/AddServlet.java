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

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("b1");
		String password = req.getParameter("b2");
		String sex = req.getParameter("sex");
		String ag = req.getParameter("b4");
		String address = req.getParameter("b5");
		String telephone = req.getParameter("b6");
		PrintWriter pw = resp.getWriter();
		// 判断录入信息是否完整
		if (name == null || password == null || sex == null || ag == null || address == null || telephone == null) {
			pw.write(
					"<script language='javascript'>alert('请完整填写信息!');window.document.location.href='add.jsp'</script>");
		} else {
			if (name.isEmpty() || password.isEmpty() || sex.isEmpty() || ag.isEmpty() || address.isEmpty()
					|| telephone.isEmpty()) {
				pw.write(
						"<script language='javascript'>alert('请完整填写信息!');window.document.location.href='add.jsp'</script>");
			} else {
				// 判断年龄输入是否符合要求
				if (Pattern.matches("^(?:[1-9][0-9]?|1[01][0-9]|120)$", ag)) {
					// 判断电话号码是否符合要求
					if (Pattern.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$", telephone)) {
						// 判断密码是否符合要求(6-16位数字和字母的组合)
						if (Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", password)) {
							UserDao dao = new UserDao();
							int age = Integer.parseInt(ag);
							int count = dao.insertToAddStu(name, password, sex, age, address, telephone);
							if (count > 0) {
								pw.write(
										"<script language='javascript'>alert('添加学生成功!');window.document.location.href='manageServlet'</script>");
							} else {
								pw.write(
										"<script language='javascript'>alert('添加学生失败!');window.document.location.href='add.jsp'</script>");
							}
						} else {
							pw.write(
									"<script language='javascript'>alert('添加学生失败!请输入正确密码格式!(6-16位数字和字母的组合)');window.document.location.href='add.jsp'</script>");
						}
					} else {
						pw.write(
								"<script language='javascript'>alert('添加学生失败!请输入正确的手机号码!');window.document.location.href='add.jsp'</script>");
					}
				} else {
					pw.write(
							"<script language='javascript'>alert('添加学生失败!请输入正确的年龄格式(1-120)!');window.document.location.href='add.jsp'</script>");
				}
			}
		}
	}
}
