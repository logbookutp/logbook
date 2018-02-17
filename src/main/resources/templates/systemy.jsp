<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" /> 
        <link href="<c:url value="static/css/materialize.css" />" rel="stylesheet"
              media="screen, projection">
        <link href="<c:url value="static/css/style.css" />" rel="stylesheet"/>
        <title>Systemy</title>
    </head>
    <body>
        <h1 class="center">Spis dostępnych systemów</h1>
        <div class="row">
             <a class="btn btn-success col s12 m3" href="<c:url value="dodajZdarzenie.htm" />"><span>Dodaj Zdarzenie</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlZdarzenie.htm"/>"><span>Wyświetl Zdarzenia</span></a>
             <a class="btn blue-grey col s12 m3" href="<c:url value="wyswietlSystemy.htm"/>"><span>Wyświetl Systemy</span></a>
             <a class="btn btn-success col s12 m2" href="<c:url value="rejestracja.htm"/>"><span>Rejestracja</span></a>
             <a class="btn red col s12 m1" href="<c:url value="powrot.htm"/>"><span>Powrót</span></a>             
        </div><br/><br/>
        
        <table class="striped highlight centered white-text">
            <th class="center">Id</th>              
            <th class="center">Nazwa</th>
            <th class="center">Opis</th>
            <th class="center">Id Administratora</th>
            <th class="center">Nazwa Administratora</th>
            <!--$zdarzenia - kolekcja przyslana przez kontroler /wyswietlZdarzenie-->
            <c:forEach items="${system}" var="s">
            <tr>
                <td>${s.id}</td>    
                <td>${s.name}</td> 
                <td>${s.description}</td> 
                <td>${s.administratorId}</td> 
                <td>${s.administratorName}</td> 
            </tr>
            </c:forEach>
        </table>
    
    </body>
</html>
