<%--
  Created by IntelliJ IDEA.
  User: noah
  Date: 24/04/19
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil User</title>
    <%@include file="entete.jsp"%>
</head>
<body>
<a href="logout" class="col s12 waves-effect waves-light btn">Déconnexion</a>
<div class="row center">
    <div class="col s2 offset-s5">
        <div class="row">
            <a href="articleClient" class="col s12 waves-effect waves-light btn">Liste des produits</a>
        </div>
        <div class="row">
            <a href="panierClient" class="col s12 waves-effect waves-light btn">Mon panier</a>
        </div>
    </div>
</div>
</body>
</html>
