package es.unican.is2.SegurosGUI;


import java.sql.Connection;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import es.unican.is2.SegurosBusiness.GestionSeguros;
import es.unican.is2.SegurosCommon.IClientesDAO;
import es.unican.is2.SegurosCommon.ISegurosDAO;
import es.unican.is2.SegurosDAOH2.ClientesDAO;
import es.unican.is2.SegurosDAOH2.H2ServerConnectionManager;
import es.unican.is2.SegurosDAOH2.SegurosDAO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VistaAgenteTestIT {
    private FrameFixture demo;
	private static Connection con;

    @BeforeAll
    public static void setUpAll() {
        try {
            con = H2ServerConnectionManager.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@BeforeEach
	public void setUp() {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
    @Test
    @Order(1)
    public void testValidoComplejo() {
        demo.textBox("txtDNICliente").enterText("11111111A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Juan");
        demo.textBox("txtTotalCliente").requireText("1820.0");
    }

	@Test
    @Order(2)
	public void testValidoTrivial() {
		demo.textBox("txtDNICliente").enterText("33333333A");
		demo.button("btnBuscar").click();		
		// Comprobamos la salida
        demo.textBox("txtNombreCliente").requireText("Luis");
		demo.textBox("txtTotalCliente").requireText("0.0");
	}

    @Test
    @Order(3)
    public void testError() {
        demo.textBox("txtDNICliente").enterText("12345678A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
    }

    @Test
    @Order(~(1 << 31))
    public void testErrorDB() {
        try {
            con.close();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
        demo.textBox("txtDNICliente").enterText("12345678A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
    }
}
