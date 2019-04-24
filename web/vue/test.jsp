<%--
  Created by IntelliJ IDEA.
  User: Asus ROH
  Date: 23/04/2019
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%= request.getSession().getAttribute("id")%>
</body>
</html>
