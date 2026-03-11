package es.unican.is2.SegurosCommon;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;

	private final static Double PRECIO_TODO_RIESGO = 1000.0;
	private final static Double PRECIO_TERCERO_LUNAS = 600.0;
	private final static Double PRECIO_TERCEROS = 400.0;
	private final static int LIMITE_POTENCIA_110 = 110;
	private final static int LIMITE_POTENCIA_90 = 90;
	private final static Double AUMENTO_POTENCIA_MAYOR_110 = 0.2;
	private final static Double AUMENTO_POTENCIA_MAYOR_IGUAL_90 = 0.05;
	private final static int NUM_PRIMER_ANHOS_PARA_DESCUENTO = 1;
	private final static Double DESCUENTO_PRIMER_ANHO = 0.2;
	public final static Double DESCUENTO_MINUSVALIA = 0.25;
	
	/**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
		if (getFechaInicio().isAfter(LocalDate.now())) {
			return 0;
		}
		
		double precio;
		
		switch (getCobertura()) {
		case TODO_RIESGO:
			precio = PRECIO_TODO_RIESGO;
			break;
		case TERCEROS_LUNAS:
			precio = PRECIO_TERCERO_LUNAS;
			break;
		case TERCEROS:
			precio = PRECIO_TERCEROS;
			break;
		default:
			throw new IllegalStateException("Cobertura case not covered: " + getCobertura().toString());
		}
		
		double aumentoPotencia = 0.0;
		if (getPotencia() > LIMITE_POTENCIA_110) {
			aumentoPotencia = precio * AUMENTO_POTENCIA_MAYOR_110;
		} else if (getPotencia() >= LIMITE_POTENCIA_90) {
			aumentoPotencia = precio * AUMENTO_POTENCIA_MAYOR_IGUAL_90;
		}
		precio += aumentoPotencia;
	
		double ofertaPrimerAnho = 0.0;
		if (getFechaInicio()
				.plus(Period.ofYears(NUM_PRIMER_ANHOS_PARA_DESCUENTO))
				.compareTo(LocalDate.now()) > 0
			) {
			ofertaPrimerAnho = precio * DESCUENTO_PRIMER_ANHO;
		}
		precio -= ofertaPrimerAnho;
		
		return precio;
	}
	
}
