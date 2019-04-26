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
    <title>Title</title>
    <%@include file="entete.jsp"%>
</head>
<body>

<table border="1">
    <tr>
        <th>Produit</th>
        <th>Prix</th>
        <th>Quantité</th>
    </tr>

    <%
        ArrayList<Article> article = (ArrayList<Article>) request.getAttribute("produits");
        for (Article u : article){
    %>
    <tr>
        <td><%=u.getNom()%></td>
        <td><%=u.getPrix()%></td>
        <td><%=u.getStock()%></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
