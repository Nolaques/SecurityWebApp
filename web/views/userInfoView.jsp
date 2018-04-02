<%--
  Created by IntelliJ IDEA.
  User: belyak
  Date: 23.03.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.userName}</h3>

        User Name: <b>${loginedUser.userName}</b>
<br/>
        Gender: ${loginedUser.gender} <br/>

</body>
</html>
