package tarefa1;

public class calculaArea {

    public static double calculaAreaRetangulo(double base, double altura) {
        try {
            if (base <= 0 || altura <= 0) {
                throw new RuntimeException("Base ou altura não podem ser negativas");
            }
            return base * altura;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static double calculaAreaTriangulo(double base, double altura) {
        try {
            if (base <= 0 || altura <= 0) {
                throw new RuntimeException("Base ou altura não podem ser negativas");
            }
            return base * altura / 2;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static double calculaCircunferencia(double raio) {
        try {
            if (raio <= 0) {
                throw new RuntimeException("Raio não pode ser negativo");
            }
            return 2 * Math.PI * raio;
        } catch (RuntimeException e) {
            throw e;
        }
    }


}
