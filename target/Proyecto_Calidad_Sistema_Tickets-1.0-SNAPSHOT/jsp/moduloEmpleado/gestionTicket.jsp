<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 01-mar.-22
  Time: 2:27 p. m.
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
  <title>Gestionar Ticket</title>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;700;800&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gestionTicket.css">
</head>
<body>
<div class="login">
  <header class="header">
    <h1 class="titulo">Sistema de Tickets</h1>
    <nav class="sub-header">
      <h2>Gestión del Ticket</h2>
      <form method="POST" action="ListarTicketsController">
        <input type="submit" value="Cancelar" class="boton" >
      </form>
    </nav>
  </header>
  <main class="container">
    <section class="principal">
      <div class="principal_container">
        <form method="POST" action="GestionarTicketController" class="datos_container">
          <label>ID</label>
          <input type="text" value="${miTicket.idTicket}" class="dato" name="idTicket"readonly>
          <label>Título</label>
          <input type="text" value="${miTicket.titulo}" class="dato"  name="titulo"readonly>
          <label>Fecha creación</label>
          <input type="text" class="dato" value="${miTicket.fechaCreacion}" name="fechaCreacion" readonly>
          <label>Prioridad</label>
          <select name="selectPrioridad" class="dato">
            <c:choose>
              <c:when test="${miTicket.prioridad == 'Alta'}">
                <option value="Alta" selected>Alta</option>
                <option value="Media" >Media</option>
                <option value="Baja">Baja</option>
              </c:when>
              <c:when test="${miTicket.prioridad == 'Media'}">
                <option value="Alta" >Alta</option>
                <option value="Media" selected>Media</option>
                <option value="Baja">Baja</option>
              </c:when>
              <c:when test="${miTicket.prioridad == 'Baja'}">
                <option value="Alta" >Alta</option>
                <option value="Media" >Media</option>
                <option value="Baja" selected>Baja</option>
              </c:when>
            </c:choose>
          </select>

          <label>Estado</label>
          <select name="selectEstado" class="dato">
            <c:choose>
              <c:when test="${miTicket.estado == 'Pendiente'}">
                <option value="Pendiente" selected>Pendiente</option>
                <option value="Finalizado">Finalizado</option>
              </c:when>
              <c:when test="${miTicket.estado == 'Finalizado'}">
                <option value="Pendiente">Pendiente</option>
                <option value="Finalizado" selected>Finalizado</option>
              </c:when>
            </c:choose>
          </select>
          <label>Descripción</label>
          <textarea class="dato" name="descripcion"  readonly>${miTicket.descripcion}</textarea>
          <label>Solución</label>
          <textarea class="dato" name="solucion" maxlength="300" placeholder="Agregar solución al ticket">${miTicket.solucion}</textarea>
          <label for="">Tiempo de resolución</label>
          <input type="text" value="${tiempoResolucion}" class="dato" readonly placeholder="No finalizado">

          <input type="submit" value="Guardar" class="enviar" onclick="enviarMensaje();">
        </form>

        <div class="emisor_container">
          <h2>Datos del emisor</h2>
          <p><span class="info">Nombre: </span><span>${miEmisor.nombre}</span></p>
          <p><span class="info">Apellido: </span><span>${miEmisor.apellido}</span></p>
          <p><span class="info">Correo: </span><span>${miEmisor.correo}</span></p>
        </div>
      </div>
    </section>
  </main>
</div>
<script>
  function enviarMensaje() {
    alert("Cambios guardados con éxito");
  }
</script>
</body>
</html>
