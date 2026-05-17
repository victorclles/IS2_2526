package es.unican.is2.TransportesRefactorizado;

import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI { // Wmc = 16, Ccog = 8
	private static final int ANHADE_CONDUCT = 0;
	private static final int ANHADE_TRANSP = 1;
	private static final int SUELDO_CONDUCT = 2;
	private static final int MEJOR_CONDUCT = 3;

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { // Wmc + 1
		// opciones del menu
		final int ANHADE_CONDUCTOR = ANHADE_CONDUCT, ANHADE_TRANSPORTE = ANHADE_TRANSP, 
		SUELDO_CONDUCTOR = SUELDO_CONDUCT, MEJOR_CONDUCTOR = MEJOR_CONDUCT;

		// crea la empresa de transportes
		GestionTransportes gt = new GestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) { // Wmc + 1, Ccog +1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {// Ccog +2
			case  ANHADE_CONDUCTOR: // Wmc + 1
				anhadeConductor(gt);
				break;

			case ANHADE_TRANSPORTE: // Wmc + 1
				anhadeTransporte(gt);
				break;
				
			case SUELDO_CONDUCTOR: // Wmc + 1
				sueldoConductor(gt);
 				break;

			case MEJOR_CONDUCTOR: // Wmc + 1
				mejorConductor(gt);
				break;
			}
		}
	}


	private static void mejorConductor(GestionTransportes gt) { // Wmc + 1
		List<Conductor> resultado = gt.mejorConductor();		
		String msj = "";
		for (Conductor conductor : resultado) { // Wmc + 1, Ccog +1
			msj += conductor.getNombre() + " "+conductor.getNombre()+"\n";
		}
		
		if (resultado.size() == 0) { // Wmc + 1, Ccog +1
			msj = "No hay conductores";
		}
		mensaje("MEJOR CONDUCTOR", msj);
	}


	private static void sueldoConductor(GestionTransportes gt) { // Wmc + 1
		String dni;
		Lectura lect;
		Conductor c;
		lect = new Lectura("Transportes Peligrosos");
		lect.creaEntrada("DNI", "");
		lect.esperaYCierra();
		dni = lect.leeString("DNI");
		c = gt.buscaConductor(dni);
		if (c!=null){ // Wmc + 1, Ccog +1
			mensaje("Sueldo", "El sueldo del conductor es: "+c.sueldo());
		} else {
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}


	private static void anhadeTransporte(GestionTransportes gt) { // Wmc + 1
		String dni;
		Lectura lect;
		Conductor c;
		lect = new Lectura("Nuevo transporte");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Tipo Transporte: P | M | MP", "");
		lect.creaEntrada("Horas", 0);
		lect.creaEntrada("Personas", 0);
		lect.creaEntrada("Toneladas", 0);
		lect.esperaYCierra();
		dni = lect.leeString("DNI");
		String tipo = lect.leeString("Tipo Transporte: P | M | MP");
		int horas = lect.leeInt("Horas");
		int personas = lect.leeInt("Personas");
		int toneladas = lect.leeInt("Toneladas");

		c = gt.buscaConductor(dni);
		if (c!=null) { // Wmc + 1, Ccog +1
			gt.anhadirTransporteRefactor(c, tipo, horas, personas, toneladas);
		} else {
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}


	private static void anhadeConductor(GestionTransportes gt) { // Wmc + 1
		String dni;
		Lectura lect;
		lect = new Lectura("Datos Conductor");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Nombre","");
		lect.creaEntrada("Apellido1", "");
		lect.creaEntrada("Apellido2", "");
		lect.creaEntrada("Direccion", "");
		lect.esperaYCierra();
		dni = lect.leeString("DNI");
		String nombre = lect.leeString("Nombre");
		String apellido1 = lect.leeString("Apellido1");
		String apellido2 = lect.leeString("Apellido2");
		String direccion = lect.leeString("Direccion");
		// Anhade el conductor
		if (!gt.anhadeConductor(new Conductor(dni, nombre, apellido1, apellido2, direccion)))  
			// Wmc + 1, Ccog +1
			mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
	}


	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { // Wmc + 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
