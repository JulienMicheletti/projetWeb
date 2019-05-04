<%@ page import="java.util.ArrayList" %>
<%@ page import="entite.Utilisateur" %>
<%@ page import="entite.Article" %><%--
  Created by IntelliJ IDEA.
  User: noah
  Date: 24/04/19
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Liste Articles</title>
    <%@include file="entete.jsp"%>
    <%!
        ArrayList<Article> articles;
    %>
</head>
<body>
<div class="back">
    <a href="accueil" class="button bBack">Retour</a>
</div>
<%
    String username = request.getParameter("username");
    if(username == null)
        username = "";

    ArrayList<String> erreurs = new ArrayList<>();
    if(request.getAttribute("erreurs") != null)
        erreurs = (ArrayList<String>)request.getAttribute("erreurs");

    for(String e : erreurs) {%>
<div class="red"><%=e%></div>
<%
    }
%>



<div class="scroll">
    <div class="pseudo">
        <h2>Ajouter un article</h2>
    </div>
    <table class="striped">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Stock</th>
            <th>Prix</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <form class="formAdd" action="addArticle" method="POST">
                <td><input name="article_nom" placeholder="Nom" required></td>
                <td><input type="number" min="1" name="article_qte" placeholder="Stock" required></td>
                <td><input type="number" name="article_prix" placeholder="Prix" required></td>
                <td><input type="submit" name="mod" value="Ajouter"></td>
            </form>
        </tr>
        </tbody>
    </table>
    <div class="pseudo">
        <h2>Voici la liste des articles</h2>
    </div>
<table>
    <thead>
    <tr>
        <th>Nom</th>
        <th>Stock</th>
        <th>Prix</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
<%
    articles = (ArrayList<Article>)request.getAttribute("articles");
    if (articles != null)
    for (Article article : articles){
        String id_article = ""+article.getId();
        %>
    <tr>
        <form class="formMod" action="ModArticle" method="POST">
            <input name="id_article" type="hidden" value="<%=id_article%>">
            <td><input name="article_nom" value="<%=article.getNom()%>" required></td>
            <td><input type="number" min="1" name="article_qte" value="<%=article.getStock()%>" required></td>
            <td><input type="number" name="article_prix" value="<%=article.getPrix()%>" required></td>
            </td>
                <td><input type="submit" name="mod" value="Modifier"></td>
                <td><input type="submit" name="mod" value="Supprimer"></td>
        </form>
    </tr>
<%
    }
%>
    </tbody>
</table>
</div>
</body>
</html>
