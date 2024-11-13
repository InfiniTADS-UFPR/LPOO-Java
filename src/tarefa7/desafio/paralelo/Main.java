package tarefa7.desafio.paralelo;

import tarefa7.desafio.Matriz;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int tamanho = 2000;
        LocalDateTime inicio;
        LocalDateTime fim;

        System.out.printf("Matrizes de tamanho %d x %d\n", tamanho, tamanho);
        System.out.println("Quantas threads você pretende usar?");
        int threads = scn.nextInt();
        Orchestrator calc = new Orchestrator(threads);

        inicio = LocalDateTime.now();
        Matriz m = new Matriz(tamanho, tamanho);
        Matriz m2 = new Matriz(tamanho, tamanho);
        fim = LocalDateTime.now();
        System.out.println("Inicialização das matrizes: "+ Duration.between(inicio, fim).toMillis()+"ms");

        inicio = LocalDateTime.now();
        Matriz m3 = calc.multiply(m, m2);
        fim = LocalDateTime.now();
        System.out.println("Multiplicação das matrizes: "+ Duration.between(inicio, fim).toSeconds()+"s");
    }
}
