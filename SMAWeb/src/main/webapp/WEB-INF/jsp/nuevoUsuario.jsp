<%-- 
    Document   : nuevoUsuario
    Created on : 26-feb-2014, 10:27:56
    Author     : Ivan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuario</title>
    </head>
    <body>
        <h1>Nuevo Usuario</h1>
        <sf:form method="POST" id="forma" name="forma" 
                 commandName="usuario" action="agregarUsuario">

            <label><b>Nombre:</b></label>
            <sf:input id="nombre" path="nombre" /><br/>

            <label><b>Apellido Paterno:</b></label>
            <sf:input id="apellido_p" path="apellidoP" /><br/>

            <label><b>Apellido Materno:</b></label>
            <sf:input id="apellido_m" path="apellidoM" /><br/>

            <label><b>Contacto</b></label>
            <sf:input id="contacto" path="contacto" /><br/>

            <label><b>Usuario:</b></label>
            <sf:input id="usuario" path="usuario" /><br/>

            <label><b>Password:</b></label>
            <sf:input id="password" path="password" /><br/>
            
            <label><b>Activo:</b></label>
            <sf:checkbox id="activo" path="activo" /><br/>
            
            <label><b>Tipo Usuario:</b></label>
            <sf:select 
                items="${tipoUsuario}"
                itemValue="idTipoUsuario"
                itemLabel="descripcion"
                path="idTipoUsuario.idTipoUsuario"
            /><br/>
            
            <br/><button type="submit" name="agregarUsuario" id="agregarUsuario">Guardar</button><br/>
        
        </sf:form>
        
        <span style="font-size: smaller;">${fechaActualEnServidor}</span>
    </body>
</html>