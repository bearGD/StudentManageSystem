package com.csuft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csuft.dao.UserDao;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		UserDao dao = new UserDao();
		String id = req.getParameter("id");
		int count = dao.deletStu(Integer.parseInt(id));
		PrintWriter pw = resp.getWriter();
		if(count > 0){
			pw.write(
					"<script language='javascript'>alert('删除信息成功!');window.document.location.href='manageServlet'</script>");
		}else{
			pw.write(
					"<script language='javascript'>alert('删除信息失败!');window.document.location.href='manageServlet'</script>");
		}
	}
	
}
