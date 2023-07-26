<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

	<div class="container">
	
	<h1>Welcome ${userSession.name}</h1>
	<a href="/logout" class="btn btn-danger"> Logout</a>
	

			
			<h2>Tasks</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Task</th>
						<th>creator</th>
						<th>Assignee</th>
						<th>priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td> <a href="/task/${task.id}"> ${task.name}  </a> </td>
							<td> ${task.creator.name} </td>
							<td> ${task.assigned.name} </td>
							<td> ${task.priority.name} </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/tasks/new" class="btn btn-success"> Create Task</a>
		</div>
</body>
</html>