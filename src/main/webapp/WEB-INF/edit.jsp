<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
      
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
   <%@ page isErrorPage="true" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

<div class="container">
		
		<h1>Edit ${task.name} </h1>
		<form:form action="/tasks/edit/" method="post" modelAttribute="task">
			  <input type="hidden" name="_method" value="put" > 
		 
			<form:hidden path="id" value="${task.id}" />
		 
		 <div class="form-group">
		 	<form:label path="name">Títle: </form:label>
		 	<form:input path="name" class="form-control"/>
		 	<form:errors path="name"/>
		 </div>
		 
		 <div class="form-group">
		 	<form:label path="assigned">Assignee:</form:label>
			<form:select path="assigned" class="form-select">
				<c:forEach var="assigned" items="${users}">
				<option value=${assigned.id}>${assigned.name}</option> 
				</c:forEach>
			</form:select>
			<form:errors path="assigned" class="text-danger" />
		 </div>
		 
		 <div class="form-group">
		 	<form:label path="priority">Priority:</form:label>
			<form:select path="priority" class="form-select">
				<c:forEach var="priority" items="${priorities}">
				<option value=${priority.id}>${priority.name}</option> 
				</c:forEach>
			</form:select>
			<form:errors path="assigned" class="text-danger" />
		 </div>
		 
		 <form:hidden path="creator" value="${userSession.id}" />
		 
	
		 
		 <input type="submit"  value="Guardar "class="btn btn-success">
		 
		</form:form>
	</div>

</body>
</html>