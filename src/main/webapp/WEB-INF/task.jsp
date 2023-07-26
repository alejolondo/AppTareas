<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${task.name}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

	<div class="container">
	 
	 <h1 class="text-primary">Task: ${task.name}</h1>
	<hr>
		<h4> Creator: ${task.creator.name} </h4>
		<h4> Assignee: ${task.assigned.name} </h4>
		<h4> Priority: ${task.priority.name} </h4>
		
		<c:if test="${task.creator.id == userSession.id }">
		
		<a href="/task/edit/${task.id}" class="btn btn-warning"> Edit</a>
		<form action="/task/delete/${task.id }" method="post">
					 			<input type="hidden" name="_method" value="Delete">
					 			<input type="submit" value="Borar" class="btn btn-danger">
		</form>
		</c:if>
		<a href="/tasks" class="btn btn-primary">Go back</a>
		<c:if test="${task.assigned.id == userSession.id }">
		<form action="/task/complete/${task.id }" method="post">
					 			<input type="hidden" name="_method" >
					 			<input type="submit" value="Completed" class="btn btn-success">
		</form>
		</c:if>
		
	</div>
	
</body>
</html>