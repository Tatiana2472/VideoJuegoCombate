/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import basedatos.ConexionBD;
import java.sql.*;

/**
 *
 * @author tatia
 */
public class DAORaza {
    private Connection conn;

    public DAORaza() {
        conn = ConexionBD.getConexion();
    }

    public boolean insertarRaza(String nombre, String descripcion) {
        String sql = "INSERT INTO raza (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando raza: " + e.getMessage());
            return false;
        }
    }
}
