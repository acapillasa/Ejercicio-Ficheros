package Ej3;

import java.io.Serializable;

public class TelefonoMovil implements Serializable {
    private int numero;
    private int credito;
    TelefonoMovil(int numero,int credito) {
        this.numero = numero;
        this.credito = credito;
    }
    void visualizacion() {
        System.out.println("Numero: "+numero+" --- Credito: "+credito);
    }
    void recarga(int s) {
        credito = credito + s;
    }
    void llamada(int minutos) {
        credito = credito - (2*minutos);
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
}
