<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@include file="entete.jsp"%>
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
<div class="back">
    <a href="/projetWeb_war_exploded/" class="button bBack">Retour</a>
</div>
<div class="form">
    <form class="form" action="" method="post">
        <div class="input"><label for="username" class="">Username :</label><input   type="text" name="username" id="username" value="<%=username%>"></div>
        <div class="input marginT10"><label for="pass" class="">Pass :</label><input type="password" name="pass" id="pass"></div>
        <div><button class="button marginT10" type="submit"> Envoyer</button></div>
    </form>
</div>
</body>
</html>
