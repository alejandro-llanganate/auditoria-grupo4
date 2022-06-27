<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 02-mar.-22
  Time: 1:15 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambiar Responsable</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700;800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cambioResponsable.css">
</head>
<body>
<div class="login">
    <header class="header">
        <h1 class="titulo">Sistema de Tickets</h1>
        <nav class="sub-header">
            <h2>Cambiar responsable del Ticket</h2>
            <form method="POST" action="ListarTicketsController">
                <input type="submit" value="Cancelar" class="boton">
            </form>
        </nav>
    </header>
    <main class="container">
        <section class="principal">
            <div class="principal_container">
                <form method="POST" action="CambiarResponsableController" class="datos_container">
                    <label>Disponibles</label>
                    <select name="selectDisponible" class="dato">
                        <c:forEach items="${disponibles}" var="disponible">
                        <option value="${disponible.idEmpleado}">${disponible.nombre} ${disponible.apellido}</option>
                        </c:forEach>
                    </select>

                    <label>Razón del cambio</label>
                    <textarea class="dato" name="razonCambio" maxlength="200" placeholder="Detallar la razón" required></textarea>
                    <input type="hidden" name="idTicket" value="${idTicket}">
                    <input type="submit" value="Cambiar" class="enviar">
                </form>

                <div class="historial_container">
                    <h3>Historial de responsables</h3>
                    <table class="tabla">
                        <tr>
                            <td class="tabla_titulo td">Nombre</td>
                            <td class="tabla_titulo td">Apellido</td>
                            <td class="tabla_titulo td">Razon del cambio</td>
                        </tr>
                        <c:forEach items="${responsables}" var="responsable">
                            <tr>
                                <td class="td">${responsable.antiguoResponsable.nombre}</td>
                                <td class="td">${responsable.antiguoResponsable.apellido}</td>
                                <td class="td">${responsable.razonCambio}</td>
                            </tr>
                        </c:forEach>
                    </table>


                </div>
            </div>
        </section>
    </main>
</div>
</body>
</html>
