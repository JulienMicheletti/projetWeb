<%@ page import="entite.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entite.Commande" %>
<%@ page import="javax.json.JsonReader" %>
<%@ page import="java.io.StringReader" %>
<%@ page import="javax.json.Json" %>
<%@ page import="javax.json.JsonObject" %><%--
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
<div class="back">
    <a href="accueil" class="button bBack">Retour</a>
</div>
<div class="scroll">
    <div class="pseudo">
        <h2>Voici votre panier</h2>
    </div>
    <table class="striped">
        <thead>
        <tr>
            <th class="min">Produit</th>
            <th class="min">Prix unitaire</th>
            <th class="min">Quantité</th>
            <th class="min">Prix</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            Cookie[] panier = request.getCookies();

            for (Cookie c : panier){
                if(!c.getName().equals("JSESSIONID")) {
                    JsonReader reader = Json.createReader(new StringReader(c.getValue()));

                    JsonObject json = reader.readObject();
        %>
        <tr>
            <form action="ModPanier" method="POST">
                <input id="id_commande" name="id_commande" type="hidden" value="<%=c.getName()%>">

                <td class="min"><%=json.getString("nom")%></td>
                <td class="min"><%=json.getJsonNumber("prixU").bigDecimalValue().floatValue()%></td>
                <td class="min"><%=json.getInt("quantite")%></td>
                <td class="min"><%=(float)(json.getInt("quantite"))*json.getJsonNumber("prixU").bigDecimalValue().floatValue()%></td>
                <td><input type="submit" name="action" value="Retirer"></td>
            </form>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <div class="block mt">
        <a href="articleClient" class="button bBack">Voir la liste des articles</a>
    </div>
</div>
</body>
</html>
