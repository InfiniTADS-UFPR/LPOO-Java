package tarefa1;

import org.junit.*;

public class calculaAreaTest {
    @Test
    public void calculaAreaRetanguloPositivo() {
        Assert.assertEquals(6, calculaArea.calculaAreaRetangulo(2, 3), 0);
    }

    @Test(expected = RuntimeException.class)
    public void calculaRetanguloNegativo() {
        calculaArea.calculaAreaRetangulo(-2, 3);
    }

    @Test
    public void calculaAreaTrianguloPositivo() {
        Assert.assertEquals(3, calculaArea.calculaAreaTriangulo(2, 3), 0);
    }

    @Test(expected = RuntimeException.class)
    public void calculaTrianguloNegativo() {
        calculaArea.calculaAreaTriangulo(-2, 3);
    }

    @Test
    public void calculaCircunferenciaPositivo() {
        Assert.assertEquals(12.566370614359172, calculaArea.calculaCircunferencia(2), 0);
    }

    @Test(expected = RuntimeException.class)
    public void calculaCircunferenciaNegativo() {
        calculaArea.calculaCircunferencia(-2);
    }
}