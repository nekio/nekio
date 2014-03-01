<%-- 
    Document   : nuevoTicket
    Created on : 26-feb-2014, 17:25:51
    Author     : Ivan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Nuevo Ticket</title>
    </head>
    <body>
        <h1>Nuevo Ticket</h1>
        <sf:form method="POST" id="forma" name="forma" 
                 commandName="ticket" action="agregarTicket">
            
            <label><b>Usuario:</b></label>
            <sf:select 
                items="${usuario}"
                itemValue="idUsuario"
                itemLabel="usuario"
                path="idUsuario.idUsuario"
            /><br/>
            
            <label><b>Departamento:</b></label>
            <sf:select 
                items="${depto}"
                itemValue="idDepto"
                itemLabel="descripcion"
                path="idDepto.idDepto"
            /><br/>
            
            <label><b>T&oacute;pico Ayuda</b></label>
            <sf:select 
                items="${topicoAyuda}"
                itemValue="idTopicoAyuda"
                itemLabel="descripcion"
                path="idTopicoAyuda.idTopicoAyuda"
            /><br/>
            
            <%-- 
                Este campo no debe capturarse, pues la logica del negocio
                implica que al crear un nuevo ticket, no tiene personal asignado
            <label><b>Personal de Atenci&oacute;n</b></label>
            <sf:select 
                items="${personalAtencion}"
                itemValue="idUsuario"
                itemLabel="usuario"
                path="idUsuario.idUsuario"
            /><br/>
            --%>
            
            <label><b>Nivel de Atenci&oacute;n</b></label>
            <sf:select 
                items="${nivelAtencion}"
                itemValue="idNivelAtencion"
                itemLabel="descripcion"
                path="idNivelAtencion.idNivelAtencion"
            /><br/>
            
            <%--
                Este campo no debe capturarse, pues la logica del negocio
                implica que al crear un nuevo ticket, su estado es "nuevo"
            <label><b>Estado</b></label>
            <sf:select 
                items="${estado}"
                itemValue="idEstado"
                itemLabel="descripcion"
                path="idEstado.idEstado"
            /><br/>
            --%>
            
            <%--
                Este campo no debe capturarse, pues la logica del negocio
                implica que al crear un nuevo ticket, no puede ser cerrado al mismo tiempo
            <label><b>Causa de Cierre del Ticket:</b></label>
            <sf:select 
                items="${causaCierre}"
                itemValue="idCausaCierre"
                itemLabel="descripcion"
                path="idCausaCierreTicket.idCausaCierre"
            /><br/>
            --%>
            
            <label><b>Descripcion:</b></label>
            <sf:input id="descripcion" path="descripcion" /><br/>

            <br/><button type="submit" name="agregarTicket" id="agregarTicket">Guardar</button><br/>
        
        </sf:form>
        
        <span style="font-size: smaller;">${fechaActualEnServidor}</span>
        <li><a href="listarTickets">Regresar al Cat&aacute;logo de Tickets</a></li>
    </body>
</html>