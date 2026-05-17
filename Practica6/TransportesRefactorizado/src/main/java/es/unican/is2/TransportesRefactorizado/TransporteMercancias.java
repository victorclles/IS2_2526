package es.unican.is2.TransportesRefactorizado;

public class TransporteMercancias extends Transporte { // Wmc = 5, Ccog = 2
    private int carga;

	private final static int EXTRA_EUROS_POR_TONELADA_MERCANCIAS = 2;

    /**
	 * Constructor de la clase TransporteMercancias
	 * @param horas Horas que ha durado el transporte
	 * @param toneladas Toneladas de carga
	 */ 
	public TransporteMercancias(double horas, int toneladas) throws IllegalArgumentException { // Wmc + 1
		if (horas <= 0 || toneladas <= 0) { // Wmc + 2, Ccog + 2
			throw new IllegalArgumentException();
		}
        this.horas = horas;
		this.carga = toneladas;
	}

    /**
     * Devuelve las toneladas de carga en el transporte
     * @return toneladas de carga en el transporte
     */
    public int toneladasCarga() { // Wmc + 1
		return carga;
	}

    @Override
    public double sueldoExtra() { // Wmc + 1
        return this.carga * EXTRA_EUROS_POR_TONELADA_MERCANCIAS;
    }
}
