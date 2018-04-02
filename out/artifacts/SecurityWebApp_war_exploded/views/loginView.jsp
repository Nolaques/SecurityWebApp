<%--
  Created by IntelliJ IDEA.
  User: belyak
  Date: 23.03.2018
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Login Page</h3>

<p style="color:red;">${errorString}</p>

<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="hidden" name="redirectId" value="${param.redirectId}"/>
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" value="${user.userName}"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${user.password}"></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <a href="${pageContext.request.contextPath}/">Cancel</a>

            </td>
        </tr>

    </table>
</form>

<p style="color:blue;">Login with:</p>

    employee1/123 <br>
    manager1/123

</body>
</html>
