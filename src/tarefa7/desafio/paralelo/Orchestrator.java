package tarefa7.desafio.paralelo;

import tarefa7.desafio.Matriz;

public class Orchestrator {
    private int turn = 0;
    private final int threads;
    private final Matriz result = new Matriz();

    public Orchestrator(int threads){
        this.threads = threads;
    }

    public Matriz multiply(Matriz a, Matriz b){
        Thread[] calculators = new Thread[threads];
        if (a.getY() != b.getX()) {
            throw new RuntimeException("Não é possível multiplicar");
        }

        int size = a.getX()/threads;
        if (size <= 0) size = 1;
        for (int i=0; i<threads; i++){
            int start = i*size;
            int end = (size*(i+1));
            if (end > a.getX()){
                break;
            }
            calculators[i] = new CalculatorAsync(a.subMatriz(start, end), b, i, this);
            calculators[i].start();
        }

        for (Thread t : calculators){
            try{
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public synchronized void appendResult(Matriz sub, int id){
        while (turn != id) {
            try{ wait(); }
            catch (InterruptedException ignored) {}
        }
        this.result.join(sub);
        this.turn++;
        notifyAll();
    }

}
