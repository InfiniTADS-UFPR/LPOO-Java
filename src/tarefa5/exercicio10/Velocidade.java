package tarefa5.exercicio10;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Velocidade {
    public static void main(String[] args){
        String tempo="";
        String distancia="";
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Informe o tempo em horas: ");
        tempo = scan.next();
        System.out.print("Informe a distância em km: ");
        distancia = scan.next();
        
        double temp = Double.parseDouble(tempo);
        double dist = Double.parseDouble(distancia);
        
        BigDecimal bdtempo = BigDecimal.valueOf(temp);
        BigDecimal bddist = BigDecimal.valueOf(dist);
        
        BigDecimal velocidade = bddist.divide(bdtempo,3,RoundingMode.CEILING);
        
        System.out.println("Velocidade do automovel é: "+velocidade+"Km/h");
    }
    
}
