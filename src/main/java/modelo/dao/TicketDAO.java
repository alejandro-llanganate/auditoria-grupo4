package modelo.dao;

import modelo.conexion.ConexionBDD;
import modelo.entidad.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private String SQL_INSERT = "INSERT INTO ticket (fecha_creacion, titulo, estado, prioridad, descripcion, solucion, fecha_cierre, id_empleado, id_emisor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String SQL_SELECTALL = "SELECT * FROM ticket WHERE id_empleado = ?";
    private String SQL_SELECTALL_IDTICKET = "SELECT * FROM ticket WHERE id_ticket = ?";
    private String SQL_UPDATE = "UPDATE ticket SET estado = ?, prioridad = ?, solucion = ?, fecha_cierre = ? WHERE id_ticket = ?";
    private String SQL_SELECTALL_IDEMISOR = "SELECT * FROM ticket WHERE id_emisor = ?";

    private ResultSet rs = null;
    private PreparedStatement pstmt = null;


    public TicketDAO() {
    }

    public boolean crearTicket(Ticket ticket){
        int empleadoConMenosCarga = this.obtenerEmpleadoConMenosCarga();
        pstmt = null;
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_INSERT);
            pstmt.setString(1, ticket.getFechaCreacion());
            pstmt.setString(2, ticket.getTitulo());
            pstmt.setString(3, ticket.getEstado());
            pstmt.setString(4, ticket.getPrioridad());
            pstmt.setString(5, ticket.getDescripcion());
            pstmt.setString(6, ticket.getSolucion());
            pstmt.setString(7, ticket.getFechaCierre());
            pstmt.setInt(8, empleadoConMenosCarga);

            System.out.println("id del emisot: " + ticket.getEmisor().getIdEmisor());
            pstmt.setInt(9, ticket.getEmisor().getIdEmisor());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear el ticket");
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarTicket(Ticket ticket){
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_UPDATE);
            pstmt.setString(1, ticket.getEstado());
            pstmt.setString(2, ticket.getPrioridad());
            pstmt.setString(3, ticket.getSolucion());
            pstmt.setString(4, ticket.getFechaCierre());
            pstmt.setInt(5, ticket.getIdTicket());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el ticket");
            e.printStackTrace();
        }
        return false;
    }

    public Ticket obtenerTicket(int idTicket){
        Ticket miTicket = null;
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_SELECTALL_IDTICKET);
            pstmt.setInt(1, idTicket);
            rs = pstmt.executeQuery();
            while (rs.next()){
                miTicket = new Ticket();
                miTicket.setIdTicket(rs.getInt("id_ticket"));
                miTicket.setFechaCreacion(rs.getString("fecha_creacion"));
                miTicket.setTitulo(rs.getString("titulo"));
                miTicket.setEstado(rs.getString("estado"));
                miTicket.setPrioridad(rs.getString("prioridad"));
                miTicket.setDescripcion(rs.getString("descripcion"));
                miTicket.setSolucion(rs.getString("solucion"));
                miTicket.setFechaCierre(rs.getString("fecha_cierre"));
                miTicket.setEmpleadoEncargado(new EmpleadoDAO().obtenerEmpleado(rs.getInt("id_empleado")));
                miTicket.setEmisor(new EmisorDAO().obtenerEmisor(rs.getInt("id_emisor")));
            }
        }catch (SQLException e){
            System.out.println("Error al obtener el ticket por su id");
            e.printStackTrace();
        }

        return miTicket;
    }

    public List<Ticket> obtenerTodosTickets(int idEmpleado){
        List<Ticket> listaTickets = new ArrayList<>();
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_SELECTALL);
            pstmt.setInt(1, idEmpleado);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_ticket"));
                ticket.setFechaCreacion(rs.getString("fecha_creacion"));
                ticket.setTitulo(rs.getString("titulo"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setPrioridad(rs.getString("prioridad"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setSolucion(rs.getString("solucion"));
                ticket.setFechaCierre(rs.getString("fecha_cierre"));
                ticket.setEmpleadoEncargado(new EmpleadoDAO().obtenerEmpleado(rs.getInt("id_empleado")));
                ticket.setEmisor(new EmisorDAO().obtenerEmisor(rs.getInt("id_emisor")));
                listaTickets.add(ticket);
            }

        }catch (SQLException e){
            System.out.println("Error al obtener todos los tickets");
            e.printStackTrace();
        }
        return listaTickets;
    }


    public int obtenerEmpleadoConMenosCarga(){
        int idEmpleado = 0;
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement("SELECT empleado.id_empleado, COUNT(ticket.id_empleado) AS total\n" +
                    "FROM empleado \n" +
                    "LEFT JOIN ticket ON empleado.id_empleado = ticket.id_empleado\n" +
                    "GROUP BY id_empleado \n" +
                    "ORDER BY total\n" +
                    "LIMIT 1");
            rs = pstmt.executeQuery();
            while (rs.next()){
                idEmpleado = rs.getInt("id_empleado");
            }

        }catch (SQLException e){
            System.out.println("Error al obtener el empleado con menos carga");
            e.printStackTrace();
        }
        System.out.println("El empleado con menos carga es: " + idEmpleado);
        return idEmpleado;
    }

    public List<Ticket> obtenerTicketsPorEmisor(int idEmisor){
        List<Ticket> listaTickets = new ArrayList<>();
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_SELECTALL_IDEMISOR);
            pstmt.setInt(1, idEmisor);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_ticket"));
                ticket.setFechaCreacion(rs.getString("fecha_creacion"));
                ticket.setTitulo(rs.getString("titulo"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setPrioridad(rs.getString("prioridad"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setSolucion(rs.getString("solucion"));
                ticket.setFechaCierre(rs.getString("fecha_cierre"));
                ticket.setEmpleadoEncargado(new EmpleadoDAO().obtenerEmpleado(rs.getInt("id_empleado")));
                ticket.setEmisor(new EmisorDAO().obtenerEmisor(rs.getInt("id_emisor")));
                listaTickets.add(ticket);
            }

        }catch (SQLException e){
            System.out.println("Error al obtener los tickets por emisor");
            e.printStackTrace();
        }
        return listaTickets;
    }


    /*
    * DELIMITER $$
CREATE TRIGGER actualizarmiTicket
BEFORE INSERT ON cambioresponsables
FOR EACH ROW
BEGIN
	UPDATE ticket SET ticket.id_empleado = NEW.nuevo_responsable WHERE ticket.id_ticket = NEW.id_ticket;
END$$
DELIMITER ;*/


}
