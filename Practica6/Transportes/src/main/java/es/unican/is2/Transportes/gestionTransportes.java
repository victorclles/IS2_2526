package es.unican.is2.Transportes;
import java.util.ArrayList;
import java.util.List;

public class gestionTransportes { // Wmc = 6, CCog = 4

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) { // Wmc +1	
		for(Conductor c: cs) // Wmc +1, Ccog + 1
			if (c.dni().equals(DNI)) // Wmc +1, Ccog + 2
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		// Wmc +1
		if (buscaConductor(dni) != null) // Wmc +1, Ccog + 1
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() { // Wmc +1
		return cs;
	}
	
}
