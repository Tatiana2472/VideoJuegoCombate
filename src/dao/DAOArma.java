/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import basedatos.ConexionBD;
import modelo.Arma;
import java.sql.*;

/**
 *
 * @author tatia
 */
public class DAOArma {
    private Connection conn;

    public DAOArma() {
        conn = ConexionBD.getConexion();
    }

    public boolean insertarArma(Arma arma) {
        String sql = "INSERT INTO arma (nombre, tipo, daño_minimo, daño_maximo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, arma.getNombreArma());
            ps.setString(2, arma.toString());
            ps.setInt(3, arma.getDanioMinimo());
            ps.setInt(4, arma.getDanioMaximo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando arma: " + e.getMessage());
            return false;
        }
    }
}
