<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 16-feb.-22
  Time: 8:04 p. m.
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
    <title>Menu</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700;800&display=swap" rel="stylesheet">
    <link rel='stylesheet' href='https://cdn-uicons.flaticon.com/uicons-regular-rounded/css/uicons-regular-rounded.css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listaTickets.css">
</head>
<body>
<div class="login">
    <header class="header">
        <h1 class="titulo">Sistema de Tickets</h1>
        <nav class="sub-header">
            <h2>Bienvenido/a ${nombreEmpleado}</h2>
            <a href="LogoutEmpleadoController">Salir</a>
        </nav>
    </header>
    <main class="container">
        <section class="principal">
            <div class="encabezado">
                <div class="aux">
                    <h2>Mis tickets</h2>
                    <i class="fi fi-rr-ticket"></i>
                </div>
            </div>
            <table class="tabla">
                <tr>
                    <td class="tabla_titulo td">ID</td>
                    <td class="tabla_titulo td">Título</td>
                    <td class="tabla_titulo td">Fecha de Creación</td>
                    <td class="tabla_titulo td">Prioridad</td>
                    <td class="tabla_titulo td">Estado</td>
                    <td class="tabla_titulo td titulo_acciones">Acciones</td>
                </tr>
                <c:forEach items="${misTickets}" var="tickets">
                    <tr>
                        <td class="td">${tickets.idTicket}</td>
                        <td class="td">${tickets.titulo}</td>
                        <td class="td">${tickets.fechaCreacion}</td>
                        <td class="td c_prioridad">${tickets.prioridad}</td>
                        <td class="td c_estado">${tickets.estado}</td>
                        <td class="td acciones"><a href="GestionarTicketController?idTicket=${tickets.idTicket}" class="a_abrir">Aceptar</a> <a href="CambiarResponsableController?idTicket=${tickets.idTicket}">Cambiar Responsable</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </main>
</div>
<script>
    var colorPrioridad = document.querySelectorAll('.tabla .c_prioridad');
    var colorEstado = document.querySelectorAll('.tabla .c_estado');

    colorPrioridad.forEach(function(color) {
        if (color.innerHTML === 'Alta') {
            color.style.color = 'red';
        } else if (color.innerHTML === 'Media') {
            color.style.color = 'orange';
        } else if (color.innerHTML === 'Baja') {
            color.style.color = 'green';
        }
    });

    colorEstado.forEach(function(color) {
        if (color.innerHTML === 'Pendiente') {
            color.style.color = 'orange';
        } else if (color.innerHTML === 'Finalizado') {
            color.style.color = 'green';
        }
    });


</script>
</body>
</html>
