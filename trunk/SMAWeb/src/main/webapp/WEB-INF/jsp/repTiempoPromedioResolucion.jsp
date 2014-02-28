<%-- 
    Document   : repTiempoPromedioResolucion
    Created on : 27-feb-2014, 18:23:00
    Author     : Ivan 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tiempo Promedio de Resolucion de Tickets por Depto.</title>
    </head>
    <body>
        <h1>Tiempo Promedio de Resolucion de Tickets por Depto.</h1>
        
        <table border="1">
            <thead>
                <th>Dias Promedio</th>
                <th>Departamento</th>
            </thead>
            <tbody>
                <c:forEach var="vResultado" items="${resultado}">
                    <tr>
                        <td>${vResultado.diasPromedio}</td>
                        <td>${vResultado.descripcion}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        
        <a href="listarTickets">Regresar al cat&aacute;logo de Tickets</a>
    </body>
</html>
