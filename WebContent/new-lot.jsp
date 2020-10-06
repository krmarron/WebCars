<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new lot</title>
</head>
<body>
	<form action = "createNewLotServlet" method = "post">
		Lot Name: <input type = "text" name = "lotName"><br />
		Trip date: <input type = "text" name = "month" placeholder = "mm" size = "4">
		<input type = "text" name = "day" placeholder = "dd" size = "4">,
		<input type = "text" name = "year" placeholder = "yyyy" size = "4">
		Buyer Name <input type = "text" name = "buyerName"><br />
		
		Available Cars:<br />
		<select name="allCarsToAdd" multiple size = "6">
			<c:forEach items="${requestScope.allCars}" var = "currentcar">
				<option value = "${currentcar.id}">${currentcar.make} | ${currentcar.model} | ${currentcar.year}</option>
			</c:forEach>
		</select>
		<br />
		<input type = "submit" value = "Create Lot and Add Cars">
	</form>
	<a href = "index.html">Go add new cars instead.</a>
</body>
</html>