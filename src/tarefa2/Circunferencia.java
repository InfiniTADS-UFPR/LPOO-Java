package tarefa2;

public class Circunferencia {
    private double raio;

    public Circunferencia(double raio) {
        if (raio < 0) {
            throw new RuntimeException("O raio não pode ser negativo.");
        }
        this.raio = raio;
    }

    public double area() {
        return Math.PI * raio * raio;
    }
}