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
        <a href="/projetWeb_war_exploded/" class="col s12 waves-effect waves-light btn">Retour</a>
        <form class="row offset-s3" action="" method="post">
            <div class="col s6 offset-s3">
                <label for="username">Username :</label><input   type="text" name="username" id="username" value="<%=username%>">
                <label for="pass">Pass :</label><input type="password" name="pass" id="pass">
                <button class="waves-effect waves-light btn" type="submit"> Envoyer</button>
            </div>
        </form>
    </body>
</html>
