package tarefa6.mvc.views.livro;

import tarefa6.mvc.controllers.NewLivroController;
import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import javax.swing.*;
import java.awt.*;

public class NewLivro extends JDialog {
    private JPanel contentPane;
    private final NewLivroController controller;
    private Livro livro;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tituloLivro;
    private JTextField assuntoLivro;
    private JTextField isbnLivro;
    private String publicacaoLivro;
    private JTextField autoresLivro;
    private JTextField idAutor;
    private JButton buscarAutorButton;
    private JLabel autorEncontrado;
    private JButton addAutorButton;
    private JTextField diaPublicacao;
    private JTextField mesPublicacao;
    private JTextField anoPublicacao;

    public NewLivro() {
        super((Frame) null, "Novo Livro", true);
        this.controller = new NewLivroController(this);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public void initView() {
        setContentPane(contentPane);
        setModal(true);
        setSize(600,300);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setController();
    }

    public void setController() {
        buscarAutorButton.addActionListener(e -> {
            try {
                controller.buscarAutor(this.idAutor.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        addAutorButton.addActionListener(e -> controller.addAutor());
        buttonOK.addActionListener(e -> {
            try {
                publicacaoLivro = diaPublicacao.getText() + "/" + mesPublicacao.getText() + "/" + anoPublicacao.getText();
                controller.salvarLivro(tituloLivro.getText(), assuntoLivro.getText(), isbnLivro.getText(), publicacaoLivro);
                onOK();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar livro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonCancel.addActionListener(e -> onCancel() );
    }

    public void showAutor(Autor autor) {
        autorEncontrado.setText(autor.getNome());
        autorEncontrado.setVisible(true);
        addAutorButton.setVisible(true);
    }

    public void updateAutores(String autores) {
        autoresLivro.setText(autores);
        autorEncontrado.setVisible(false);
        addAutorButton.setVisible(false);
    }
}
