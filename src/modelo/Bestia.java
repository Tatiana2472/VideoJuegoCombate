/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;
import modelo.Personaje;
/**
 *
 * @author jocelyn y tati
 */
public class Bestia extends Personaje {
    
    public Bestia(String nombrePersonaje, Arma armaEquipada) {
        super(nombrePersonaje, "Bestia", 100, 15, 90, armaEquipada);
    }

    @Override
    public int atacar() {
        Random rand = new Random();
        if (armaEquipada.getNombreArma().equalsIgnoreCase("Punnos")){
            //Daño fijo 25, pero pierde 10 vida quien ataca
            return 25;
         }else if (armaEquipada.getNombreArma().equalsIgnoreCase("Espada")){
             return rand.nextInt(armaEquipada.getDanioMaximo() - armaEquipada.getDanioMinimo() + 1) + armaEquipada.getDanioMinimo();
        }
        return 0;
    }

    @Override
    public void sanar() {
        int vidaPerdida = getVidaMaxima() - this.puntosVida;
        int vidaRecuperada = (int)(vidaPerdida * 0.45);
        System.out.println(nombrePersonaje + " duerme y recupera " + vidaRecuperada + " puntos de vida.");
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Bestia: " + nombrePersonaje + " | Vida: " + puntosVida + " | Arma: " + armaEquipada.getNombreArma());
    }
}
