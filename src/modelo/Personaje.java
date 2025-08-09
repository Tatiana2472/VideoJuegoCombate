package modelo;
import modelo.Arma; 

public abstract class Personaje {
    protected String nombrePersonaje;
    public String tipoRaza;
    protected int vidaMaxima;
    protected int puntosVida;
    public int fuerza;
    public int energia;
    protected Arma armaEquipada;

    public Personaje(String nombrePersonaje, String tipoRaza, int puntosVida, int fuerza, int energia, Arma armaEquipada) {
        this.nombrePersonaje = nombrePersonaje;
        this.tipoRaza = tipoRaza;
        this.vidaMaxima = vidaMaxima;
        this.puntosVida = puntosVida;
        this.fuerza = fuerza;
        this.energia = energia;
        this.armaEquipada = armaEquipada;
    }

    //Metodo para recibir daño sin bajar de 0
    public void recibirDanio(int danio) {
        this.puntosVida -= danio;
        if (this.puntosVida < 0) this.puntosVida = 0;
    }
    
    //Metodo para curar sin pasar la vida Maxima
    public void curar(int cantidad) {
        this.puntosVida += cantidad;
        if(this.puntosVida > vidaMaxima) this.puntosVida = vidaMaxima;
    }
    public abstract int atacar();
    public abstract void sanar();
    public abstract void mostrarInfo();

    // Getters 
    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public String getTipoRaza() {
        return tipoRaza;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getEnergia() {
        return energia;
    }
}

