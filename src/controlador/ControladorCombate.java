/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.*;
import dao.DAOJugador;
import java.util.Scanner;

/**
 *
 * @author Tatiana y Jocelyn
 */
public class ControladorCombate {
    private Personaje jugador1, jugador2;
    private Jugador jugador1DB, jugador2DB; // objetos para BD
    private DAOJugador daoJugador;
    private int distancia; // distancia entre jugadores
    private Scanner sc;

    public ControladorCombate() {
        daoJugador = new DAOJugador();
        sc = new Scanner(System.in);
        distancia = 0; // empiezan cara a cara
    }

    // Inicializa jugadores y objetos BD
    public void iniciarCombate(Personaje p1, Personaje p2, String nombreJugador1, String nombreJugador2) {
        this.jugador1 = p1;
        this.jugador2 = p2;

        // Cargar o crear jugadores en BD
        jugador1DB = cargarOInsertarJugador(nombreJugador1);
        jugador2DB = cargarOInsertarJugador(nombreJugador2);

        System.out.println("\n¡Que comience el combate!\n");
        mostrarEstado();

        boolean turnoJugador1 = true;
        boolean combateActivo = true;

        while (combateActivo) {
            if (turnoJugador1) {
                aplicarEfectos(jugador1);
                combateActivo = turno(jugador1, jugador2, jugador1DB, jugador2DB);
            } else {
                aplicarEfectos(jugador2);
                combateActivo = turno(jugador2, jugador1, jugador2DB, jugador1DB);
            }
            turnoJugador1 = !turnoJugador1;
        }
    }

    // Carga jugador de BD o inserta si no existe
    private Jugador cargarOInsertarJugador(String nombre) {
        int id = daoJugador.obtenerIdJugador(nombre);
        if (id == -1) {
            // No existe, insertar nuevo jugador
            Jugador nuevo = new Jugador(nombre);
            boolean inserted = daoJugador.insertarJugador(nuevo);
            if (inserted) {
                id = daoJugador.obtenerIdJugador(nombre);
                nuevo.setId(id);
                return nuevo;
            } else {
                System.out.println("Error creando jugador en BD. Continuando sin persistencia.");
                return new Jugador(nombre); // Sin ID válido
            }
        } else {
            // Para simplicidad, sólo seteamos ID y nombres, sin cargar partidas ganadas/perdidas
            return new Jugador(id, nombre, 0, 0);
        }
    }

    // Aplica efectos como sangrado o curación adicional antes del turno
    private void aplicarEfectos(Personaje p) {
        if (p instanceof Orco) {
            ((Orco) p).aplicarEfectosTurno();
        }
        // Aquí se pueden agregar más efectos futuros
    }

    // Maneja un turno: recibe quien ataca y quien recibe, y objetos Jugador para actualizar BD
    private boolean turno(Personaje atacante, Personaje defensor, Jugador jugadorAtacanteBD, Jugador jugadorDefensorBD) {
        System.out.println("\nTurno de " + capitalizar(atacante.getNombrePersonaje()));
        System.out.println("Opciones:");
        System.out.println("1. Atacar");
        System.out.println("2. Sanar");
        System.out.println("3. Avanzar");
        System.out.println("4. Retroceder");
        System.out.print("Elige una accion: ");

        int opcion = leerEnteroValidado(1, 4);

        switch (opcion) {
            case 1:
                if (distancia != 0 && !armaPuedeAtacarADistancia(atacante)) {
                    System.out.println("No puedes atacar a distancia. Acércate primero.");
                    return true;
                }
                int danio = atacante.atacar();
                danio = ajustarDanioPorDistancia(atacante, danio);
                System.out.println(capitalizar(atacante.getNombrePersonaje()) + " inflige " + danio + " puntos de daño a " + capitalizar(defensor.getNombrePersonaje()));

                int nuevaVida = defensor.getPuntosVida() - danio;
                defensor.setPuntosVida(Math.max(nuevaVida, 0));

                // Sangrado Orco
                if (atacante instanceof Orco && atacante.getArmaEquipada().getNombreArma().equalsIgnoreCase("Hacha")) {
                    if (defensor instanceof Orco) {
                        ((Orco) defensor).activarSangrado();
                    }
                }
                break;

            case 2:
                atacante.sanar();
                break;

            case 3:
                distancia++;
                System.out.println(capitalizar(atacante.getNombrePersonaje()) + " avanza. Distancia actual: " + distancia);
                break;

            case 4:
                distancia--;
                if (distancia < 0) distancia = 0;
                System.out.println(capitalizar(atacante.getNombrePersonaje()) + " retrocede. Distancia actual: " + distancia);
                break;
        }

        mostrarEstado();

        if (defensor.getPuntosVida() <= 0) {
            System.out.println(capitalizar(defensor.getNombrePersonaje()) + " ha sido derrotado.");
            actualizarEstadisticas(jugadorAtacanteBD, jugadorDefensorBD);
            return false; // combate termina
        }
        return true;
    }

    // Ajusta daño según distancia y arma
    private int ajustarDanioPorDistancia(Personaje atacante, int danioBase) {
        if (distancia == 0) return danioBase;

        String raza = atacante.tipoRaza.toLowerCase();
        String arma = atacante.getArmaEquipada().getNombreArma().toLowerCase();

        if (raza.equals("humano") && arma.equals("rifle francotirador")) {
            danioBase = 5 + (int)(Math.random() * 6); // 5 a 10
        } else if (raza.equals("elfo") && arma.equals("baculo")) {
            if (((Elfo)atacante).tipoMagia.equalsIgnoreCase("aire")) {
                danioBase = 4 + (int)(Math.random() * 9); // 4 a 12
            }
        }
        return danioBase;
    }

    private boolean armaPuedeAtacarADistancia(Personaje p) {
        String raza = p.tipoRaza.toLowerCase();
        String arma = p.getArmaEquipada().getNombreArma().toLowerCase();

        if (raza.equals("humano") && arma.equals("rifle francotirador")) return true;
        if (raza.equals("elfo") && arma.equals("baculo") && ((Elfo)p).tipoMagia.equalsIgnoreCase("aire")) return true;

        return false;
    }

    private void actualizarEstadisticas(Jugador ganador, Jugador perdedor) {
        ganador.setPartidasGanadas(ganador.getPartidasGanadas() + 1);
        perdedor.setPartidasPerdidas(perdedor.getPartidasPerdidas() + 1);

        boolean ok1 = daoJugador.actualizarEstadisticas(ganador);
        boolean ok2 = daoJugador.actualizarEstadisticas(perdedor);

        if (ok1 && ok2) {
            System.out.println("Estadisticas actualizadas en la base de datos.");
        } else {
            System.out.println("Error al actualizar estadísticas en la base de datos.");
        }

        System.out.println(capitalizar(ganador.getNombre()) + " ¡es el ganador!");
    }

    private void mostrarEstado() {
        System.out.println("\n Estado de los jugadores");
        jugador1.mostrarInfo();
        jugador2.mostrarInfo();
        System.out.println("Distancia actual: " + distancia);
    }

    private int leerEnteroValidado(int min, int max) {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion < min || opcion > max) {
                    System.out.print("Opción invalida, intenta de nuevo: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida, ingresa un número: ");
            }
        }
        return opcion;
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}
