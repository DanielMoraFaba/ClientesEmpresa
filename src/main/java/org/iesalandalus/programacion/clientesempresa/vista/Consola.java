package org.iesalandalus.programacion.clientesempresa.vista;

import utilidades.Entrada;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

import java.time.LocalDate;
import java.time.Year;

public class Consola {

    private Consola() {

    }

    // MÉTODO mostrarMenu
    public static void mostrarMenu() {
        System.out.println(" ===================|Menú|=================== ");
        System.out.println("| 1. Insertar cliente.                       |");
        System.out.println("| 2. Buscar cliente.                         |");
        System.out.println("| 3. Borrar cliente.                         |");
        System.out.println("| 4. Mostrar clientes nacidos en una fecha.  |");
        System.out.println("| 5. Mostrar todos los clientes.             |");
        System.out.println("| 0. Salir.                                  |");
        System.out.println(" ============================================ ");
    }

    // MÉTODO elegirOpcion
    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Elegir opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > 6);
        switch (opcion) {
            case 1:
                return Opcion.INSERTAR_CLIENTE;
            case 2:
                return Opcion.BUSCAR_CLIENTE;
            case 3:
                return Opcion.BORRAR_CLIENTE;
            case 4:
                return Opcion.MOSTRAR_CLIENTES_FECHA;
            case 5:
                return Opcion.MOSTRAR_CLIENTES;
            case 0:
                return Opcion.SALIR;
            default:
                return null;
        }
    }

    // MÉTODO leerCliente
    public static Cliente leerCliente() {
        String nombre = "a";
        String dni = "a";
        String correo = "a";
        String telefono = "a";
        LocalDate fechaNacimiento;
        boolean error = false;
        Cliente cliente = null;
        String[] nombreCompleto = nombre.split("\\s+");
        do {
            do {
                System.out.print("\nIntroduce nombre y apellidos: ");
                nombre = Entrada.cadena();
                nombreCompleto = nombre.split("\\s+");
            }
            while (nombreCompleto.length < 2);
            do {
                System.out.print("Introduce un dni: ");
                dni = Entrada.cadena();
            }
            while (!dni.matches("[0-9]{7,8}[A-Za-z]"));
            do {
                System.out.print("Introduce el correo: ");
                correo = Entrada.cadena();
            }
            while (!correo.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}"));
            do {
                System.out.print("Introduce un teléfono: ");
                telefono = Entrada.cadena();
            } while (!telefono.matches("^[0-9]{9}$"));
            fechaNacimiento = leerFechaNacimiento();
            try {
                cliente = new Cliente(nombre, dni, correo, telefono, fechaNacimiento);
            } catch (Exception e) {
                error = true;
                e.printStackTrace();
            }
        } while (error);
        return cliente;
    }

    // MÉTODO leerClienteDni
    public static Cliente leerClienteDni() {
        String dni;
        boolean dniCorrecto = false;
        do {
            System.out.println("Introduce un dni");
            dni = Entrada.cadena();
            dniCorrecto = dni.matches("[0-9]{7,8}[A-Z]");
        } while (!dniCorrecto);
        return new Cliente("Andrés Rubio Del Río", dni, "andresrubio@iesalandalus.org", "666223344", LocalDate.of(1992, 7, 3));
    }

    // MÉTODO leerFechaNacimiento
    public static LocalDate leerFechaNacimiento() {
        boolean fechaCorrecta = false;
        int dia = -1, mes = -1, annio = -1;
        while (!fechaCorrecta) {
            System.out.println("Introduce la fecha de nacimiento");
            while (dia > 32 || dia < 0) {
                System.out.print("día: ");
                dia = Entrada.entero();
            }
            while (mes > 13 || mes < 0) {
                System.out.print("mes: ");
                mes = Entrada.entero();
            }
            while (annio > Year.now().getValue() || annio < 1920) {
                System.out.print("año: ");
                annio = Entrada.entero();
            }
            if (mes == 2 && (java.time.Year.of(annio).isLeap()) && dia <= 29) {
                fechaCorrecta = true;
            }
            if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia <= 30) {
                fechaCorrecta = true;
            }
            if ((mes != 4 && mes != 6 && mes != 9 && mes != 11) && dia <= 31) {
                fechaCorrecta = true;
            }
        }
        LocalDate fecha = LocalDate.of(annio, mes, dia);
        return fecha;
    }

}
