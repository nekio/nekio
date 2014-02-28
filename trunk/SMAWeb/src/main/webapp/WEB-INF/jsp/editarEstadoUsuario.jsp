<%-- 
    Document   : editarEstadoUsuario
    Created on : 28-feb-2014, 9:52:40
    Author     : Ivan 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar estado de un Usuario</title>
    </head>
    <body>
        <h1>Editar estado de un Usuario</h1>
        <sf:form method="POST" id="forma" name="forma" 
                 commandName="usuario" action="desactivarUsuario">
                        
            <label><b>Usuario:</b></label>
            <sf:select 
                items="${usuarios}"
                itemValue="idUsuario"
                itemLabel="usuario"
                path="idUsuario"
            /><br/>
            
            <br/>
            <button type="submit" name="desactivarUsuario" id="desactivarUsuario">Desactivar</button>
        
        </sf:form>
        <br/><a href="listarUsuarios">Regresar al Cat&aacute;logo de Usuarios</a>
    </body>
</html>