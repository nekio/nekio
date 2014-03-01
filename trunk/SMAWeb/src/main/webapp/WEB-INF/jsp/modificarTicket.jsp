<%-- 
    Document   : modificarTicket
    Created on : 28-feb-2014, 15:24:46
    Author     : Ivan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Modificar Ticket</title>
    </head>
    <body>
        <h1>Modificar Ticket</h1>
        
        <%--
            Un usuario sin privilegios no puede alterar ninguno de estos datos,
            por eso se les muestran en esta tabla
        --%>
        <label><b>Generales del Ticket con folio: </b><br/>${ticket.folio}</label><br/>
        <br/>
        
            <table border="1">
            <thead>
                <th>ID Ticket</th>
                <th>Usuario</th>
                <th>Depto</th>
                <th>Topico Ayuda</th>
                <th>Personal Atencion</th>
                <th>Nivel Atencion</th>
                <th>Estado</th>
                <th>Fecha de Creaci&oacute;n</th>
            </thead>
            <tbody>
                <tr>
                    <td>${ticket.idTicket}</td>
                    <td>${ticket.idUsuario.usuario}</td>
                    <td>${ticket.idDepto.descripcion}</td>
                    <td>${ticket.idTopicoAyuda.descripcion}</td>
                    <td>${ticket.idPersonalAtencion.usuario}</td>
                    <td>${ticket.idNivelAtencion.descripcion}</td>
                    <td>${ticket.idEstado.descripcion}</td>
                    <td>
                        <fmt:formatDate value="${ticket.fechaCreacion}"
                            pattern="dd/MM/yyyy" />
                    </td>
                </tr>
            </tbody>
        </table>
        <br/>
        
        <sf:form method="POST" id="forma" name="forma" 
                 commandName="ticket" action="guardarModificacionTicket">
            
            <sf:input type="hidden" id="idTicket" path="idTicket" value="${ticket.idTicket}"/><br/>
            
            <label><b>Descripcion:</b></label>
            <sf:input id="descripcion" path="descripcion" value="${ticket.descripcion}"  size="120" maxlength="200"/><br/>
            
            <%--
                Solo un usuario con privilegios es quien puede definir
                la causa del cierre del ticket
                (aunque tanto un usuario con privilegios, como uno sin privilegios
                tienen la posibilidad de cerrar un ticket)
            
            <label><b>Causa de Cierre del Ticket:</b></label>
            <sf:select path="idCausaCierreTicket.idCausaCierre">
                <sf:option value="null" label="--- Sin cerrar ---"/>
                <sf:options items="${causaCierre}" itemValue="idCausaCierre" itemLabel="descripcion"/>
            </sf:select><br/>
            --%>
            
            <br/><label><b>Calificaci&oacute;n:</b></label>
            <sf:select path="calificacion">
                <sf:option value="null" label="--- Sin calificar ---"/>
                <sf:option value="1" label="Muy mala"/>
                <sf:option value="2" label="Mala"/>
                <sf:option value="3" label="Regular"/>
                <sf:option value="4" label="Buena"/>
                <sf:option value="5" label="Muy buena"/>
            </sf:select><br/>
            
            <label><b>NOTA: </b><i>Asignar una calificacion implica que fue resuelta su solicitud y ud. procede a cerrar el ticket</i></label><br/>

            <br/><button type="submit" name="guardarModificacionTicket" id="guardarModificacionTicket">Guardar Cambios</button><br/>
        </sf:form>
        
        <br/><a href="listarTickets">Regresar al Cat&aacute;logo de Tickets</a>
    </body>
</html>