<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Calories management</title>
</head>
<body>

<jsp:useBean id="meals" scope="request" type="java.util.List"/>
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
        <td><a href="meals">Update</a></td>
        <td><a href="meals">Delete</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
