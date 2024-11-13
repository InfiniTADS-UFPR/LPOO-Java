package tarefa6.mvc.controllers;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.models.connection.AutorDAO;
import tarefa6.mvc.models.connection.LivroDAO;
import tarefa6.mvc.views.livro.NewLivro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewLivroController {
    private final NewLivro view;
    private final Livro livro;
    private Autor autorEncontrado;
    private final AutorDAO autorDAO = new AutorDAO();
    private final LivroDAO livroDAO = new LivroDAO();

    public NewLivroController(NewLivro view){
        this.view = view;
        this.view.initView();
        this.livro = new Livro();
    }

    public void buscarAutor(String idAutor) {
        try {
            int id = Integer.parseInt(idAutor);
            this.autorEncontrado = autorDAO.consultarAutor(id);
            if (autorEncontrado == null) {
                throw new IllegalArgumentException("Autor não encontrado.");
            }
            view.showAutor(this.autorEncontrado);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException("ID do autor deve ser um número inteiro.");
        }
    }

    public void addAutor() {
        if (this.autorEncontrado == null) {
            throw new IllegalArgumentException("Autor vazio.");
        }
        this.livro.getAutores().add(this.autorEncontrado);
        view.updateAutores(String.join("; ", this.livro.getAutores().stream().map(Autor::getNome).toList()));
    }

    public void salvarLivro(String titulo, String assunto, String isbn, String publicacao) {
        this.livro.setTitulo(titulo);
        this.livro.setAssunto(assunto);
        this.livro.setIsbn(isbn);
        this.livro.setPublicacao(LocalDate.parse(publicacao, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        livroDAO.inserirLivro(this.livro);
    }
}
