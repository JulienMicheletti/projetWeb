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
    <title>Title</title>
    <%@include file="entete.jsp"%>
</head>
<body>

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

</body>
</html>
