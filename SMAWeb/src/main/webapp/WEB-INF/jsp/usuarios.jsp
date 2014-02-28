<%-- 
    Document   : usuarios
    Created on : 26-feb-2014, 11:51:13
    Author     : Ivan 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cat&aacute;logo de Usuarios</title>
    </head>
    <body>
        <a href="regresarIndex">Regresar</a><br/>
        
        <h1>Usuarios</h1>
        
        <table border="1">
            <thead>
                <th>Id Usuario</th>
                <th>Tipo Usuario</th>
                <th>Nombre</th>
                <th>Apellido P.</th>
                <th>Apellido M.</th>
                <th>Contacto</th>
                <th>Usuario</th>
                <th>Activo</th>
            </thead>
            <tbody>
                <c:forEach var="vUsuario" items="${usuario}">
                    <tr>
                        <td>${vUsuario.idUsuario}</td>
                        <td>${vUsuario.idTipoUsuario.descripcion}</td>
                        <td>${vUsuario.nombre}</td>
                        <td>${vUsuario.apellidoP}</td>
                        <td>${vUsuario.apellidoM}</td>
                        <td>${vUsuario.contacto}</td>
                        <td>${vUsuario.usuario}</td>
                        <td>${vUsuario.activo}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />
        
        <a href="nuevoUsuario">Nuevo Usuario</a><br />
        <a href="editarEstadoUsuario">Desactivar un Usuario</a><br />
    </body>
</html>
