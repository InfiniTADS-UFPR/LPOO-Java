package tarefa6.mvc.views;

import tarefa6.mvc.controllers.LivrosEAutoresController;
import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.views.autor.AutorsTableModel;
import tarefa6.mvc.views.livro.LivrosTableModel;

import javax.swing.*;
import java.util.List;

public class LivrosEAutoresView {
    public JPanel panel;
    private JTabbedPane tabbedPane1;
    private JPanel autorsPanel;
    private JPanel livrosPanel;
    private JTable tableAutores;
    private JTable tableLivros;
    private JButton addAutor;
    private JButton addLivro;
    private LivrosEAutoresController controller;

    private final AutorsTableModel autorsTableModel = new AutorsTableModel();
    private final LivrosTableModel livrosTableModel = new LivrosTableModel();

    public LivrosEAutoresView() {
        this.tableAutores.setModel(autorsTableModel);
        this.tableLivros.setModel(livrosTableModel);
        this.controller = new LivrosEAutoresController(this);
    }

    public void loadAutores(List<Autor> lista) {
        this.autorsTableModel.updateAutores(lista);
    }

    public void loadLivros(List<Livro> lista) {
        this.livrosTableModel.updateLivros(lista);
    }

    public void setController() {
        this.addAutor.addActionListener(e -> controller.addAutor());
        this.addLivro.addActionListener(e -> controller.addLivro());
        this.tableAutores.getSelectionModel().addListSelectionListener(e -> controller.detalhesAutor(this.tableAutores));
        this.tableLivros.getSelectionModel().addListSelectionListener(e -> controller.detalhesLivro(this.tableLivros));
    }
}
