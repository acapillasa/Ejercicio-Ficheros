package Ej3;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorEmpleados implements Serializable {
    private String nombreArchivo;

    GestorEmpleados(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    void guardaEmpleados(List<Empleado> listaEmpleados) {
        try {
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < listaEmpleados.size(); i++) {
                oos.writeObject(listaEmpleados.get(i));
            }
            oos.writeObject(null);
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void muestraEmpleados() {
        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Empleado nuevoEmp1 = (Empleado) ois.readObject();

            while (nuevoEmp1 != null) {
                nuevoEmp1.visualizacion();
                nuevoEmp1 = (Empleado) ois.readObject();
            }
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Empleado busquedaEmployee(String empname) {

        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Empleado nuevoEmp1 = (Empleado) ois.readObject();

            while (nuevoEmp1 != null) {
                if (Objects.equals(nuevoEmp1.getNombre(), empname)) {
                    return nuevoEmp1;
                }
                nuevoEmp1 = (Empleado) ois.readObject();
            }
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    void generaFicheroMoviles(String nombreFichero) {
        try {
            FileOutputStream fos = new FileOutputStream(nombreFichero);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            FileInputStream fis = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Empleado nuevoEmp1 = (Empleado) ois.readObject();

            while (nuevoEmp1 != null) {
                nuevoEmp1.getMovil().setCredito(0);
                oos.writeObject(nuevoEmp1.getMovil());
                nuevoEmp1 = (Empleado) ois.readObject();
            }
            oos.writeObject(null);

            oos.close();
            fos.close();
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    void mostrarFicheroMoviles() {
        try {
            FileInputStream fis = new FileInputStream("ficheroMoviles.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            TelefonoMovil nuevotelefono = (TelefonoMovil) ois.readObject();

            while (nuevotelefono != null) {
                nuevotelefono.visualizacion();
                nuevotelefono = (TelefonoMovil) ois.readObject();
            }
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    void trabajarTodos() {
        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Empleado nuevoemp1 = (Empleado) ois.readObject();
            List<Empleado> listaTraspaso = new ArrayList<>();

            while (nuevoemp1 != null) {
                nuevoemp1.trabajo();
                listaTraspaso.add(nuevoemp1);
                nuevoemp1 = (Empleado) ois.readObject();
            }

            FileOutputStream fos = new FileOutputStream(nombreArchivo, false);
            fos.close();
            FileOutputStream fos2 = new FileOutputStream(nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos2);

            for (int i = 0; i < listaTraspaso.size(); i++) {
                oos.writeObject(listaTraspaso.get(i));
            }
            oos.writeObject(null);

            ois.close();
            fis.close();
            oos.close();
            fos2.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        TelefonoMovil tm1 = new TelefonoMovil(637178741,500);
        TelefonoMovil tm2 = new TelefonoMovil(637178742,400);
        TelefonoMovil tm3 = new TelefonoMovil(637178743,300);
        TelefonoMovil tm4 = new TelefonoMovil(637178744,200);
        TelefonoMovil tm5 = new TelefonoMovil(637178745,100);
        Empleado emp1 = new Empleado("Álvaro",1000,tm1);
        Empleado emp2 = new Empleado("Miguel",900,tm2);
        Empleado emp3 = new Empleado("Oussama",800,tm3);
        Empleado emp4 = new Empleado("Jurgi",700,tm4);
        Empleado emp5 = new Empleado("Jon",600,tm5);
        List<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados.add(emp1);
        listaEmpleados.add(emp2);
        listaEmpleados.add(emp3);
        listaEmpleados.add(emp4);
        listaEmpleados.add(emp5);

        GestorEmpleados ge1 = new GestorEmpleados("employee.data");
        ge1.guardaEmpleados(listaEmpleados);

        System.out.println("MOSTRAR EMPLEADOS");
        ge1.muestraEmpleados();

        System.out.println();

        System.out.println("BUSQUEDA DE EMPLEADO EXISTENTE");
        System.out.println(ge1.busquedaEmployee("Jon"));

        System.out.println("BUSQUEDA DE EMPLEADO NO EXISTENTE");
        System.out.println(ge1.busquedaEmployee("Juan"));

        System.out.println();

        System.out.println("FICHERO DE MOVILES");
        ge1.generaFicheroMoviles("ficheroMoviles.txt");

        System.out.println();

        System.out.println("MOSTRAR FICHERO MOVILES");
        ge1.mostrarFicheroMoviles();

        System.out.println();

        System.out.println("TODOS A TRABAJAR");
        ge1.trabajarTodos();

        System.out.println();

        System.out.println("MOSTRAR EMPLEADOS TRABAJANDO");
        ge1.muestraEmpleados();


    }
}
