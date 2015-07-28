<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Spring JdbcTemplate</title>
	</head>
	<body>
		<div align="center">
			<h2>Spring JdbcTemplate Implementation</h2><br>	
			<c:url var="addAction" value="/add-edit" ></c:url>
			<form:form method="post" action="${addAction}" commandName="user">
				<table>
					<tr>
						<td><label for="userid">Userid:</label></td>
						<td><form:input readonly="true" path="userid" /></td>
					</tr>
					<tr>
						<td><label for="username">Username:</label></td>
						<td><form:input path="username" /></td>
					</tr>
					<tr>
						<td><label for="password">Password:</label></td>
						<td><form:input path="password" /></td>
					</tr>
					<tr>
						<td><label for="email">Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td colspan="2">			            
							<c:if test="${!empty user.username}">
								<input type="submit" value="<spring:message text="Edit Person"/>" />
							</c:if>
							<c:if test="${empty user.username}">
								<input type="submit" value="<spring:message text="Save"/>" />
							</c:if>
						</td>
					</tr>
				</table> 
			</form:form><br>
			<h3>List of Users....</h3>
			<table border="1">
				<tr>
					<td>Sl.</td>
					<td>Username</td>
					<td>Email</td>
					<td>Action..</td>
					<td>Action..</td>
				</tr> 
				<c:forEach var="user" items="${userList}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td><a href="<c:url value='edit/${user.userid}' />" >Edit</a></td>
						<td><a href="delete/${user.userid}">delete</a></td>
					</tr>
				</c:forEach>             
			</table> 
		</div>
	</body>
</html>