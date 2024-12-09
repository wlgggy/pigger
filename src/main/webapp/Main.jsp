<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="Create.createDTO"%>
<%@ page import="Create.createDAO"%>
<%@page import="java.sql.DriverManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/Main.css">
<title>게시판</title>
</head>
<body>
	<%
	createDAO createdao = new createDAO();
	ArrayList<createDTO> createlist = createdao.getAllDatas();
	%>
	<div class="container">
		<div class="header">
			<h1>게시판</h1>
		</div>
		<hr>
		<div class="session">
			<div class="notice_board_top">
				<button>
					<a href="Create.jsp">추가하기</a>
				</button>
			</div>
			<div class="notice_board_mid">
				<hr>
				<hr>
				<div class="nbm">
					<div class="number">
						<p>번호</p>
					</div>
					<div class="title">
						<p>제목</p>
					</div>
					<div class="name">
						<p>이름</p>
					</div>
					<div class="text">
						<p>내용</p>
					</div>
				</div>
				<hr>
				<hr>
				<div class="sub_nbm_content">
					<%
					for (int i = 0; i < createlist.size(); i++) {
						createDTO createdto = createlist.get(i);
					%>
					<div class="nbm">

						<div class="number">
							<p><%=createdto.getNotice_no()%></p>
						</div>
						<div class="title">
							<a href="Read.jsp?notice_no=<%=createdto.getNotice_no()%>"><%=createdto.getTitle()%></a>
						</div>
						<div class="name">
							<p><%=createdto.getName()%></p>
						</div>
						<div class="text">
							<p><%=createdto.getContent()%></p>
						</div>
						<div class="delete_update">
							<button class="update">
								<a href="Update.jsp?notice_no=<%=createdto.getNotice_no()%>">수정</a>
							</button>
							<form action="DeleteAction.jsp" method="post">
								<button class="delete">
									<a href="DeleteAction.jsp?notice_no=<%=createdto.getNotice_no()%>">삭제</a>
								</button>
							</form>
						</div>
					</div>
					<hr>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
