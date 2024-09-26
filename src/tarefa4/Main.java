package tarefa4;

public class Main {
    public static void main(String[] args) {

        Integer[] arrayI = {-3, -2, 1, 0, 2, 4, 3, 0};
        Double[] arrayD = {0.0, 0.5, 1.0, 2.0, 1.5, -0.5};
        System.out.println("Integer" + Genericos.arrayToString(arrayI));
        System.out.println("Maior Integer: " + Genericos.maior(arrayI));
        System.out.println("Double" + Genericos.arrayToString(arrayD));
        System.out.println("Maior Double : " + Genericos.maior(arrayD));
    }
}