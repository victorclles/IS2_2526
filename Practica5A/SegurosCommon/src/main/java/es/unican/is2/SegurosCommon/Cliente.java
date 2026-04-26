package es.unican.is2.SegurosCommon;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
public class Cliente {

    private String dni;

    private String nombre;  
    
    private boolean minusvalia;

    private List<Seguro> seguros = new LinkedList<Seguro>();

    public Cliente() {};

    /**
     * Constructor de la clase Cliente.
     */
    public Cliente(String dni, String nombre, boolean minusvalia) {
        if (dni == null) throw new NullPointerException("dni cannot be null");
        if (nombre == null) throw new NullPointerException("nombre cannot be null");
        if (!dni.matches("\\d{8}[A-Z]"))
            throw new IllegalArgumentException("`dni` must follow this format 8 numbers + 1 uppercase char");
        
        this.dni = dni;
        this.nombre = nombre;
        this.minusvalia = minusvalia;
    }
    
	/**
     * Retorna los seguros del cliente 
     */
    public List<Seguro> getSeguros() {
        return seguros;
    }
    
    /**
     * Asigna la lista de seguros
     */
    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    /**
     * Retorna el nombre del cliente.   
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del cliente
     */
    public void setNombre(String nombre) {
        if (nombre == null) 
            throw new IllegalArgumentException("`nombre` cannot be null");

        this.nombre = nombre;
    }
    /**
     * Retorna el dni del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el dni del cliente
     * @param dni
     */
    public void setDni(String dni) {
        if (dni == null) throw new NullPointerException("dni cannot be null");
        if (!dni.matches("\\d{8}[A-Z]"))
            throw new IllegalArgumentException("`dni` must follow this format 8 numbers + 1 uppercase char");
        this.dni = dni;
    }
    
    /**
     * Indica si el cliente es minusvalido
     */
    public boolean getMinusvalia() {
    	return minusvalia;
    }

    /**
     * Asigna la minusvalia del cliente
     * @param minusvalia
     */
     public void setMinusvalia(boolean minusvalia) {
        this.minusvalia = minusvalia;
    }

    @Override
    public int hashCode() {
        if (dni != null) return dni.hashCode();
        if (nombre != null) return nombre.hashCode();
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
            obj.getClass() == Cliente.class &&
            ((Cliente) obj).dni.equals(this.dni);
    }
    
    /**
     * Calcula el total a pagar por el cliente por 
     * todos los seguros a su nombre
     */
    public double totalSeguros() {
    	double precio = seguros
        		.stream()
        		.map(Seguro::precio)
        		.reduce(0.0, Double::sum);
    	
        if (getMinusvalia()) {
        	precio *= (1 - Seguro.DESCUENTO_MINUSVALIA);
        }
        
    	return precio;
    }
}
