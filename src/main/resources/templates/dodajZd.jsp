<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Nowe zdarzenie</title>
        <link href="<c:url value="static/css/materialize.css" />" rel="stylesheet"
              media="screen, projection">
        <link href="<c:url value="static/css/style.css" />" rel="stylesheet">
    </head>
    <body>
        <h1 class="center">Dodawanie zdarzenia w systemie</h1>
        <div class="row">
             <a class="btn blue-grey col s12 m3" href="<c:url value="dodajZdarzenie.htm" />"><span>Dodaj Zdarzenie</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlZdarzenie.htm"/>"><span>Wyświetl Zdarzenia</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlSystemy.htm"/>"><span>Wyświetl Systemy</span></a>
             <a class="btn btn-success col s12 m2" href="<c:url value="rejestracja.htm"/>"><span>Rejestracja</span></a>
             <a class="btn red col s12 m1" href="<c:url value="powrot.htm"/>"><span>Powrót</span></a>             
        </div></br></br>
        <!--commandName - obiekt przyslany z kontrolera ktorego pola zostana wypelnione przez formularz-->
        <!--action - nazwa kontrolera wywolywanego po wcisnieciu przycisku-->
        <!--errors - domyslne kryteria walidacji pola na podstawie typu(String)-->
        <!--path - nazwa pola w obiekcie zdarzenie-->
        <div class="row">
            <div class="center">
            <form:form class="col m8 s12 offset-m2" commandName="zdarzenie" method="POST" action="dodajZdarzenie.htm">
                <div class="row">
                    <p>Data:</p><form:input type="date" path="dataZdarzenia" required="true" />
                </div>
                <div class="row">
                    <p>System:<p><form:select class="browser-default" items="${systemy}" path="nazwaSystemu" itemValue="name" itemLabel="name" />
                 </div>
                 <div class="input-field col s12">
                     <p>Opis:</p><form:textarea class="materialize-textarea" path="opis" required="true"/>
                 </div>
                 <div class="row">
                    <form:button class="btn">Dodaj Zdarzenie</form:button>
                 </div>
            </form:form>
            </div>
        </div>
    </body>
</html>
