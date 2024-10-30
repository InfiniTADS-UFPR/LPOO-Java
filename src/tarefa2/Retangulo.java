package tarefa2;

public class Retangulo {
    private double lado1;
    private double lado2;

    public Retangulo(double lado1, double lado2) {
        if (lado1 < 0 || lado2 < 0) {
            throw new RuntimeException("Os lados não podem ser negativos.");
        }
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public double area() {
        return lado1 * lado2;
    }

    public double perimetro() {
        return 2 * (lado1 + lado2);
    }
}