<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script
                src="https://code.jquery.com/jquery-3.3.1.js"
                integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
                crossorigin="anonymous">
        </script>
    </head>
    <body>
        <form class="row offset-s3" action="" method="post">
            <div class="col s6 offset-s3">
                <label for="username">Username :</label><input   type="text" name="username" id="username">
                <label for="pass">Pass :</label><input type="password" name="pass" id="pass">
                <button class="waves-effect waves-light btn" type="submit"> Envoyer</button>
            </div>
        </form>
    </body>
</html>
