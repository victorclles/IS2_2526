package es.unican.is2.Transportes;
import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor { // Wmc = 18, CCog = 8

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { // Wmc +1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { 
			// Wmc +4, Ccog + 2
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String dni() { // Wmc +1
		return dni;
	}

	public String getDni() { // Wmc +1
		return dni;
	}

	public String getNombre() { // Wmc +1
		return nombre;
	}

	public String getApellido1() { // Wmc +1
		return apellido1;
	}

	public String apellido2() { // Wmc +1
		return apellido2;
	}

	public String getDire() { // Wmc +1
		return dire;
	}

	public double sueldo() { // Wmc +1
		double sueldoTransportes = 0;
		for (Transporte t : transportes) { // Wmc +1, Ccog + 1
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) {// Ccog + 2
				case Mercancias: // Wmc +1
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas: // Wmc +1
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas: // Wmc +1
					if (t.getPersonas() < 10) // Wmc +1, Ccog + 3
						sueldoExtraTransporte = t.horas() * 0.5;
					else
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) { // Wmc +1
		transportes.add(t);
	}

}
