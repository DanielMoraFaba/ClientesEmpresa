package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;

public class Clientes {

    private int capacidad;
    private int tamano;
    public Cliente[] coleccionClientes;

    // CONSTRUCTOR
    public Clientes(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionClientes = new Cliente[capacidad];
    }

    // GETTERS
    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    // MÉTODO capacidadSuperada
    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    // MÉTODO tamanoSuperado
    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    // MÉTODO buscarIndice
    private int buscarIndice(Cliente cliente) {
        for (int i = 0; i < coleccionClientes.length; i++) {
            if (coleccionClientes[i] != null && cliente.getDni().equals(coleccionClientes[i].getDni())) {
                return i;
            }
        }
        return tamano + 1;
    }

    // MÉTODO insertar
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        int indice = buscarIndice(cliente);
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
        }
        if (capacidadSuperada(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
        }
        if (tamanoSuperado(indice)) {
            coleccionClientes[tamano] = new Cliente(cliente);
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
        }
        tamano++;
    }

    // MÉTODO buscar
    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
        }
        if (tamanoSuperado(buscarIndice(cliente))) {
            return null;
        } else {
            return coleccionClientes[buscarIndice(cliente)];
        }
    }

    // MÉTODO desplazarUnaPosicionHaciaIzquierda
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < coleccionClientes.length - 1; i++) {
            coleccionClientes[i] = coleccionClientes[i + 1];
        }
        coleccionClientes[i] = null;
        tamano--;
    }

    // MÉTODO borrar
    public void borrar(Cliente cliente) {
        int indice = buscarIndice(cliente);
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    // MÉTODO get
    public Cliente[] get() {
        return coleccionClientes;
    }

}
