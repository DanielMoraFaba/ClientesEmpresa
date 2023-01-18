package org.iesalandalus.programacion.clientesempresa;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp {

    private static int NUM_MAX_CLIENTES = 5;
    public static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);

    public static void main(String[] args) {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
        System.out.println("¡Adiós!");
    }

    // MÉTODO insertarCliente
    private static void insertarCliente() {
        Cliente cliente = new Cliente(Consola.leerCliente());
        try {
            clientes.insertar(cliente);
            System.out.println("\nCliente creado con éxito.\n");
        } catch (OperationNotSupportedException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    // MÉTODO buscarCliente
    private static void buscarCliente() {
        Cliente buscarCliente = null;
        Cliente cliente = null;
        cliente = new Cliente(Consola.leerClienteDni());
        try {
            buscarCliente = clientes.buscar(cliente);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (buscarCliente != null) {
            System.out.println(buscarCliente.toString());
        } else {
            System.out.println("\nEl cliente no se ha encontrado o era nulo.\n");
        }
    }

    // MÉTODO borrarCliente
    private static void borrarCliente() {
        boolean clienteABorrar = false;
        Cliente cliente = null;
        cliente = Consola.leerClienteDni();
        for (int i = 0; i < clientes.getTamano(); i++) {
            if (clientes.get()[i].getDni().equals(cliente.getDni())) {
                try {
                    clientes.borrar(cliente);
                    clienteABorrar = true;
                    System.out.println("\nCliente borrado con éxito.\n");
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if (clienteABorrar == false) {
            System.out.println("\nEl cliente no puede borrarse o no existe.\n");
        }
    }

    // MÉTODO mostrarClientesFecha
    private static void mostrarClientesFecha() {
        boolean mostrarCliente = false;
        LocalDate fechaNacimiento = Consola.leerFechaNacimiento();
        for (int i = 0; i < clientes.getTamano(); i++) {
            if (clientes.get()[i].getFechaNacimiento().equals(fechaNacimiento)) {
                mostrarCliente = true;
                System.out.println(clientes.get()[i].toString());
            }
        }
        if (mostrarCliente == false) {
            System.out.println("\nNo se han encontrado clientes con dicha fecha.\n");
        }
    }

    // MÉTODO mostrarClientes
    private static void mostrarClientes() {
        for (int i = 0; i < clientes.getTamano(); i++) {
            try {
                System.out.println(clientes.get()[i].toString());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        if (clientes.getTamano() < 1) {
            System.out.println("\nLa colección de clientes está vacía.\n");
        }
    }

    // MÉTODO ejecutarOpcion
    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_CLIENTE:
                insertarCliente();
                break;

            case BUSCAR_CLIENTE:
                buscarCliente();
                break;

            case BORRAR_CLIENTE:
                borrarCliente();
                break;

            case MOSTRAR_CLIENTES_FECHA:
                mostrarClientesFecha();
                break;

            case MOSTRAR_CLIENTES:
                mostrarClientes();
                break;

            case SALIR:
                break;

        }

    }

}