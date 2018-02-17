<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" /> <!--Dostosowanie wymiarow do urzadzenia-->
        <title>Rejestracja</title>   
        <link href="<c:url value="static/css/materialize.css" />" rel="stylesheet"
              media="screen, projection">
        <link href="<c:url value="static/css/style.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 class="center">Rejestracja</h1>
        <div class="row">
             <a class="btn btn-success col s12 m3" href="<c:url value="dodajZdarzenie.htm" />"><span>Dodaj Zdarzenie</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlZdarzenie.htm"/>"><span>Wyświetl Zdarzenia</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlSystemy.htm"/>"><span>Wyświetl Systemy</span></a>
             <a class="btn blue-grey col s12 m2" href="<c:url value="rejestracja.htm"/>"><span>Rejestracja</span></a>
             <a class="btn red col s12 m1" href="<c:url value="powrot.htm"/>"><span>Powrót</span></a>             
        </div></br></br>
            <form:form class="col m8 s12 offset-m2" commandName="uzytkownik" method="POST" action="rejestracja.htm">
                <div class="row center">
                    <div class="input-field col s6 offset-s3">
                        <form:input path="name" placeholder="login" required="true" />
                    </div>
                </div>
                <div class="row center">
                    <div class="input-field col s6 offset-s3">
                        <form:password path="password" placeholder="hasło" required="true" ></form:password>
                    </div>
                </div>
                <div class="row s12 center">
                    <form:button class="btn green">Dodaj użytkownika</form:button>
                </div>
            </form:form>
    </body>
</html>
