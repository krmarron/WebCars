<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit An Existing Lot</title>
</head>
<body>
	<form action = "editLotDetailsServlet" method = "post">
		<input type = "hidden" name = "id" value = "${lotToEdit.id}">
		Lot Name: <input type = "text" name = "lotName" value="${lotToEdit.lotName}"><br />
		
		Trip date: <input type = "text" name = "month" placeholder = "mm" size = "4" value ="${month}">
		<input type = "text" name = "day" placeholder = "dd" size = "4" value = "${date}">,
		<input type = "text" name = "year" placeholder = "yyyy" size = "4" value = "${year}">
		
		Buyer Name: <input type = "text" name = "buyerName" value = "${lotToEdit.buyer.buyerName}"><br />
		
		Available Cars:<br />
		
		<select name="allCarsToAdd" multiple size = "6">
			<c:forEach items="${requestScope.allCars}" var = "currentcar">
				<option value = "${currentcar.id}">${currentcar.make} | ${currentcar.model} | ${currentcar.year}</option>
			</c:forEach>
		</select>
		<br />
		<input type = "submit" value = "Edit Lot and Add Cars">
	</form>
	<a href = "index.html">Go add new cars instead.</a>
</body>
</html>