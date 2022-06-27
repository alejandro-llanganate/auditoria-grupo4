package controlador;

import modelo.dao.EmpleadoDAO;
import modelo.entidad.Empleado;
import modelo.validacion.BloqueoEmpleado;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "LoginEmpleadoController", value = "/LoginEmpleadoController")
public class LoginEmpleadoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginEmpleadoController - doGet");
        getServletContext().getRequestDispatcher("/jsp/moduloEmpleado/loginEmpleado.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginEmpleadoController - doPost");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String clave = request.getParameter("clave");
        EmpleadoDAO empleadodao = new EmpleadoDAO();
        Empleado miEmpleado = empleadodao.autenticar(nombreUsuario, clave);

        if (miEmpleado != null) {
            //Aqui el empleado esta autenticado, se verifica tambien si no esta bloqueado
            String empleadoBloqueado = BloqueoEmpleado.verificarBloqueo(nombreUsuario);
            System.out.println("Empleado bloqueado: " + empleadoBloqueado);
            if(empleadoBloqueado == null){
                //El empleado no esta bloqueado
                BloqueoEmpleado.limpiarEmpleado(nombreUsuario);
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("miEmpleado", miEmpleado);
                request.getRequestDispatcher("ListarTicketsController").forward(request, response);
            }else{
                //El empleado esta bloqueado
                System.out.println("Empleado bloqueado: " + empleadoBloqueado);
                request.setAttribute("mensaje", empleadoBloqueado);
                getServletContext().getRequestDispatcher("/jsp/moduloEmpleado/loginEmpleado.jsp").forward(request, response);

            }

        }else{
            //Aqui es que el empleado no esta autenticado
            BloqueoEmpleado.bloquearEmpleado(nombreUsuario);
            String empleadoBloqueado = BloqueoEmpleado.verificarBloqueo(nombreUsuario);
            System.out.println("Empleado bloqueado: " + empleadoBloqueado);
            System.out.println("No se encontro el empleado");
            request.setAttribute("mensaje", "Usuario o clave incorrectos");
            getServletContext().getRequestDispatcher("/jsp/moduloEmpleado/loginEmpleado.jsp").forward(request, response);
        }

    }
}
