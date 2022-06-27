package controlador;

import modelo.dao.EmisorDAO;
import modelo.dao.EmpleadoDAO;
import modelo.entidad.Emisor;
import modelo.entidad.Empleado;
import modelo.validacion.BloqueoEmisor;
import modelo.validacion.BloqueoEmpleado;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginEmisorController", value = "/LoginEmisorController")
public class LoginEmisorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginEmisorController - doGet");
        getServletContext().getRequestDispatcher("/jsp/moduloEmisor/loginEmisor.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginEmisorController - doPost");

        String nombreUsuario = request.getParameter("nombreUsuario");
        String clave = request.getParameter("clave");

        EmisorDAO emisorDAO = new EmisorDAO();
        Emisor miEmisor = emisorDAO.autenticar(nombreUsuario, clave);

        if (miEmisor != null) {
            //Aqui el emisor esta autenticado, se verifica tambien si no esta bloqueado
            String emisorBloqueado = BloqueoEmisor.verificarBloqueo(nombreUsuario);
            System.out.println("Usuario bloqueado: " + emisorBloqueado);
            if(emisorBloqueado == null){
                //El emisor no esta bloqueado
                BloqueoEmisor.limpiarEmisor(nombreUsuario);
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("miEmisor", miEmisor);
                request.getRequestDispatcher("GestionSolicitudController").forward(request, response);
            }else{
                //El emisor esta bloqueado
                System.out.println("Emisor bloqueado: " + emisorBloqueado);
                request.setAttribute("mensaje", emisorBloqueado);
                getServletContext().getRequestDispatcher("/jsp/moduloEmisor/loginEmisor.jsp").forward(request, response);

            }

        }else{
            //Aqui es que el emisor no esta autenticado
            BloqueoEmisor.bloquearEmisor(nombreUsuario);
            String emisorBloqueado = BloqueoEmisor.verificarBloqueo(nombreUsuario);
            System.out.println("Emisor bloqueado: " + emisorBloqueado);
            System.out.println("No se encontro el emisor");
            request.setAttribute("mensaje", "Correo o clave incorrectos");
            getServletContext().getRequestDispatcher("/jsp/moduloEmisor/loginEmisor.jsp").forward(request, response);
        }
    }
}
