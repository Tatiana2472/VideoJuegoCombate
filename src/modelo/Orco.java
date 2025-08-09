package modelo;

import java.util.Random;

public class Orco extends Personaje {
    private int turnosSangrado = 0;

    public Orco(String nombrePersonaje, Arma armaEquipada) {
        super(nombrePersonaje, "Orco", 100, 12, 80, armaEquipada);
    }

    @Override
    public int atacar() {
        Random rand = new Random();
        int danio = rand.nextInt(armaEquipada.getDanioMaximo() - armaEquipada.getDanioMinimo() + 1) + armaEquipada.getDanioMinimo();
        // Nota: activación sangrado la hace el controlador al recibir daño con hacha
        return danio;
    }

    public void activarSangrado() {
        turnosSangrado = 2;
    }

    public void aplicarEfectosTurno() {
        if (turnosSangrado > 0) {
            puntosVida -= 3;
            turnosSangrado--;
            if (puntosVida < 0) puntosVida = 0;
            System.out.println(capitalizar(nombrePersonaje) + " sufre sangrado (-3 vida).");
        }
    }

    @Override
    public void sanar() {
        int vidaPerdida = 100 - this.puntosVida;
        if (vidaPerdida < 0) vidaPerdida = 0;
        int vidaRecuperada = (int)(vidaPerdida * 0.25);
        this.puntosVida += vidaRecuperada;
        if (this.puntosVida > 100) this.puntosVida = 100;

        System.out.println(capitalizar(nombrePersonaje) + " bebe una poción y recupera " + vidaRecuperada + " puntos de vida. Recuperará 15% más el próximo turno.");
        // La curación extra debe manejarla el controlador
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Orco: " + capitalizar(nombrePersonaje) + " | Vida: " + puntosVida + " | Arma: " + armaEquipada.getNombreArma());
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

