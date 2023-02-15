package Ej3;

import java.io.Serializable;

public class Empleado implements Serializable {
    private String nombre;
    private int salario;
    private TelefonoMovil movil;
    Empleado(String nombre,int salario,TelefonoMovil movil) {
        this.nombre = nombre;
        this.salario = salario;
        this.movil = movil;
    }
    void visualizacion() {
        System.out.println("Empleado: "+nombre+" Salario: "+salario);
        movil.visualizacion();
    }
    void trabajo() {
        salario = salario + 10;
        movil.llamada(15);
    }
    void trabajo(int g, int m) {
        salario = salario + g;
        movil.llamada(m);
    }

    public String getNombre() {
        return nombre;
    }

    public TelefonoMovil getMovil() {
        return movil;
    }
}
