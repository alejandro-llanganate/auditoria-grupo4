package modelo.dao;

import modelo.conexion.ConexionBDD;
import modelo.entidad.Emisor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmisorDAO {
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    private String SQL_SELECT = "SELECT * FROM emisor WHERE id_emisor = ?";
    private String SQL_AUTENTICAR = "SELECT * FROM emisor WHERE correo = ? AND clave = ?";

    public Emisor obtenerEmisor(int idEmisor) {
        Emisor emisor = null;
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_SELECT);
            pstmt.setInt(1, idEmisor);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                emisor = new Emisor();
                emisor.setIdEmisor(rs.getInt("id_emisor"));
                emisor.setNombre(rs.getString("nombre"));
                emisor.setApellido(rs.getString("apellido"));
                emisor.setCorreo(rs.getString("correo"));
                emisor.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el emisor: " + e.getMessage());
            e.printStackTrace();
        }
        return emisor;
    }

    public Emisor autenticar(String correo, String clave) {
        Emisor emisor = null;
        try {
            pstmt = ConexionBDD.getConexion().prepareStatement(SQL_AUTENTICAR);
            pstmt.setString(1, correo);
            pstmt.setString(2, clave);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                emisor = new Emisor();
                emisor.setIdEmisor(rs.getInt("id_emisor"));
                emisor.setNombre(rs.getString("nombre"));
                emisor.setApellido(rs.getString("apellido"));
                emisor.setCorreo(rs.getString("correo"));
                emisor.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            System.out.println("Error al autenticar el emisor: " + e.getMessage());
            e.printStackTrace();
        }
        return emisor;
    }
}
