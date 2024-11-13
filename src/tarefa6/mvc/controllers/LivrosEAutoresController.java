package tarefa6.mvc.controllers;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.models.connection.AutorDAO;
import tarefa6.mvc.models.connection.LivroDAO;
import tarefa6.mvc.views.LivrosEAutoresView;
import tarefa6.mvc.views.autor.AutorsTableModel;
import tarefa6.mvc.views.autor.DetalhesAutor;
import tarefa6.mvc.views.autor.NewAutor;
import tarefa6.mvc.views.livro.DetalheLivro;
import tarefa6.mvc.views.livro.LivrosTableModel;
import tarefa6.mvc.views.livro.NewLivro;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LivrosEAutoresController {
    private LivroDAO livroDAO = new LivroDAO();
    private AutorDAO autorDAO = new AutorDAO();
    private LivrosEAutoresView view;

    public LivrosEAutoresController(LivrosEAutoresView view){
        this.view = view;
        initView();
    }

    private void initView() {
        this.view.setController();
        loadLivros();
        loadAutores();
    }

    private void loadLivros(){
        try {
            this.view.loadLivros(this.livroDAO.listarLivroComAutores());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this.view.panel, "Ocorreu um erro ao carregar os livros: " + e.getMessage() );
            System.out.println(e);
        }
    }

    private void loadAutores(){
        try {
            this.view.loadAutores(this.autorDAO.listarAutoresComLivros());
        } catch (Exception e){
            JOptionPane.showMessageDialog(this.view.panel, "Ocorreu um erro ao carregar os autores: " + e.getMessage() );
            System.out.println(e);
        }
    }

    public void addAutor() {
        NewAutor newAutor = new NewAutor();
        newAutor.setVisible(true);
        newAutor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadAutores();
            }
        });
    }

    public void addLivro() {
        NewLivro newLivro = new NewLivro();
        newLivro.setVisible(true);
        newLivro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadLivros();
            }
        });
    }

    public void detalhesAutor(JTable tableAutores) {
        if (tableAutores.getSelectedRow() != -1) {
            AutorsTableModel model = (AutorsTableModel) tableAutores.getModel();
            Autor autor = model.getAutor(tableAutores.getSelectedRow());
            DetalhesAutor detalhesAutor = new DetalhesAutor(autor);
            detalhesAutor.setVisible(true);
        }
    }

    public void detalhesLivro(JTable tableLivros) {
        if (tableLivros.getSelectedRow() != -1) {
            LivrosTableModel model = (LivrosTableModel) tableLivros.getModel();
            Livro livro = model.getLivro(tableLivros.getSelectedRow());
            JOptionPane.showMessageDialog(this.view.panel, "Aguarde...");
            try {
                DetalheLivro detalheLivro = new DetalheLivro(livro);
                detalheLivro.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.view.panel, "Ocorreu um erro ao carregar os detalhes do livro: " + e.getMessage() );
                System.out.println(e);
            }
        }
    }
}
