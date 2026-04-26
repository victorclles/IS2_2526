package es.unican.is2.SegurosCommon;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class SeguroTest 
{
	@BeforeEach
	public void setUp() {
    }

    @Test
    public void testConstructor()
    {   
        assertDoesNotThrow(() -> { 
            new Seguro(
                0,
                "1111AAA", 
                0,
                Cobertura.TODO_RIESGO,
                LocalDate.now(),
                null
            );

            new Seguro(
                0,
                "1111AAA", 
                120,
                Cobertura.TERCEROS_LUNAS,
                LocalDate.now().plusYears(5),
                null    
            );

            new Seguro(
                0,
                "1111AAA", 
                0,
                Cobertura.TERCEROS,
                LocalDate.now(),
                null
            );
        });

        /// Cobertura is null
        assertThrows(NullPointerException.class, () -> {
            new Seguro(0, "1111AAA", 0, null, LocalDate.now(), null);
        });

        /// Matricula is null
        assertThrows(NullPointerException.class, () -> {
            new Seguro(0, null, 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Matricula only numbers
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111222", 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Matricula only letters
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "AAAABBB", 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Matricula wrong format 
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "111AAAA", 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Matricula shorter than it should be
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111AA", 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Matricula empty
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "", 0, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Potencia negative
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111AAA", -1, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Potencia negative 
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111AAA", -123, Cobertura.TODO_RIESGO, LocalDate.now(), null);
        });

        /// Fecha is null
        assertThrows(NullPointerException.class, () -> {
            new Seguro(0, "1111AAA", 0, Cobertura.TODO_RIESGO, null, null);
        });

        /// Fecha yesterday
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111AAA", -1, Cobertura.TODO_RIESGO, LocalDate.now().minusDays(1), null);
        });

        /// Fecha 10 years ago
        assertThrows(IllegalArgumentException.class, () -> {
            new Seguro(0, "1111AAA", -1, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(10), null);
        });

        /// Fecha is birth of Jesus
        assertThrows(DateTimeException.class, () -> {
            new Seguro(0, "1111AAA", -1, Cobertura.TODO_RIESGO, LocalDate.of(0, 0, 0), null);
        });
    }

    @Test
    public void testPrecio() {
        assertDoesNotThrow(() -> {
            Seguro seguro = new Seguro(
                0,
                "1111AAA", 
                0,
                Cobertura.TODO_RIESGO,
                LocalDate.now(),
                ""
            );
            assertEquals(800.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                50,
                Cobertura.TODO_RIESGO,
                LocalDate.now(),
                ""
            );
            assertEquals(800.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                89,
                Cobertura.TODO_RIESGO,
                LocalDate.now(),
                ""
            );
            assertEquals(800.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                111,
                Cobertura.TERCEROS_LUNAS,
                LocalDate.now().plusYears(5),
                ""
            );
            seguro.setFechaInicio(LocalDate.now().minusYears(1).minusDays(1));
            assertEquals(720.0, seguro.precio());
            
            seguro = new Seguro(
                0,
                "1111AAA", 
                676,
                Cobertura.TERCEROS_LUNAS,
                LocalDate.now().plusYears(5),
                ""
            );
            seguro.setFechaInicio(LocalDate.now().minusYears(2));
            assertEquals(720.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                ~(1 << 31),
                Cobertura.TERCEROS_LUNAS,
                LocalDate.now().plusYears(5),
                ""
            );
            seguro.setFechaInicio(LocalDate.MIN);
            assertEquals(720.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                90,
                Cobertura.TERCEROS,
                LocalDate.now(),
                ""
            );
            assertEquals(336.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                100,
                Cobertura.TERCEROS,
                LocalDate.now(),
                ""
            );
            seguro.setFechaInicio(LocalDate.now().minusMonths(6));
            assertEquals(336.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                110,
                Cobertura.TERCEROS,
                LocalDate.now(),
                ""
            );
            seguro.setFechaInicio(LocalDate.now().minusYears(1).plusDays(1));
            assertEquals(336.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                110,
                Cobertura.TERCEROS,
                LocalDate.now().plusYears(1),
                ""
            );
            assertEquals(0.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                110,
                Cobertura.TERCEROS,
                LocalDate.now().plusYears(3),
                ""
            );
            assertEquals(0.0, seguro.precio());

            seguro = new Seguro(
                0,
                "1111AAA", 
                110,
                Cobertura.TERCEROS,
                LocalDate.MAX,
                ""
            );
            assertEquals(0.0, seguro.precio());
        });
    }
}