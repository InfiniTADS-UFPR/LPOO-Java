package tarefa1;

public class calculaSalario {


    public static float salarioBruto(float valorHora, float horasTrabalhadas){
        try {
            if (valorHora < 0 || horasTrabalhadas < 0) throw new RuntimeException("Valor inválido");
            return valorHora * horasTrabalhadas;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static float salarioLiquido(float valorHora, float horasTrabalhadas, float descontoINSS){
        try {
            if (descontoINSS < 0) throw new RuntimeException("Valor inválido");
            return salarioBruto(valorHora, horasTrabalhadas) * (1 - descontoINSS/100);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static void imprimeResultados(float valorHora, float horasTrabalhadoas, float descontoINSS){
        try {
            System.out.println("Extrato de salário");
            System.out.println("Valor Hora: R$" + valorHora);
            System.out.println("Horas Trabalhadas: " + horasTrabalhadoas + " horas");
            System.out.println("Salário Bruto: " + salarioBruto(valorHora, horasTrabalhadoas));
            System.out.println("Salário Líquido: " + salarioLiquido(valorHora, horasTrabalhadoas, descontoINSS));
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
