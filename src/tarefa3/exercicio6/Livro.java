package tarefa3.exercicio6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Livro {

    private int id;
    private String titulo;
    private List<Autor> autores;

    public Livro(String titulo,List<Autor> autores) {
        this.titulo = titulo;
        this.autores = new ArrayList();
        this.setAutores(autores);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutores(List<Autor> autores) {
        Iterator iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor autor = (Autor) iterator.next();
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
