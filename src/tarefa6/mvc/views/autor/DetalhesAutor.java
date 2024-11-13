package tarefa6.mvc.views.autor;

import tarefa6.mvc.controllers.DetalhesAutorController;
import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;
import tarefa6.mvc.views.livro.LivrosTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DetalhesAutor extends JDialog {
    public JPanel contentPane;
    private DetalhesAutorController controller;
    private Autor autor;
    private JButton buttonOK;
    private JTable tableLivros;
    private JLabel nomeAutor;
    private JLabel documentoAutor;
    private JLabel nascimentoAutor;
    private JLabel nacionalidadeAutor;
    private final LivrosTableModel livrosTableModel = new LivrosTableModel();

    public DetalhesAutor(Autor autor) {
        super((Frame) null, "Detalhes autor", true);
        this.autor = autor;
        controller = new DetalhesAutorController(this, autor);
    }

    public void initView() {
        setContentPane(contentPane);
        setModal(true);
        setSize(600,350);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        this.nomeAutor.setText(autor.getNome());
        this.documentoAutor.setText(autor.getDocumento());
        this.nacionalidadeAutor.setText(autor.getNaturalidade());
        this.nascimentoAutor.setText(autor.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.tableLivros.setModel(livrosTableModel);
        setController();
    }

    private void setController() {
        buttonOK.addActionListener(e -> onOK());
        this.tableLivros.getSelectionModel().addListSelectionListener(e -> controller.detalhesLivro(this.tableLivros));
    }

    public void loadLivros(List<Livro> lista) {
        this.livrosTableModel.updateLivros(lista);
    }

    private void onOK() {
        dispose();
    }
}
