package tarefa3.exercicio5;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class UsaContato {
    public static void main(String[] args) {
        ContatoDAO dao = ContatoDAO.getContatoDAO();
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("|---------------------------------|");
            System.out.println("|              Menu               |");
            System.out.println("| 1 - Inserir novo contato        |");
            System.out.println("| 2 - Atualizar contato existente |");
            System.out.println("| 3 - Remover um contato          |");
            System.out.println("| 4 - Listar todos os contatos    |");
            System.out.println("| 5 - Sair                        |");
            System.out.println("|---------------------------------|");
            int optin = scn.nextInt();
            switch (optin) {
                case 1:
                    inserir(dao);
                    System.out.println();
                    break;
                case 2:
                    atualizar(dao);
                    System.out.println();
                    break;
                case 3:
                    remover(dao);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Contatos: ");
                    List<Contato> contatos = dao.lista();
                    contatos.forEach(System.out::println);
                    System.out.println();
                    break;
                case 5:
                    exit(0);
            }
        }
    }

    public static void inserir(ContatoDAO dao){
        Scanner scn = new Scanner(System.in);
        System.out.println("Informe o nome do contato: ");
        String nome = scn.nextLine();
        System.out.println("Informe o email do contato: ");
        String email = scn.nextLine();
        System.out.println("Informe a data de nascimento do contato: (yyyy-mm-dd)");
        String[] data = scn.nextLine().split("-");
        System.out.println("Informe o endereço do contato: ");
        String endereco = scn.nextLine();
        Contato contato = new Contato(-1, nome, LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])), email, endereco);
        dao.insere(contato);
    }

    private static void atualizar(ContatoDAO dao) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Digite o Id do contato para atualizar: ");
        int id = scn.nextInt();scn.nextLine();
        System.out.println("Informe o novo nome do contato: ");
        String nome = scn.nextLine();
        System.out.println("Informe o novo email do contato: ");
        String email = scn.nextLine();
        System.out.println("Informe a nova data de nascimento do contato: (yyyy-mm-dd)");
        String[] data = scn.nextLine().split("-");
        System.out.println("Informe o novo endereço do contato: ");
        String endereco = scn.nextLine();
        Contato contato = new Contato(id, nome, LocalDate.of(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])), email, endereco);
        dao.altera(contato);
    }

    public static void remover(ContatoDAO dao){
        Scanner scn = new Scanner(System.in);
        System.out.println("Digite o Id do contato para remover: ");
        int id = scn.nextInt();
        dao.remove(new Contato(id, "", LocalDate.now(), "", ""));
    }
}
