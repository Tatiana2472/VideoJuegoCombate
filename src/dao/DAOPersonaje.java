/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import basedatos.ConexionBD;
import modelo.Personaje;
import java.sql.*;

/**
 *
 * @author tatia
 */
public class DAOPersonaje {
    private Connection conn;

    public DAOPersonaje() {
        conn = ConexionBD.getConexion();
    }

    public boolean insertarPersonaje(Personaje p, int idJugador, int idArma, int idRaza) {
        String sql = "INSERT INTO personaje (nombre, raza, fuerza, energia, vida_actual, id_arma, id_jugador) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombrePersonaje());
            ps.setString(2, p.getTipoRaza());
            ps.setInt(3, p.fuerza);
            ps.setInt(4, p.energia);
            ps.setInt(5, p.getPuntosVida());
            ps.setInt(6, idArma);
            ps.setInt(7, idJugador);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando personaje: " + e.getMessage());
            return false;
        }
    }
}