package tarefa7.exercicio12;

import java.time.LocalTime;

public class ContadoraAsync extends Thread{
    private final int max;
    private final Clock clock;

    public ContadoraAsync(int max){
        this.max = max;
        clock = Clock.getClock();
    }

    public void run(){
        for (int i =0; i< max; i++){
            clock.setTime(LocalTime.now());
            System.out.println(this.getName() + " contou "+ (i+1) + " em " + clock.getTime());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
