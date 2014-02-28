<%-- 
    Document   : ticketsNoResueltos
    Created on : 27-feb-2014, 11:42:16
    Author     : Ivan 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tickets no resueltos</title>
    </head>
    <body>
        <h1>Tickets no resueltos</h1>
        
        <table border="1">
            <thead>
                <th>Id Ticket</th>
                <th>Folio</th>
                <th>Descripcion</th>
                <th>Fecha Creaci&oacute;n</th>
            </thead>
            <tbody>
                <c:forEach var="vTicket" items="${ticket}">
                    <tr>
                        <td>${vTicket.idTicket}</td>
                        <td>${vTicket.folio}</td>
                        <td>${vTicket.descripcion}</td>
                        <td>
                            <fmt:formatDate value="${vTicket.fechaCreacion}"
                                pattern="dd/MM/yyyy" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        
        <a href="listarTickets">Regresar al cat&aacute;logo de Tickets</a>
    </body>
</html>
