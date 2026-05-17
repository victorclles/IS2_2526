package es.unican.is2.TransportesRefactorizado;

/* Clase que representa un transporte realizado por un conductor */
public abstract class Transporte { // Wmc = 1, Ccog = 0
	protected double horas;
	
	public double horas() { // Wmc + 1
		return horas;
	}

	abstract public double sueldoExtra();
}
