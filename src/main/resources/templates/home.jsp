<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>           
        <link href="<c:url value="static/css/materialize.css" />" rel="stylesheet"
              media="screen, projection">
        <link href="<c:url value="static/css/style.css" />" rel="stylesheet" />             
    </head>
    <body>
        <h1 class="center">STRONA GŁÓWNA</h1>
        <div class="row">
             <a class="btn btn-success col s12 m3" href="<c:url value="dodajZdarzenie.htm" />"><span>Dodaj Zdarzenie</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlZdarzenie.htm"/>"><span>Wyświetl Zdarzenia</span></a>
             <a class="btn btn-success col s12 m3" href="<c:url value="wyswietlSystemy.htm"/>"><span>Wyświetl Systemy</span></a>
             <a class="btn btn-success col s12 m2" href="<c:url value="rejestracja.htm"/>"><span>Rejestracja</span></a>
             <a class="btn red col s12 m1" href="<c:url value = 'j_spring_security_logout' />"><span>Wyloguj</span></a>
        </div><!--KONIEC NAWIGACJI-->
     
        <div class="container">
            <table class="striped highlight centered white-text">
                <th class="center">Id</th>              
                <th class="center">Nazwa</th>
                <th class="center">Opis</th>
                <th class="center">Admin</th>
                <!--$zdarzenia - kolekcja przyslana przez kontroler /wyswietlZdarzenie-->
                <c:forEach items="${systemy}" var="s">
                <tr>
                    <td>${s.id}</td>    
                    <td>${s.name}</td> 
                    <td>${s.description}</td> 
                    <td>${s.administratorName}</td> 
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
