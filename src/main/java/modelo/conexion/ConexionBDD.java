package modelo.conexion;

import java.sql.*;

public class ConexionBDD {
    private static ConexionBDD conexionClass =null;
    private static Connection instancia = null;

    private ConexionBDD() {
        if(instancia == null) {
            String servidor = "localhost:3306";
            String database = "nuevosistematickets";
            String usuario = "root";
            String clave = "";
            String url  = "jdbc:mysql://"+servidor+"/"+database;

            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                instancia = DriverManager.getConnection(url, usuario, clave);
                System.out.println("Conexion realizada");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("Error al conectar");
                e.printStackTrace();
            }

        }
    }

    public static Connection getConexion() {
        if(conexionClass == null) {
            conexionClass = new ConexionBDD();
        }
        return instancia;
    }


    public static void cerrar (ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rs = null;
    }


    public static void cerrar(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pstmt = null;
    }
}
