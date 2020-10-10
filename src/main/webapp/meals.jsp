<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Calories management</title>
</head>
<body>

<p><a href="meals?action=insert">Add Meal</a></p>
<table border="1" cellpadding="5">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
<c:forEach items="${meals}" var="meal">
    <c:set var="tdColor" value="${meal.excess ? 'color:red' : 'color:green'}"/>
    <tr style="${tdColor}">
        <td><javatime:format value="${meal.dateTime}" style="MS" /></td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=update&mealId=${meal.id}">Update</a></td>
        <td><a href="meals?action=delete&mealId=${meal.id}">Delete</a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
