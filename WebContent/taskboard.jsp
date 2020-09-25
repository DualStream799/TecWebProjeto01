<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Task Board</title>
	</head>
	
	<body>
		<h1>Your tasks for today</h1>
		<h3>Logged as "${user_email}"</h3>
	
		<form action="addTask" method="post">
			<input type="text" id="add_description" name="description" placeholder="Write your task here..."/>
			<input type="hidden" name="user_email" value="${user_email}"/>
			<input type="submit" value="Add"/>
		</form>
		
	<table>
		<tr>
			<th>Tasks</th>
			<th>Tag</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="task" items="${taskslist}">
			<tr>
				<td>${task.description}</td>
				<td>${task.tag}</td>
			  	<td><form action="addTask" method="post" onsubmit="getDescriptionText(${task.id});">
						<input type="submit" id="btn-edit" name="edit" value="Edit" >
						<input type="submit" name="remove" value="Remove">
						<input type="hidden" name="task_id" value="${task.id}"/>
						<input type="hidden" name="user_email" value="${user_email}"/>
						<input type="hidden" id="edit_description ${task.id}" name="description"/>
					</form>
				</td>
			</tr>
			
		</c:forEach>
	</table>
	

	<script>
	
	function getDescriptionText(task_id) {
		var inputAdd = document.getElementById("add_description");
		var inputEdit = document.getElementById("edit_description " + task_id);
		inputEdit.value = inputAdd.value;
	}
	</script>
		
	</body>
</html>