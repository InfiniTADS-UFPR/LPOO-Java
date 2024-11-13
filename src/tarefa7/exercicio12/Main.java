package tarefa7.exercicio12;

public class Main {
    public static void main(String[] args) {
        ContadoraAsync c1 = new ContadoraAsync(10);
        ContadoraAsync c2 = new ContadoraAsync(20);

        c1.start();
        c2.start();
    }
}