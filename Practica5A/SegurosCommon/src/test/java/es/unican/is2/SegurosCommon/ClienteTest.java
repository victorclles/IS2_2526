package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.time.LocalDate;

/**
 * Unit test for simple App.
 */
public class ClienteTest 
{
    private Cliente cliente;

	@BeforeEach
	public void setUp() {
		cliente = new Cliente("12345678A", "NOMBRE", false);	
    }

    @Test
    public void testConstructor()
    {   
        assertDoesNotThrow(() -> { 
            Cliente cliente;           
            boolean minusvalia;

            String dni = "11111111A";
            String nombre = "Ramoncín";

            /// Valid DNI, no minusvalido, valid name
            minusvalia = false;
            cliente = new Cliente(dni, nombre, minusvalia);

            assertEquals(dni, cliente.getDni());
            assertEquals(nombre, cliente.getNombre());
            assertEquals(minusvalia, cliente.getMinusvalia());

            /// Valid DNI, minusvalido, valid name
            minusvalia = true;
            cliente = new Cliente(dni, nombre, minusvalia);

            assertEquals(dni, cliente.getDni());
            assertEquals(nombre, cliente.getNombre());
            assertEquals(minusvalia, cliente.getMinusvalia());
        });

        /// DNI is null
        assertThrows(NullPointerException.class, () -> {
            new Cliente(null, "Ramoncín", false);
        });

        /// DNI is larger than it should
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("1111111111", "Ramoncín", false);
        });

        /// DNI has letters instead of numbers
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("A11111111A", "Ramoncín", false);
        });

        /// DNI is shorter than it should
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("1111111A", "Ramoncín", false);
        });

        /// Name is null
        assertThrows(NullPointerException.class, () -> {
            new Cliente("11111111A", null, false);
        });
    }

    @Test
    public void testTotalSeguros()
    {
        assertDoesNotThrow(() -> {            
            /// Empty list
            cliente.setSeguros(Arrays.asList());
            assertEquals(0.0, cliente.totalSeguros());

            /// Single element list
            cliente.setSeguros(Arrays.asList(
                new Seguro(1, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), "")
            ));
            assertEquals(800.0, cliente.totalSeguros());

            /// Multiple elements, no minusvalido
            cliente.setSeguros(Arrays.asList(
                new Seguro(1, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), ""),
                new Seguro(2, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), ""),
                new Seguro(3, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), ""),
                new Seguro(4, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), ""),
                new Seguro(5, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), "")
            ));
            assertEquals(4000.0, cliente.totalSeguros());

            /// Multiple elements, minusvalido
            cliente.setMinusvalia(true);
            assertEquals(3000.0, cliente.totalSeguros());
        });

        /// List is null
        assertThrows(NullPointerException.class, () -> {
            cliente.setSeguros(null);
            cliente.totalSeguros();
        });

        /// List contains null
        assertThrows(NullPointerException.class, () -> {
            cliente.setSeguros(Arrays.asList(
                new Seguro(1, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), ""),
                null,
                new Seguro(3, "1234ABC", 1, Cobertura.TODO_RIESGO, LocalDate.now(), "")
            ));
            cliente.totalSeguros();
        });
    }
}
