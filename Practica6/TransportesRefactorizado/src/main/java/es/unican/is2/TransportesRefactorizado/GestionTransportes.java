package es.unican.is2.TransportesRefactorizado;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GestionTransportes { // Wmc = 14, CCog = 9

	private static List<Conductor> conductores = new ArrayList<Conductor>();

	public Conductor buscaConductor(String DNI) { // Wmc + 1		
		for(Conductor c: conductores) // Wmc + 1, CCog +1
			if (c.getDni().equals(DNI)) // Wmc + 1, CCog +2
				return c;

		return null;
	}

	public boolean anhadeConductor(Conductor conductor) { // Wmc + 1
		if (buscaConductor(conductor.getDni()) != null) // Wmc + 1, CCog +1
			return false;
		conductores.add(conductor);
		return true;
	}

	public List<Conductor> conductores() { // Wmc + 1
		return conductores;
	}

	public List<Conductor> mejorConductor() { // Wmc + 1
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor conductor : conductores) { // Wmc + 1, CCog +1
			if (conductor.sueldo() > maxSueldo) { // Wmc + 1, CCog +2
				maxSueldo = conductor.sueldo();
				resultado.clear();
				resultado.add(conductor);
			} else if (conductor.sueldo() == maxSueldo) { // Wmc + 1, CCog +1
				resultado.add(conductor);
			}
		}
		return resultado;
	}

	public void anhadirTransporteRefactor(Conductor c, String tipo, int horas, int personas, int toneladas) {
		// Wmc + 1
		Transporte t = null;
		switch (tipo) { //, CCog +1
		case "P": // Wmc + 1
			t = new TransportePersonas(horas, personas);
			break;
		case "M": // Wmc + 1
			t = new TransporteMercancias(horas, toneladas);
			break;
		case "MP": // Wmc + 1
			t = new TransporteMercanciasPeligrosas(horas, toneladas);
			break;		
		}
		c.anhadeTransporte(t);
	}

}
