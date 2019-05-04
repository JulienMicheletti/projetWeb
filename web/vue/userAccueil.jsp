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
<div class="back">
    <a href="logout" class="button bBack">Déconnexion</a>
</div>
<div class="">
    <div class="pseudo">
        <h2 class="">Bienvenue ${pseudo}</h2>
    </div>
    <div class="block">
        <a href="articleClient" class="button">Liste des produits</a>
        <a href="panierClient" class="button marginT10">Mon panier</a>
    </div>
</div>
</body>
</html>
