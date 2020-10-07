<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<h1>User List</h1>
	<security:authorize access="hasRole('ROLE_ADMIN') && isAuthenticated()">
		<div>
			<a href="user_form" class="btn btn-primary">Add User</a>
		</div>
	</security:authorize>
	<c:if test="${!empty users}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>User Name</th>
					<th>Password</th>
					<th>Email</th>
					<th>Mobile No</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Hobbies</th>
					<th>Nationality</th>
					<th>Address</th>
					<th>Comments</th>
					<th>Image</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.userName}</td>
						<td>${user.password}</td>
						<td>${user.email}</td>
						<td>${user.mobileNo}</td>
						<td>${user.dob}</td>
						<td>${user.gender}</td>
						<td>${user.hobbies}</td>
						<td>${user.nationality}</td>
						<td>${user.address.cityName},${user.address.countryName}</td>
						<td>${user.comments}</td>
						<td><image src="display_image?id=${user.id}" width="50px"
								height="50px" alt="pic" /></td>
						<security:authorize
							access="hasRole('ROLE_ADMIN') && isAuthenticated()">
							<td><a href="user_edit?id=${user.id}"
								class="btn btn-primary">Edit</a>| <a
								href="user_delete?id=${user.id}" class="btn btn-danger">Delete</a>
							</td>
						</security:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>