package tarefa6.mvc.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Livro {

    private int id;
    private String titulo;
    private String isbn;
    private String assunto;
    private LocalDate publicacao;
    private final List<Autor> autores;

    public Livro(){
        this.autores = new ArrayList<>();
    }

    public Livro(String titulo,List<Autor> autores) {
        this.titulo = titulo;
        this.autores = new ArrayList<>();
        this.setAutores(autores);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public LocalDate getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(LocalDate publicacao) {
        this.publicacao = publicacao;
    }

    public void setAutores(List<Autor> autores) {
        for (Autor autor : autores) {
            this.adicionarAutor(autor);
        }
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public void adicionarAutor(Autor autor) {
        if (!this.getAutores().contains(autor)) {
            this.autores.add(autor);
            autor.adicionarLivro(this);
        }
    }

    public void removerAutor(Autor autor) {
        if (!this.getAutores().contains(autor)) {
            this.autores.remove(autor);
            autor.removerLivro(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
