/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Tatiana y Jocelyn
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/juegocombate";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "admi";
    
    private static Connection conexion = null;
    
   public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (SQLException e) {
                System.out.println("Error conectando a la base de datos: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }
}
