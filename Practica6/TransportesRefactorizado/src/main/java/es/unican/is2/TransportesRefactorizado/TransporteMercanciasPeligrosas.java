package es.unican.is2.TransportesRefactorizado;

public class TransporteMercanciasPeligrosas extends TransporteMercancias { // Wmc = 2, Ccog = 0
    private final static int EXTRA_EUROS_MERCANCIAS_PELIGROSAS = 50;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) { // Wmc + 1
        super(horas, toneladas);
    }

    @Override
    public double sueldoExtra() { // Wmc + 1
        return super.sueldoExtra() + EXTRA_EUROS_MERCANCIAS_PELIGROSAS;
    }
}
