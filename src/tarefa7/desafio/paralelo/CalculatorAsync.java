package tarefa7.desafio.paralelo;

import tarefa7.desafio.Matriz;

import java.util.List;
import java.util.stream.IntStream;

public class CalculatorAsync extends Thread{
    private int id;
    private Orchestrator orchestrator;
    private Matriz a;
    private Matriz b;
    private Matriz c;

    public CalculatorAsync(Matriz a, Matriz b, int id, Orchestrator orchestrator){
        this.id = id;
        this.orchestrator = orchestrator;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        c = multiply(a, b);
        this.orchestrator.appendResult(c, this.id);
    }

    public Matriz multiply(Matriz a, Matriz b) {
        if (a.getY() != b.getX()) {
            throw new RuntimeException("Não é possível multiplicar");
        }
        Matriz c = new Matriz(a.getX(), b.getY());
        for (int i = 0; i < a.getX();i++){
            for(int j =0;j<b.getY();j++){
                c.setValue(i,j, calcValue(a.getRow(i), b.getColumn(j)));
            }
        }
        return c;
    }

    private Long calcValue(List<Long> a, List<Long> b) {
        List<Long> c = IntStream.range(0, a.size())
                .mapToObj(i -> a.get(i) * b.get(i))
                .toList();
        return c.stream().reduce(0L, Long::sum);
    }
}
