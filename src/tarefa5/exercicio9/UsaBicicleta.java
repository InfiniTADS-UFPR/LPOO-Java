package tarefa5.exercicio9;

public class UsaBicicleta {
        
    public static void main(String[] args){
        Bicicleta bicicleta = new Bicicleta(){
           @Override
           public void aplicarFreios(int decremento){
               velocidade = velocidade - 2*decremento;
           }
        };
        
        System.out.println("Bicicleta alterada:");
        bicicleta.aumentarVelocidade(20);
        
        bicicleta.printStates();
        
        bicicleta.aplicarFreios(10);
        bicicleta.printStates();
        
        Bicicleta bicicleta1 = new Bicicleta();
        System.out.println("Bicicleta normal:");
        bicicleta1.aumentarVelocidade(20);
        bicicleta1.printStates();
        bicicleta1.aplicarFreios(10);
        bicicleta1.printStates();
        
    }
    
}
