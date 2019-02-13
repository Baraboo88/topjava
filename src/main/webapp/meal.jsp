<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Sursh
  Date: 13.02.2019
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Meal</title>
</head>
<body>

<form method="POST" action='controller' name="frmAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="mealId" value="${meal.id}"/> <br/>
    Time : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/> <br/>
    Description : <input type="text" name="description" value="${meal.description}"/> <br/>
    Calories : <input type="text" name="calories" value="${meal.calories}"/> <br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>

