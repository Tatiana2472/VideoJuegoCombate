/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Bestia;
import modelo.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tatiana Y Jocelyn
 */
public class ControladorMenu {
    private Scanner sc;

    public ControladorMenu() {
        sc = new Scanner(System.in);
    }

    // Método para iniciar el flujo completo de selección y combate
    public void iniciarMenu() {
        System.out.println("Bienvenido al juego de combate por turnos.\n");

        System.out.print("Jugador 1, ingresa tu nombre: ");
        String nombre1 = sc.nextLine().trim();

        System.out.print("Jugador 2, ingresa tu nombre: ");
        String nombre2 = sc.nextLine().trim();

        System.out.println("\nJugador 1, selecciona tu raza:");
        Personaje p1 = seleccionarPersonaje(nombre1);

        System.out.println("\nJugador 2, selecciona tu raza:");
        Personaje p2 = seleccionarPersonaje(nombre2);

        ControladorCombate combate = new ControladorCombate();
        combate.iniciarCombate(p1, p2, nombre1, nombre2);
    }

    // Selecciona personaje con raza y arma, retorna objeto Personaje
    private Personaje seleccionarPersonaje(String nombreJugador) {
        int opcionRaza = mostrarMenuRazas();

        switch (opcionRaza) {
            case 1:
                return crearHumano(nombreJugador);

            case 2:
                return crearElfo(nombreJugador);

            case 3:
                return crearOrco(nombreJugador);

            case 4:
                return crearBestia(nombreJugador);

            default:
                System.out.println("Opción inválida. Se asigna Humano por defecto.");
                return crearHumano(nombreJugador);
        }
    }

    private int mostrarMenuRazas() {
        System.out.println("Selecciona una raza:");
        System.out.println("1. Humano");
        System.out.println("2. Elfo");
        System.out.println("3. Orco");
        System.out.println("4. Bestia");
        System.out.print("Opción: ");
        return leerEnteroValidado(1, 4);
    }

    private Personaje crearHumano(String nombre) {
        System.out.println("Armas para Humano:");
        System.out.println("1. Escopeta");
        System.out.println("2. Rifle Francotirador");
        System.out.print("Elige tu arma: ");
        int opcion = leerEnteroValidado(1, 2);
        Arma arma;

        if (opcion == 1) {
            arma = new Arma("Escopeta", "Fuego", 1, 5);
        } else {
            arma = new Arma("Rifle Francotirador", "Fuego", 1, 5);
        }
        return new Humano(nombre, arma);
    }

    private Personaje crearElfo(String nombre) {
        System.out.println("Magia para Elfo:");
        System.out.println("1. Fuego");
        System.out.println("2. Tierra");
        System.out.println("3. Aire");
        System.out.println("4. Agua");
        System.out.print("Elige tipo de magia: ");
        int opcion = leerEnteroValidado(1, 4);
        String tipoMagia;

        switch (opcion) {
            case 1: tipoMagia = "Fuego"; break;
            case 2: tipoMagia = "Tierra"; break;
            case 3: tipoMagia = "Aire"; break;
            case 4: tipoMagia = "Agua"; break;
            default: tipoMagia = "Fuego"; break;
        }

        Arma arma = new Arma("báculo", "Magia", 1, 5);
        return new Elfo(nombre, arma, tipoMagia);
    }

    private Personaje crearOrco(String nombre) {
        System.out.println("Armas para Orco:");
        System.out.println("1. Hacha");
        System.out.println("2. Martillo");
        System.out.print("Elige tu arma: ");
        int opcion = leerEnteroValidado(1, 2);
        Arma arma;

        if (opcion == 1) {
            arma = new Arma("Hacha", "Cuerpo a cuerpo", 1, 5);
        } else {
            arma = new Arma("Martillo", "Cuerpo a cuerpo", 1, 5);
        }
        return new Orco(nombre, arma);
    }

    private Personaje crearBestia(String nombre) {
        System.out.println("Armas para Bestia:");
        System.out.println("1. Puños");
        System.out.println("2. Espada");
        System.out.print("Elige tu arma: ");
        int opcion = leerEnteroValidado(1, 2);
        Arma arma;

        if (opcion == 1) {
            arma = new Arma("Puños", "Cuerpo a cuerpo", 25, 25);
        } else {
            arma = new Arma("Espada", "Cuerpo a cuerpo", 1, 10);
        }
        return new Bestia(nombre, arma);
    }

    private int leerEnteroValidado(int min, int max) {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion < min || opcion > max) {
                    System.out.print("Opción inválida, intenta de nuevo: ");
                } else {
                    return opcion;
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida, ingresa un número: ");
            }
        }
    }
}
