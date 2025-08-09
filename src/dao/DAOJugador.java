/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Jugador;
import basedatos.ConexionBD;

/**
 *
 * @author tatia
 */
public class DAOJugador {
   private Connection conn;

    public DAOJugador() {
        this.conn = ConexionBD.getConexion();
        if (this.conn == null) {
            System.out.println("Error: conexión a base de datos no establecida en DAOJugador.");
        }
    }

    public int obtenerIdJugador(String nombre) {
        int id = -1;
        String sql = "SELECT id FROM jugador WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            } else {
                System.out.println("No se encontró jugador con nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo ID jugador: " + e.getMessage());
        }
        return id;
    }

    public boolean actualizarEstadisticas(Jugador jugador) {
        if (conn == null) {
            System.out.println("No hay conexión para actualizar estadísticas.");
            return false;
        }

        String sql = "UPDATE jugador SET partidas_ganadas = ?, partidas_perdidas = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jugador.getPartidasGanadas());
            ps.setInt(2, jugador.getPartidasPerdidas());
            ps.setInt(3, jugador.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Estadísticas actualizadas para el jugador ID: " + jugador.getId());
                return true;
            } else {
                System.out.println("No se actualizó ninguna fila para el jugador ID: " + jugador.getId());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error actualizando estadísticas: " + e.getMessage());
            return false;
        }
    }

    public boolean insertarJugador(Jugador jugador) {
        if (conn == null) {
            System.out.println("No hay conexion para insertar jugador.");
            return false;
        }

        String sql = "INSERT INTO jugador (nombre, partidas_ganadas, partidas_perdidas) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, jugador.getNombre().trim());
            ps.setInt(2, jugador.getPartidasGanadas());
            ps.setInt(3, jugador.getPartidasPerdidas());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Jugador insertado correctamente: " + jugador.getNombre());
                return true;
            } else {
                System.out.println("No se inserto el jugador: " + jugador.getNombre());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error insertando jugador: " + e.getMessage());
            return false;
        }
    }
}

