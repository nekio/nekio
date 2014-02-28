<%-- 
    Document   : tickets
    Created on : 26-feb-2014, 17:18:04
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
        <title>Cat&aacute;logo de Tickets</title>
    </head>
    <body>
        <a href="regresarIndex">Regresar</a><br/>
        
        <h1>Tickets</h1>
        
        <table border="1">
            <thead>
                <th>ID Ticket</th>
                <th>Usuario</th>
                <th>Depto</th>
                <th>Topico Ayuda</th>
                <th>Personal Atencion</th>
                <th>Nivel Atencion</th>
                <th>Estado</th>
                <th>Causa Cierre Ticket</th>
                <th>Folio</th>
                <th>Descripcion</th>
                <th>Fecha Creaci&oacute;n</th>
                <th>Fecha Cierre</th>
                <th>Calificacion</th>
                <th>Resuelto</th>
            </thead>
            <tbody>
                <c:forEach var="vTicket" items="${ticket}">
                    <tr>
                        <td>${vTicket.idTicket}</td>
                        <td>${vTicket.idUsuario.usuario}</td>
                        <td>${vTicket.idDepto.descripcion}</td>
                        <td>${vTicket.idTopicoAyuda.descripcion}</td>
                        <td>${vTicket.idPersonalAtencion.usuario}</td>
                        <td>${vTicket.idNivelAtencion.descripcion}</td>
                        <td>${vTicket.idEstado.descripcion}</td>
                        <td>${vTicket.idCausaCierreTicket.descripcion}</td>
                        <td>${vTicket.folio}</td>
                        <td>${vTicket.descripcion}</td>
                        <td>
                            <fmt:formatDate value="${vTicket.fechaCreacion}"
                                pattern="dd/MM/yyyy" />
                        </td>
                        <td>
                            <fmt:formatDate value="${vTicket.fechaCierre}"
                                pattern="dd/MM/yyyy" />
                        </td>
                        <td>${vTicket.calificacion}</td>
                        <td>${vTicket.resuelto}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        
        <a href="nuevoTicket">Nuevo Ticket</a><br/>
        
        <h3>Consultas</h3>
        <ul>
            <li><a href="listarTicketsNoResueltos">Tickets no resueltos</a></li>
            <li><a href="listarTicketsPorFecha">Tickets por fecha de creaci&oacute;n</a></li>
        </ul>
        <br/>
        
        <h3>Reportes por Departamento</h3>
        <ul>
            <li><a href="verSolicitudesPorDepto">Solicitudes por periodo</a></li>
            <li><a href="verTiempoPromedioResolucion">Tiempo promedio de resoluci&oacute;n de Tickets</a></li>
        </ul>
    </body>
</html>
