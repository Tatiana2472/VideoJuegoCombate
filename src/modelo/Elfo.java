package modelo;

import java.util.Random;

public class Elfo extends Personaje {
    public String tipoMagia;

    public Elfo(String nombrePersonaje, Arma armaEquipada, String tipoMagia) {
        // Si elfo usa magia de agua, inicia con 115 de vida
        super(nombrePersonaje, "Elfo", tipoMagia.equalsIgnoreCase("Agua") ? 115 : 100, 8, 120, armaEquipada);
        this.tipoMagia = tipoMagia;
    }

    @Override
    public int atacar() {
        Random rand = new Random();
        int danioBase = rand.nextInt(armaEquipada.getDanioMaximo() - armaEquipada.getDanioMinimo() + 1) + armaEquipada.getDanioMinimo();
        int danioTotal = danioBase;

        switch (tipoMagia.toLowerCase()) {
            case "fuego":
                danioTotal += (int)(danioBase * 0.10); // +10%
                break;
            case "tierra":
                danioTotal += (int)(danioBase * 0.02); // +2%
                // Nota: lógica para "acierta más veces" se puede añadir después
                break;
            case "aire":
                // sin modificación
                break;
            case "agua":
                // sin modificación
                break;
        }

        return danioTotal;
    }

    @Override
public void sanar() {
    int vidaMaxima = tipoMagia.equalsIgnoreCase("agua") ? 115 : 100;
    int vidaPerdida = vidaMaxima - this.puntosVida;
    if (vidaPerdida < 0) vidaPerdida = 0;
    int vidaRecuperada = (int)(vidaPerdida * (tipoMagia.equalsIgnoreCase("agua") ? 0.90 : 0.75));
    this.puntosVida += vidaRecuperada;
    if (this.puntosVida > vidaMaxima) this.puntosVida = vidaMaxima;

    System.out.println(nombrePersonaje.substring(0,1).toUpperCase() + nombrePersonaje.substring(1) +
        " usa hechizo de sanacion y recupera " + vidaRecuperada + " puntos de vida.");
}

    @Override
    public void mostrarInfo() {
        System.out.println("Elfo: " + nombrePersonaje + " | Vida: " + puntosVida + " | Magia: " + tipoMagia + " | Arma: " + armaEquipada.getNombreArma());
    }

    //Getters
    public String getTipoMagia() {
        return tipoMagia;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public String getTipoRaza() {
        return tipoRaza;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getEnergia() {
        return energia;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }
}
