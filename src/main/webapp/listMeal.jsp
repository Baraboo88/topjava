<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sursh
  Date: 13.02.2019
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<head>
    <title>Show all meals</title>
</head>
<body>

<table border="=1">
    <tr>
        <th>Meal ID</th>
        <th>Time</th>
        <th>Description</th>
        <th>Calories</th>



    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr>
            <td>${meal.value.id}</td>
            <td>
                ${f:formatLocalDateTime(meal.value.dateTime)}
            </td>
            <td>${meal.value.description}</td>
            <td>${meal.value.calories}</td>
            <td><a href="controller?action=edit&mealId=${meal.value.id}">Update</a></td>
            <td><a href="controller?action=delete&mealId=${meal.value.id}">Delete</a></td>
            <td></td>
        </tr>
    </c:forEach>

</table>


<p><a href="controller?action=insert">Add Meal</a></p>


</body>
</html>
