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
<div class="back">
    <a href="accueil" class="button bBack">Retour</a>
</div>
<div class="scroll">
    <div class="pseudo">
        <h2>Voici la liste des articles</h2>
    </div>
    <table class="striped">
        <thead>
            <tr>
                <th class="min">Produit</th>
                <th class="min">Prix</th>
                <th class="min">Stock</th>
                <th></th>
            </tr>
        <thead>
        <tbody>
        <%
            ArrayList<Article> article = (ArrayList<Article>) request.getAttribute("produits");
            for (Article u : article){
                if(u.getStock() > 0) {
        %>
        <tr>
            <form action="AjoutArticle" method="POST">
                <input id="id_article" name="id_article" type="hidden" value="<%=u.getId()%>">
                <td class="min"><%=u.getNom()%></td>
                <td class="min"><%=u.getPrix()%></td>
                <td class="min"><%=u.getStock()%></td>

                <td><input type="submit" name="action" value="Ajouter au panier"></td>
            </form>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <div class="block mt">
            <a href="panierClient" class="button bBack">Voir le panier</a>
    </div>
</div>
</body>
</html>
