<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Calories management util</title>
</head>
<body>

<form method="POST" action='meals' name="frmAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="mealId"
           value="${meal.id}" /> <br />
    Date : <input type="text" name="dateTime"
                  value="<javatime:format value="${meal.dateTime}" style="MS" />" /> <br />
    Description : <input type="text" name="description"
        value="${meal.description}" /> <br />
    Calories : <input type="text" name="calories"
                   value="${meal.calories}" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>