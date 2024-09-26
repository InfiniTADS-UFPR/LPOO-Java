package tarefa1;

import org.junit.Test;

import static org.junit.Assert.*;

public class calculaSalarioTest {

    @Test
    public void salarioBruto() {
        assertEquals(1600, calculaSalario.salarioBruto(10, 160), 0);
    }

    @Test (expected = RuntimeException.class)
    public void salarioBrutoException() {
        calculaSalario.salarioBruto(-10, 160);
    }

    @Test
    public void salarioLiquido() {
        assertEquals(1280, calculaSalario.salarioLiquido(10, 160, 20), 0);
    }

    @Test (expected = RuntimeException.class)
    public void salarioLiquidoException() {
        calculaSalario.salarioLiquido(10, 160, -20);
    }

    @Test
    public void imprimeResultados() {
        calculaSalario.salarioLiquido(10, 160, 20);
    }

    @Test (expected = RuntimeException.class)
    public void imprimeResultadosException() {
        calculaSalario.salarioLiquido(10, 160, -20);
    }
}