package tarefa5.exercicio9;

class Bicicleta {

    int cadencia = 0;
    int velocidade = 0;
    int marcha = 1;

    void mudarCadencia(int novoValor) {
        cadencia = novoValor;
    }

    void mudarMarcha(int novoValor) {
        marcha = novoValor;
    }

    void aumentarVelocidade(int incremento) {
        velocidade = velocidade + incremento;
    }

    void aplicarFreios(int decremento) {
        velocidade = velocidade - decremento;
    }

    void printStates() {
        System.out.println("cadencia: " + cadencia + ", velocidade: " + velocidade +", marcha: "+marcha);
    }
}
