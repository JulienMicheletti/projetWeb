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
<div class="back">
    <a href="logout" class="button bBack">Déconnexion</a>
</div>
<div class="">
    <div class="pseudo">
        <h2 class="">Bienvenue ${pseudo}</h2>
    </div>
    <div class="block">
        <a href="ListeUsers" class="button">Liste utilisateurs</a>
        <a href="adminArticles" class="button marginT10">Liste produits</a>
    </div>
</div>
</body>
</html>
