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
    <div class="col s4 offset-s4">
        <h2 class="card-panel teal lighten-2">Voici la liste des utilisateurs</h2>
    </div>
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
        String id_user = ""+user.getId();
        %>
    <tr>
        <form class="formMod" action="ModUser" method="POST">
            <input id="id_user" name="id_user" type="hidden" value="<%=id_user%>">
                <td><input id="user_pseudo" name="user_pseudo" value="<%=user.getPseudo()%>" required></td>
            <td><input id="user_nom" name="user_nom" value="<%=user.getNom()%>" required></td>
            <td><input id="user_pre" name="user_pre" value="<%=user.getPrenom()%>" required></td>
            <td><select name="user_role">
                <%
                    if (user.getRole() == 1){
                %>
                <option value="user">User</option>
                <option value="admin">Admin</option>
                <%
                } else {
                %>
                <option value="admin">Admin</option>
                <option value="user">User</option>
                <%
                    }
                %>
            </select>
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
