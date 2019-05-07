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
<div class="button red"><%=e%></div>
<%
    }
%>
<div class="back">
    <a href="accueil" class="button bBack">Retour</a>
</div>
<div class="scroll">
    <div class="pseudo">
        <h2>Ajouter un utilisateur</h2>
    </div>
    <table>
        <thead>
        <tr>
            <th>Pseudo</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Mot de passe</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <form class="formAdd" action="addUser" method="POST">
                <td><input name="user_pseudo" placeholder="Pseudo" required></td>
                <td><input name="user_nom" placeholder="Nom" required></td>
                <td><input name="user_pre" placeholder="Prénom" required></td>
                <td><input name="user_pass" type="password" placeholder="Mot de passe" required></td>
                <td><label>
                    <input type="checkbox" name="user_role"/>
                    <span class="mr">Admin</span>
                </label>
                </td>
                <td><input type="submit" name="mod" value="Ajouter"></td>
            </form>
        </tr>
        </tbody>
    </table>
    <div class="pseudo">
        <h2>Voici la liste des utilisateurs</h2>
    </div>
<table class="">
    <thead>
        <tr>
            <th>Pseudo</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Role</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
<%
    users = (ArrayList<Utilisateur>)request.getAttribute("users");
    if (users != null)
    for (Utilisateur user : users){
        String id_user = ""+user.getId();
        %>
    <tr>
        <form class="formMod" action="ModUser" method="POST">
            <input name="id_user" type="hidden" value="<%=id_user%>">
                <td><input name="user_pseudo" value="<%=user.getPseudo()%>" required></td>
            <td><input name="user_nom" value="<%=user.getNom()%>" required></td>
            <td><input name="user_pre" value="<%=user.getPrenom()%>" required></td>
            <td><select class="browser-default" name="user_role">
                <%
                    if (user.getRole() == 1){
                %>
                <option value="user" selected>User</option>
                <option value="admin">Admin </option>
                <%
                } else {
                %>
                <option value="admin" selected>Admin</option>
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
