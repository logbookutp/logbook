<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" /> <!--Dostosowanie wymiarow do urzadzenia-->
        <title>Logowanie</title>
        <link href="<c:url value="static/css/materialize.css" />" rel="stylesheet"
              media="screen, projection">
        <link href="<c:url value="static/css/style.css" />" rel="stylesheet" >
    </head>
    <body>       
        <h1 class="center">Logowanie</h1>
            <form class="col m8 s12 offset-m2 text" method="POST" action="<c:url value='j_spring_security_check' />">               
                <div class="row center">
                    <div class="input-field col s6 offset-s3">
                        <input type="text" name="email" placeholder="login"/>   
                    </div>
                </div>
                <div class="row center">
                    <div class="input-field col s6 offset-s3">
                        <input type="password" name="password" placeholder="hasło"/>   
                    </div>
                </div>
                <div class="row s12 center">
                    <button class="btn green">Zaloguj</button>
                </div>
            </form>
        </div>
    </body>
</html>
