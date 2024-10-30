package tarefa2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsaFormas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Object> formas = new ArrayList<>();

        System.out.print("Quantas formas deseja criar? ");
        int quantidade = scanner.nextInt();

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o tipo de forma (1: Circunferencia, 2: Retangulo, 3: Triangulo): ");
            int tipo = scanner.nextInt();

            switch (tipo) {
                case 1:
                    System.out.print("Digite o raio: ");
                    double raio = scanner.nextDouble();
                    formas.add(new Circunferencia(raio));
                    break;
                case 2:
                    System.out.print("Digite o lado1: ");
                    double lado1 = scanner.nextDouble();
                    System.out.print("Digite o lado2: ");
                    double lado2 = scanner.nextDouble();
                    formas.add(new Retangulo(lado1, lado2));
                    break;
                case 3:
                    System.out.print("Digite a base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Digite a altura: ");
                    double altura = scanner.nextDouble();
                    formas.add(new Triangulo(base, altura));
                    break;
                default:
                    System.out.println("Tipo de forma inválido.");
                    i--;
                    break;
            }
            if(i < quantidade - 1){
                System.out.print("Deseja adicionar mais formas? (s/n): ");
                String resposta = scanner.next();
                if (resposta.equalsIgnoreCase("n")) {
                    break;
                }
            }
        }

        for (Object forma : formas) {
            if (forma instanceof Circunferencia) {
                System.out.println("Área da Circunferência: " + ((Circunferencia) forma).area());
            } else if (forma instanceof Retangulo) {
                System.out.println("Área do Retângulo: " + ((Retangulo) forma).area());
                System.out.println("Perímetro do Retângulo: " + ((Retangulo) forma).perimetro());
            } else if (forma instanceof Triangulo) {
                System.out.println("Área do Triângulo: " + ((Triangulo) forma).area());
            }
        }

        scanner.close();
    }
}