package modelo;

public class Arma {
    private String nombreArma;
    private String tipoArma;
    private int danioMinimo;
    private int danioMaximo;

    public Arma(String nombreArma, String tipoArma, int danioMinimo, int danioMaximo) {
        this.nombreArma = nombreArma;
        this.tipoArma = tipoArma;
        this.danioMinimo = danioMinimo;
        this.danioMaximo = danioMaximo;
    }

    public String getNombreArma() {
        return nombreArma.toLowerCase(); //Evita errores en comparación
    }

    public int getDanioMinimo() {
        return danioMinimo;
    }

    public int getDanioMaximo() {
        return danioMaximo;
    }

    @Override
    public String toString() {
        return nombreArma + " (" + tipoArma + ") [" + danioMinimo + "-" + danioMaximo + "]";
    }
}
