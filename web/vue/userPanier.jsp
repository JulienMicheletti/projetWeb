<%@ page import="entite.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entite.Commande" %><%--
  Created by IntelliJ IDEA.
  User: Julien
  Date: 24/04/2019
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateur - Panier</title>
    <%@include file="entete.jsp"%>
</head>
<body>

<div class="col s4 offset-s4">
    <h2 class="card-panel teal lighten-2">Voici votre panier</h2>

<table border="1">
    <tr>
        <th>Produit</th>
        <th>Quantité</th>
    </tr>

    <%
        ArrayList<Commande> commande = (ArrayList<Commande>) request.getAttribute("commande");

        for (Commande c : commande){
    %>
    <tr>
        <td><%=c.getArticle().getNom()%></td>
        <td><%=c.getQuantite()%></td>
    </tr>
    <%
        }
    %>
</table>
<div class="row center">
    <div class="col s2 offset-s5">
        <div class="row">
            <a href="articleClient" class="col s12 waves-effect waves-light btn">Voir la liste des articles</a>
        </div>
    </div>
</div>
</div>
</body>
</html>
