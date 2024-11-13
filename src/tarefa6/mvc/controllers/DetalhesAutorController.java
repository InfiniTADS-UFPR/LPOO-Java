package tarefa6.mvc.controllers;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.models.connection.AutorDAO;
import tarefa6.mvc.views.autor.DetalhesAutor;
import tarefa6.mvc.views.livro.DetalheLivro;
import tarefa6.mvc.views.livro.LivrosTableModel;

import javax.swing.*;

public class DetalhesAutorController {
    private final DetalhesAutor view;
    private final Autor autor;
    private final AutorDAO autorDAO = new AutorDAO();

    public DetalhesAutorController(DetalhesAutor view, Autor autor) {
        this.view = view;
        this.autor = autor;
        this.view.initView();
        loadLivros();
    }

    public void loadLivros() {
        try {
            this.view.loadLivros(this.autorDAO.lerLivros(autor.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detalhesLivro(JTable tableLivros) {
        if (tableLivros.getSelectedRow() != -1) {
            LivrosTableModel model = (LivrosTableModel) tableLivros.getModel();
            Livro livro = model.getLivro(tableLivros.getSelectedRow());
            JOptionPane.showMessageDialog(this.view.contentPane, "Aguarde...");
            try {
                DetalheLivro detalheLivro = new DetalheLivro(livro);
                detalheLivro.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.view.contentPane, "Ocorreu um erro ao carregar os detalhes do livro: " + e.getMessage() );
                System.out.println(e);
            }
        }
    }
}
