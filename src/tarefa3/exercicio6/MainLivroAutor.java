package tarefa3.exercicio6;

import tarefa3.conexao.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainLivroAutor {
    
    private final AutorDAO autorDAO;
    private final LivroDAO livroDAO;

    public MainLivroAutor() {
        autorDAO = new AutorDAO();
        livroDAO = new LivroDAO();
    }

    public static void main(String[] args) {
        MainLivroAutor main = new MainLivroAutor();
        String opcao = "";
        while (true) {
            try{
            System.out.println("Escolha uma das opções e tecle <ENTER>: ");
            System.out.println("  1 - Incluir Autor");
            System.out.println("  2 - Incluir Livro");
            System.out.println("  3 - Listar Autores");
            System.out.println("  4 - Listar Livro com autores");
            System.out.println("  5 - Listar Autores de um livro");
            System.out.println("  6 - Listar Livros de um autor");
            System.out.println("  7 - Sair");
            Scanner sc = new Scanner(System.in,"ISO-8859-1");
            opcao = sc.nextLine();
            switch(opcao){
                case "1":
                    main.incluirAutor();
                    break;
                case "2":
                    main.incluirLivro();
                    break;
                case "3":
                    main.listarAutores();
                    break;
                case "4":
                    main.listarLivroComAutores();
                    break;
                case "5":
                    main.listarAutoresPorLivro();
                    break;
                case "6":
                    main.listarLivrosPorAutor();
                    break;
                case "7":
                    System.out.println("Você escolheu sair :D");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            if(opcao.equals("7")){
                break;
            }
            }catch(Exception ex){
                System.out.println("Falha na operação. Mensagem: "+ ex.getMessage());
            }
        }
    }
    public void incluirAutor(){
        System.out.print("Digite o nome do autor: ");
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String nome = sc.nextLine();
        Autor autor = new Autor(nome);

        int numLivros=1;
        List<Livro> listaLivros = new ArrayList<>();
        int idLivro;
        System.out.println("Informe os IDs dos livros ou -1 para terminar: ");
        do{
            try{
                Scanner sc2 = new Scanner(System.in,"ISO-8859-1");
                System.out.print("ID Livro "+numLivros+":");
                idLivro = sc2.nextInt();
                if(idLivro==-1)
                    break;
                Livro livro = livroDAO.consultarLivro(idLivro);
                if(livro != null){
                    listaLivros.add(livro);
                    numLivros++;
                }else{
                    System.out.println("Livro não existe!");
                }
            }
            catch(Exception ex){
                System.out.println("ID livro não é inteiro ou inválido!");
            }
        }while(true);
        autorDAO.inserirAutor(autor, listaLivros);
    }

    public void incluirLivro() {
        System.out.print("Digite o título do livro: ");
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String titulo = sc.nextLine();
        int numAutores=1;
        List<Autor> listaAutores = new ArrayList<>();
        int idAutor=0;
        System.out.println("Informe os IDs dos autores ou -1 para terminar: ");
        do{
            try{
                Scanner sc2 = new Scanner(System.in,"ISO-8859-1");
                System.out.print("ID Autor "+numAutores+":");
                idAutor = sc2.nextInt();
                if(idAutor==-1)
                    break;
                Autor autor = autorDAO.consultarAutor(idAutor);
                if(autor != null){
                    listaAutores.add(autor);
                    numAutores++;
                }else{
                    System.out.println("Autor não existe!");
                }
            }
            catch(Exception ex){
                System.out.println("ID autor não é inteiro ou inválido!");
            }
        }while(true);

        Livro livro = new Livro(titulo,listaAutores);
        livroDAO.inserirLivro(livro);
    }

    public void listarAutores() throws Exception{
        List<Autor> listaAutores = autorDAO.listarAutores();
        listaAutores.sort((autor0, autor1) -> autor0.getNome().compareToIgnoreCase(autor1.getNome()));
        System.out.println("ID\tNOME");
        listaAutores.forEach(a -> System.out.println(String.format("%2d\t", a.getId()) + a.getNome()));
    }

    public void listarLivroComAutores() throws Exception{
        List<Livro> listaLivros = livroDAO.listarLivroComAutores();
        Collections.sort(listaLivros, new Comparator<Livro>() {
           public int compare(Livro arg0, Livro arg1) {
               String titulo = arg0.getTitulo();
               int i = titulo.compareToIgnoreCase(arg1.getTitulo());
               return i;
             }
        });
        System.out.println("ID\tTitulo do Livro\tAutores");
        for(Livro livro:listaLivros){
            System.out.print(livro.getId()+"\t"+livro.getTitulo()+"\t");
            for(Autor autor:livro.getAutores()){
                System.out.print(autor.getNome()+";");
            }
            System.out.print("\n");
        }
    }

    public void listarAutoresPorLivro() throws SQLException, IOException {
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        System.out.println("Digite o id do livro: ");
        int id = sc.nextInt();
        Livro livro = livroDAO.consultarLivro(id);
        List<Autor> autores = livroDAO.lerAutores(id, Database.getConnection());
        System.out.println("Autores de "+livro.getTitulo());
        autores.forEach(au -> System.out.println(au.getNome()));
    }

    public void listarLivrosPorAutor(){
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        System.out.println("Digite o id do autor: ");
        int id = sc.nextInt();
        Autor autor = autorDAO.consultarAutor(id);
        List<Livro> livros = autorDAO.lerLivros(id);
        System.out.println("Livros de "+ autor.getNome());
        livros.forEach(li -> System.out.println(li.getTitulo()));
    }
}
