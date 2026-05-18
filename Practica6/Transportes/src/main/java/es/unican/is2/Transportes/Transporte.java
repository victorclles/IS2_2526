
package es.unican.is2.Transportes;

/* Clase que representa un transporte realizado por un conductor */
public class Transporte { // Wmc = 9, CCog = 4
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {
		// Wmc +1
		if (horas <= 0 || valor <= 0 || cat == null) { // Wmc +3, Ccog + 2
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) { // Wmc +1, Ccog + 1
			this.personas = valor;
		} else  { // Ccog + 1
			this.ton = valor;
		}
	}
	
	public double horas() { // Wmc +1
		return horas;
	}

	public CategoriaTransporte categoria() { // Wmc +1
		return cat;
	}

	public int ton() { // Wmc +1
		return ton;
	}

	public int getPersonas() { // Wmc +1
		return personas; 
	}
	
}
