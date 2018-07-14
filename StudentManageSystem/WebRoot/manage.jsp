
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>学生信息页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var id = $("#id").val();
			if(confirm("确定删除吗?")){
				var url = "deleteServlet?id=" + id;
				/* alert("删除成功!"); */
				window.document.location.href= url
			}else{
				alert("删除失败!");
				window.document.location.href="manageServlet"
			}
		});
	});
</script>
</head>

<body class="sd">
	<div class="sd-l">
		<div class="sd-l-top">
			<img src="image/user.jpg" width="100" height="100"> <a href="manageServlet">管理员</a>
		</div>
		<div class="sd-l-list">
			<li class="sd-list-li on"><span class="sd-list-title"><i
					class="icon-sz"></i>系统管理<i class="icon-fx"></i></span>
				<ul>
					<li><a href="add.jsp"><i class="icon-add"></i>添加学员</a></li>
					<li><a href="manageServlet"><i class="icon-list"></i>学员列表</a></li>
				</ul></li>
		</div>
	</div>
	<div class="sd-r">
		<a class="sd-r-box" href=""></a>
		<div class="sd-r-top"></div>
		<div class="sd-r-bottom">
			<div class="sd-r-title">添加学员</div>

			<div class="sd-box-opt">
				<div class="sd-opt-l fl">
					<a href="add.jsp"><span class="opt-add"></span></a>
				</div>
				<form action="searchServlet" method="post">
					<div class="sd-opt-r fr">
						<input type="text" placeholder="搜索" name="searchName">
						<input type="submit" value="搜索">
					</div>
				</form>
			</div>
			
			<div class="sd-box-center">
				<form action="manageServlet" method="post"></form>
				<table>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>学生姓名</th>
							<th>学生性别</th>
							<th>学生年龄</th>
							<th>学生电话</th>
							<th>学生地址</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
							<c:forEach items="${list}" var="list">	
							<tr>
								<td><input type="checkbox"></td>
								<td>${list.name}</td>
								<td>${list.sex}</td>
								<td>${list.age}</td>
								<td>${list.telephone}</td>
								<td>${list.address}</td>
								<td>
								<input type="hidden" id="id" value="${list.id}">
								<a <%-- href="deleteServlet?id=${list.id}" --%> class="delete">删除</a>
								<a href="updateServlet?id=${list.id}" >修改</a></td>
							</tr>
							</c:forEach>
					</tbody>
				</table>
				<div class="studio-table-page">
					<a href="pageServlet?pageNo=${pageNo==1?1:pageNo-1}">上一页</a>
					<c:forEach begin="1" end="${count}" step="1" varStatus="sta">
						<a href="pageServlet?pageNo=${sta.count}">${sta.count}</a>
					</c:forEach>
					<a href="pageServlet?pageNo=${pageNo+1>count?pageNo:pageNo+1}">下一页</a>
				</div>
			</div>
		</div>

		<script src="js/jquery-3.2.1.min.js"></script>
		<script>
			$(".icon-fx").on('click',function(){
				$(".sd-list-li ul").toggle();
				$(this).toggleClass("on");
			})
		</script>
</body>
</html>
