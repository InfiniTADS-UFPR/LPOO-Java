package tarefa2;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormasTest {

    @Test
    public void testCircunferenciaArea() {
        Circunferencia c = new Circunferencia(5);
        assertEquals(78.5398, c.area(), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testCircunferenciaException() {
        new Circunferencia(-5);
    }

    @Test
    public void testRetanguloArea() {
        Retangulo r = new Retangulo(4, 5);
        assertEquals(20, r.area(), 0);
    }

    @Test
    public void testRetanguloPerimetro() {
        Retangulo r = new Retangulo(4, 5);
        assertEquals(18, r.perimetro(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testRetanguloException() {
        new Retangulo(-4, 5);
    }

    @Test
    public void testTrianguloArea() {
        Triangulo t = new Triangulo(4, 5);
        assertEquals(10, t.area(), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testTrianguloException() {
        new Triangulo(-4, 5);
    }
}