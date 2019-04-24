<%@ page import="java.util.ArrayList" %>
<%@ page import="entite.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: noah
  Date: 24/04/19
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Liste Utilisateurs</title>
    <%@include file="entete.jsp"%>
    <%!
        ArrayList<Utilisateur> users;
    %>
</head>
<body>
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
<div class="row center">
<table class="striped">
    <thead>
    <tr>
        <th>Pseudo</th>
        <th>Nom</th>
        <th>Prenom</th>
    </tr>
    </thead>
    <tbody>
<%
    users = (ArrayList<Utilisateur>)request.getAttribute("users");
    for (Utilisateur user : users){
        %>
    <tr>
        <td><%=user.getPseudo()%></td>
        <td><%=user.getNom()%></td>
        <td><%=user.getPrenom()%></td>
    </tr>
<%
    }
%>
    </tbody>
</table>
</div>
</body>
</html>
