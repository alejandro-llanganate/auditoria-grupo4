package modelo.dao;

import modelo.conexion.ConexionBDD;
import modelo.entidad.CambioResponsables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CambioResponsablesDAO {
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    String SQL_OBTENER_TODOS_LOS_RESPONSABLES_DEL_TICKET = "SELECT * FROM CAMBIORESPONSABLES WHERE ID_TICKET = ?";
    String SQL_INSERT_CAMBIO_RESPONSABLE = "INSERT INTO CAMBIORESPONSABLES (ID_TICKET, ANTIGUO_RESPONSABLE, NUEVO_RESPONSABLE, RAZON_CAMBIO) VALUES (?, ?, ?, ?)";

    public CambioResponsablesDAO() {
    }

    public List<CambioResponsables> obtenerTodosLosResponsablesDelTicket(int idTicket) {
        List<CambioResponsables> listaResponsables = new ArrayList<>();
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_OBTENER_TODOS_LOS_RESPONSABLES_DEL_TICKET);
            pstmt.setInt(1, idTicket);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CambioResponsables responsable = new CambioResponsables();
                responsable.setIdCambio(rs.getInt("id_cambio"));
                responsable.setIdTicket(rs.getInt("id_ticket"));
                responsable.setAntiguoResponsable(new EmpleadoDAO().obtenerEmpleado(rs.getInt("antiguo_responsable")));
                responsable.setNuevoResponsable(new EmpleadoDAO().obtenerEmpleado(rs.getInt("nuevo_responsable")));
                responsable.setRazonCambio(rs.getString("razon_cambio"));

                listaResponsables.add(responsable);

            }
        }catch(Exception e) {
            System.out.println("Error al obtener los responsables del ticket: " + e.getMessage());
            e.printStackTrace();
        }
        return listaResponsables;
    }

    public boolean insertarCambioResponsable(CambioResponsables cambioResponsable) {

        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_INSERT_CAMBIO_RESPONSABLE);
            pstmt.setInt(1, cambioResponsable.getIdTicket());
            pstmt.setInt(2, cambioResponsable.getAntiguoResponsable().getIdEmpleado());
            pstmt.setInt(3, cambioResponsable.getNuevoResponsable().getIdEmpleado());
            pstmt.setString(4, cambioResponsable.getRazonCambio());
            pstmt.executeUpdate();
            return true;
        }catch(Exception e) {
            System.out.println("Error al cambiar el responsable: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
