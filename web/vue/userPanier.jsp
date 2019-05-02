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
<a href="accueil" class="col s12 waves-effect waves-light btn">Retour</a>
<div class="row">
    <div class="col s4 offset-s4">
        <h2 class="card-panel teal lighten-2 center-align">Voici votre panier</h2>
    </div>
    <table class="striped">
        <tr>
            <th>Produit</th>
            <th>Prix unitaire</th>
            <th>Quantité</th>
            <th>Prix</th>
            <th>Supprimer</th>
        </tr>

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

                <td><%=json.getString("nom")%></td>
                <td><%=json.getJsonNumber("prixU").bigDecimalValue().floatValue()%></td>
                <td><%=json.getInt("quantite")%></td>
                <td><%=(float)(json.getInt("quantite"))*json.getJsonNumber("prixU").bigDecimalValue().floatValue()%></td>
                <td><input type="submit" name="action" value="Supprimer"></td>
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
                <a href="articleClient" class="col s12 waves-effect waves-light btn">Voir la liste des articles</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
