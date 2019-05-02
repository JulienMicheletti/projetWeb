<%@ page import="entite.Article" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Julien
  Date: 24/04/2019
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateurs - Liste de produits</title>
    <%@include file="entete.jsp"%>
</head>
<body>
<a href="accueil" class="col s12 waves-effect waves-light btn">Retour</a>
<div class="row">
    <div class="col s4 offset-s4">
        <h2 class="card-panel teal lighten-2 center-align">Voici la liste des articles</h2>
    </div>
    <table class="striped">
        <tr>
            <th>Produit</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Ajouter au panier</th>
        </tr>

        <%
            ArrayList<Article> article = (ArrayList<Article>) request.getAttribute("produits");
            for (Article u : article){
                if(u.getStock() > 0) {
        %>
        <tr>
            <form action="AjoutArticle" method="POST">
                <input id="id_article" name="id_article" type="hidden" value="<%=u.getId()%>">
                <td><%=u.getNom()%></td>
                <td><%=u.getPrix()%></td>
                <td><%=u.getStock()%></td>

                <td><input type="submit" name="action" value="Ajouter"></td>
            </form>
        </tr>
        <%
                }
            }
        %>
    </table>

    <div class="row center">
        <div class="col s2 offset-s5">
            <div class="row">
                <a href="panierClient" class="col s12 waves-effect waves-light btn">Voir le panier</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
