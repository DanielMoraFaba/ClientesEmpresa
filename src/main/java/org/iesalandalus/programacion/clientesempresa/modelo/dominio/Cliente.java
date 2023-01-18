package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cliente {

    private final String ER_CORREO = "^[a-zA-Z0-9](\\w|-|\\.)+@[a-z]+\\.[a-z]+";
    private final String ER_DNI = "\\d{8}[A-Z]";
    private final String ER_TELEFONO = "\\d{9}";
    public static final DateTimeFormatter FORMATO_FECHA=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nombre;
    private String dni;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;

    // CONSTRUCTORES
    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
        }
        nombre = cliente.getNombre();
        dni = cliente.getDni();
        correo = cliente.getCorreo();
        telefono = cliente.getTelefono();
        fechaNacimiento = cliente.getFechaNacimiento();
    }

    public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    // MÉTODO formateaNombre
    private String formateaNombre(String nombre) {
        nombre = nombre.trim();
        StringBuilder nombreCompleto = new StringBuilder();
        String[] arrayNombre = nombre.split(" +");
        for (String parteNombre : arrayNombre) {
            parteNombre = parteNombre.substring(0, 1).toUpperCase() + parteNombre.substring(1).toLowerCase();
            nombreCompleto.append(parteNombre).append(" ");
        }
        nombre = nombreCompleto.toString().trim();
        return nombre;
    }

    // MÉTODO comprobarLetraDni
    private boolean comprobarLetraDni(String dni) {
        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int numDni = Integer.parseInt(dni.substring(0, 8));
        char letraDni = dni.substring(8).charAt(0);
        return letrasDni[numDni % 23] == letraDni ? true : false;
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    // SETTERS
    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
        }
        this.dni = dni;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    // MÉTODOS equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(ER_CORREO, cliente.ER_CORREO) && Objects.equals(ER_DNI, cliente.ER_DNI) && Objects.equals(ER_TELEFONO, cliente.ER_TELEFONO) && Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getDni(), cliente.getDni()) && Objects.equals(getCorreo(), cliente.getCorreo()) && Objects.equals(getTelefono(), cliente.getTelefono()) && Objects.equals(getFechaNacimiento(), cliente.getFechaNacimiento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ER_CORREO, ER_DNI, ER_TELEFONO, getNombre(), getDni(), getCorreo(), getTelefono(), getFechaNacimiento());
    }

    // MÉTODO getIniciales
    private String getIniciales() {
        nombre = nombre.trim();
        StringBuilder iniciales = new StringBuilder();
        String[] arrayNombre = nombre.split(" +");
        for (String parteNombre : arrayNombre) {
            parteNombre = parteNombre.substring(0, 1).toUpperCase();
            iniciales.append(parteNombre);
        }
        return "(" + iniciales.toString() + ")";
    }

    // MÉTODO toString
    @Override
    public String toString() {
        return "nombre=" + nombre + " " + getIniciales() + ", DNI=" + dni + ", correo=" + correo + ", teléfono=" + telefono + ", fecha nacimiento=" + fechaNacimiento.format(FORMATO_FECHA);
    }

}
