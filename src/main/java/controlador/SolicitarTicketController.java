package controlador;

import modelo.dao.TicketDAO;
import modelo.entidad.Emisor;
import modelo.entidad.Ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "SolicitarTicketController", value = "/SolicitarTicketController")
public class SolicitarTicketController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SolicitarTicketController - doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SolicitarTicketController - doPost");

        //Se accede a los datosdel formulario
        Emisor miEmisor = (Emisor) request.getSession().getAttribute("miEmisor");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");

        System.out.println("Imprimo el emisor: " + miEmisor);

        //Se crea el ticket
        TicketDAO miTicketDAO = new TicketDAO();


        Ticket miTicket = new Ticket();
        miTicket.setFechaCreacion(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        miTicket.setTitulo(titulo);
        miTicket.setEstado("Pendiente");
        miTicket.setPrioridad("Baja");
        miTicket.setDescripcion(descripcion);
        miTicket.setSolucion(null);
        miTicket.setFechaCierre(null);
        miTicket.setEmisor(miEmisor);

        //Imprimo el ticket
        System.out.println("Imprimo el ticket: " + miTicket);
        //Se guarda el ticket en la base de datos
        miTicketDAO.crearTicket(miTicket);

        //Se llama a la vista
        request.getRequestDispatcher("GestionSolicitudController").forward(request, response);


    }
}
