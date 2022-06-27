<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 02-mar.-22
  Time: 8:41 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información del Ticket</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700;800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/infoMiTicket.css">
</head>
<body>
<div class="login">
    <header class="header">
        <h1 class="titulo">Sistema de Tickets</h1>
        <nav class="sub-header">
            <h2>Información de mi ticket</h2>
            <form method="POST" action="GestionSolicitudController">
                <input type="submit" value="Regresar" class="boton">
            </form>
        </nav>
    </header>
    <main class="container">
        <section class="principal">
            <div class="principal_container">
                <div class="datos_container">
                    <label>Título</label>
                    <input type="text" value="${miTicket.titulo}" class="dato" readonly>
                    <label>Fecha de creación</label>
                    <input type="text" class="dato" readonly value="${miTicket.fechaCreacion}">
                    <label>Mi Descripción</label>
                    <textarea class="dato" readonly>${miTicket.descripcion}</textarea>
                </div>

                <div class="emisor_container">
                    <label class="info">Solución</label>
                    <textarea class="dato" maxlength="300" placeholder="Todavía no hay una solución para este ticket" readonly>${miTicket.solucion}</textarea>
                    <label for="" class="info">Tiempo de resolución</label>
                    <input type="text" value="${tiempoResolucion}" class="dato" readonly placeholder="No finalizado">
                </div>
            </div>
        </section>
    </main>
</div>
</body>
</html>
