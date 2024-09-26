package tarefa4;

public class Genericos {
    public static <T extends Comparable<T>> T maior(T[] array) {
        T maior = array[0];
        for (T elemento : array) {
            if (maior.compareTo(elemento) < 0) {
                maior = elemento;
            }
        }
        return maior;
    }
    public static <T> String arrayToString(T[] array) {
        StringBuilder sb = new StringBuilder("[ ");
        for (T elemento: array) {
            sb.append(elemento).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}