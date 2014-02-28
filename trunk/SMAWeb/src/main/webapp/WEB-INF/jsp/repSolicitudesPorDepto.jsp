<%-- 
    Document   : repSolicitudesPorDepto
    Created on : 27-feb-2014, 12:59:09
    Author     : Ivan 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte de Solicitudes de Tickets por periodo</title>
    </head>
    <body>
        <h1>Reporte de Solicitudes de Tickets por periodo</h1>
        
        <table border="1">
            <thead>
                <th>Numero de Tickets</th>
                <th>Descripcion</th>
            </thead>
            <tbody>
                <c:forEach var="vResultado" items="${resultado}">
                    <tr>
                        <td>${vResultado.numTickets}</td>
                        <td>${vResultado.mesDepto}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        
        <a href="listarTickets">Regresar al cat&aacute;logo de Tickets</a>
    </body>
</html>
