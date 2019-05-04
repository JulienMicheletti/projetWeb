<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Asus ROH
  Date: 23/04/2019
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <%@include file="entete.jsp"%>
</head>
<body>
<%
    String[] info = new String[3];
    info[0] = request.getParameter("nom");
    info[1] = request.getParameter("prenom");
    info[2] = request.getParameter("username");

    for(int i = 0; i < 3; i++) {
        if(info[i] == null)
            info[i] = "";
    }

    ArrayList<String> erreurs = new ArrayList<>();
    if(request.getAttribute("erreurs") != null)
        erreurs = (ArrayList<String>)request.getAttribute("erreurs");

    for(String e : erreurs) {%>
<div class="red"><%=e%></div>
<%
    }
%>
<div class="back">
    <a href="/projetWeb_war_exploded/" class="button bBack">Retour</a>
</div>
<div class="">
    <form class="form" action="" method="post">
        <div class="input marginT10"><label for="nom">Nom :</label><input type="text" name="nom" id="nom" value="<%=info[0]%>"></div>
        <div class="input marginT10"><label for="prenom">Prénom :</label><input type="text" name="prenom" id="prenom" value="<%=info[1]%>"></div>
        <div class="input marginT10"><label for="username">Username :</label><input   type="text" name="username" id="username" value="<%=info[2]%>"></div>
        <div class="input marginT10"><label for="pass">Pass :</label><input type="password" name="pass" id="pass"></div>
        <div><button class="button marginT10" type="submit"> Envoyer</button></div>
    </form>
</div>
</body>
</html>
