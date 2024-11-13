package tarefa7.exercicio12;

import java.time.LocalTime;

public class Clock {
    private static Clock instance;
    private boolean flag = false;
    private LocalTime hora;

    private Clock(){}

    public static Clock getClock(){
        if(instance == null){
            instance = new Clock();
        }
        return instance;
    }

    public synchronized void setTime(LocalTime time){
        while (flag) {
            try{ wait(); }
            catch (InterruptedException ignored) {}
        }
        hora = time;
        flag = true;
        notifyAll();
    }

    public synchronized String getTime() {
        while (!flag) {
            try{ wait(); }
            catch (InterruptedException ignored) {}
        }
        flag = false;
        notifyAll();
        return hora.getMinute() + "m" + hora.getSecond()+"s";
    }
}
