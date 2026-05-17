package es.unican.is2.TransportesRefactorizado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteTest {

    @Test
    public void testConstructor() {

        // Casos validos
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        assertEquals(1, sut.horas());
        assertEquals(1, sut.toneladasCarga());
        
        TransporteMercanciasPeligrosas sutp = new TransporteMercanciasPeligrosas(10, 1000);
        assertEquals(10, sutp.horas());
        assertEquals(1000, sutp.toneladasCarga());

        TransportePersonas sutpe = new TransportePersonas(10, 10);
        assertEquals(10, sutpe.horas());
        assertEquals(10, sutpe.personas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
    
        assertThrows(IllegalArgumentException.class, () -> {
            new TransportePersonas(-1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new TransportePersonas(1, -1);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new TransporteMercancias(-1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new TransporteMercancias(1, -1);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new TransporteMercanciasPeligrosas(-1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new TransporteMercanciasPeligrosas(1, -1);
        });
        
    }

}
