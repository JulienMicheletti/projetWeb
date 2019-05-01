<%@ page import="javax.json.JsonObject" %>
<%@ page import="javax.json.JsonObjectBuilder" %>
<%@ page import="javax.json.Json" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.Writer" %><%--
  Created by IntelliJ IDEA.
  User: noah
  Date: 24/04/19
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil Admin</title>
    <%@include file="entete.jsp"%>
</head>
<body>
<a href="logout" class="col s12 waves-effect waves-light btn">Déconnexion</a>
<div class="row center">
    <div class="col s4 offset-s4">
        <h2 class="card-panel teal lighten-2">Bienvenue ${pseudo}</h2>
    </div>
    <div class="col s2 offset-s5">
        <div class="row">
            <a href="ListeUsers" class="col s12 waves-effect waves-light btn">Liste utilisateurs</a>
        </div>
        <div class="row">
            <a href="" class="col s12 waves-effect waves-light btn">Liste produits</a>
        </div>
    </div>
    <%
        Cookie[] list = request.getCookies();
        for (Cookie c : list) {
            if(!c.getName().equals("JSESSIONID")) {
    %>
    <p><%=c.getName()%>: <%=c.getValue()%></p>
    <%
            }
        }
    %>
</div>
</body>
</html>
