package es.unican.is2.TransportesRefactorizado;

public class TransportePersonas extends Transporte { // Wmc = 7, Ccog = 3
    private int personas;
    
	private final static int MAX_PERSONAS_TRANSPORTE_COLECTIVO = 9;

	private final static double EXTRA_EUROS_POR_HORA_TRANSPORTE_NO_COLECTIVO = 0.5;
	private final static double EXTRA_EUROS_POR_HORA_TRANSPORTE_COLECTIVO = 1;

    /**
	 * Constructor de la clase TransportePersonas
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param personas Personas en el transporte
	 */ 
	public TransportePersonas(double horas, int personas) throws IllegalArgumentException { // Wmc + 1
		if (horas <= 0 || personas <= 0) { // Wmc + 2, Ccog + 2
			throw new IllegalArgumentException();
		}
        this.horas = horas;
		this.personas = personas;
	}

	/**
	 * Devuelve el numero de personas en el transporte
	 * @return el numero de personas en el transporte
	 */
    public int personas() { // Wmc + 1
		return personas;
	}

	/**
	 * Devuelve si el transporte es colectivo
	 * @return si el transporte es colectivo
	 */
	public boolean esColectivo() { // Wmc + 1
		return personas() > MAX_PERSONAS_TRANSPORTE_COLECTIVO;
	}

	@Override
	public double sueldoExtra() { // Wmc + 1
		double out = this.horas;

		if (esColectivo())  // Wmc + 1, Ccog + 1
			out *= EXTRA_EUROS_POR_HORA_TRANSPORTE_COLECTIVO;
		else 
			out *= EXTRA_EUROS_POR_HORA_TRANSPORTE_NO_COLECTIVO;

		return out;
	}
}
