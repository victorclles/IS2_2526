package es.unican.is2.TransportesRefactorizado;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un conductor, con sus datos perso * y l * y los transportes que ha realizado. 
 */
public class Conductor { // Wmc = 13, CCog = 3

	private List<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;

	private final static int SUELDO_BASE = 700;
	private final static int EXTRA_EUROS_POR_HORA = 5;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { // Wmc + 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { // Wmc + 4, CCog +2
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
	}

	public String getDni() { // Wmc + 1
		return dni;
	}

	public String getNombre() { // Wmc + 1
		return nombre;
	}

	public String getApellido1() { // Wmc + 1
		return apellido1;
	}

	public String getApellido2() { // Wmc + 1
		return apellido2;
	}

	public String getDireccion() { // Wmc + 1
		return direccion;
	}

	public double sueldo() { // Wmc + 1
		double sueldoTransportes = 0.0;

		for (Transporte t : transportes) { // Wmc + 1, CCog + 1
			sueldoTransportes += t.horas() * EXTRA_EUROS_POR_HORA + t.sueldoExtra();
		}

		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) { // Wmc + 1
		transportes.add(t);
	}

}