package modelo;

import java.util.Random;
import modelo.Arma;

public class Humano extends Personaje {

    public Humano(String nombrePersonaje, Arma armaEquipada) {
        super(nombrePersonaje, "Humano", 100, 10, 100, armaEquipada);
    }

    @Override
    public int atacar() {
        Random rand = new Random();
        int danio = rand.nextInt(armaEquipada.getDanioMaximo() - armaEquipada.getDanioMinimo() + 1) + armaEquipada.getDanioMinimo();

        if (armaEquipada.getNombreArma().equalsIgnoreCase("Escopeta")) {
            danio += (int)(danio * 0.02);
        }

        return danio;
    }

    @Override
    public void sanar() {
        int vidaPerdida = getVidaMaxima() - this.puntosVida;
        int vidaRecuperada = (int)(vidaPerdida * 0.5);
        curar(vidaRecuperada); //usar metodo curar
        if (this.puntosVida > 100) this.puntosVida = 100;

        System.out.println(nombrePersonaje + " se alimenta y recupera " + vidaRecuperada + " puntos de vida.");
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Humano: " + nombrePersonaje + " | Vida: " + puntosVida + " | Arma: " + armaEquipada.getNombreArma());
    }
}
