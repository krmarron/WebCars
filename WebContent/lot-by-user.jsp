<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Lot</title>
</head>
<body>
	<form method = "post" action = "lotnavigationServlet">
		<table>
			<c:forEach items="${requestScope.allLists}" var="currentlot">
				<tr>
					<td><input type="radio" name="id" value="${currentlot.id}"></td>
					<td><h2>${currentlot.lotName}</h2></td>
				</tr>
				<tr><td colspan="3">Trip Date: ${currentlot.tripDate}</td></tr>
				<tr><td colspan="3">Buyer: ${currentlot.buyer.buyerName}</td></tr>
				<c:forEach var = "lotVal" items="${currentlot.lotOfCars}">
					<tr><td></td><td colspan = "3">
						${lotVal.make}, ${lotval.model}, ${lotval.year}
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		<input type = "submit" value = "edit" name = "doThisToLot">
		<input type = "submit" value = "delete" name = "doThisToLot">
		<input type = "submit" value = "add" name = "doThisToLot">
	</form>
	<a href = "addCarsForLotServlet">Create a new Lot</a>
	<a href = "index.html">Insert a new car</a>
</body>
</html>