
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Sursh
  Date: 12.02.2019
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table border="1">

    <tr>
        <th>Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <c:forEach var="meal" items="${requestScope.mealsTo}">
    <tr>

            <td>

                        ${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm')}

            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>

    </tr>
    </c:forEach>


</table>
</body>
</html>
