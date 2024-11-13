package tarefa6.mvc.controllers;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.models.connection.AutorDAO;
import tarefa6.mvc.models.connection.LivroDAO;
import tarefa6.mvc.views.autor.NewAutor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewAutorController {
    private final NewAutor view;
    private final Autor autor;
    private Livro livroEncontrado;
    private final AutorDAO autorDAO = new AutorDAO();
    private final LivroDAO livroDAO = new LivroDAO();

    public NewAutorController(NewAutor view) {
        this.view = view;
        this.view.initView();
        this.autor = new Autor();
    }

    public void buscarLivro(String idLivro) {
        try {
            int id = Integer.parseInt(idLivro);
            this.livroEncontrado = livroDAO.consultarLivro(id);
            if (livroEncontrado == null) {
                throw new IllegalArgumentException("Livro não encontrado.");
            }
            view.showLivro(this.livroEncontrado);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("ID do livro deve ser um número inteiro.");
        }
    }

    public void addLivro() {
        if (this.livroEncontrado == null) {
            throw new IllegalArgumentException("Livro vazio.");
        }
        this.autor.getLivros().add(this.livroEncontrado);
        view.updateLivros(String.join("; ", this.autor.getLivros().stream().map(Livro::getTitulo).toList()));
    }

    public void salvarAutor(String nome, String documento, String naturalidade, String nascimento) {
        this.autor.setNome(nome);
        this.autor.setDocumento(documento);
        this.autor.setNaturalidade(naturalidade);
        this.autor.setNascimento(LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        autorDAO.inserirAutor(this.autor);
    }
}
