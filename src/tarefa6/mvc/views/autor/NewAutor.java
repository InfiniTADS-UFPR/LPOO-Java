package tarefa6.mvc.views.autor;

import tarefa6.mvc.controllers.NewAutorController;
import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import javax.swing.*;
import java.awt.*;

public class NewAutor extends JDialog {
    private JPanel contentPane;
    private NewAutorController controller;
    private Autor autor;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nomeAutor;
    private JTextField documentoAutor;
    private JTextField naturalidadeAutor;
    private JTextField livrosAutor;
    private String nascimentoAutor;
    private JTextField diaNascimento;
    private JTextField mesNascimento;
    private JTextField anoNascimento;
    private JTextField idLivro;
    private JButton buscarLivroButton;
    private JLabel livroEncontrado;
    private JButton addLivroButton;

    public NewAutor() {
        super((Frame) null, "Novo Autor", true);
        this.controller = new NewAutorController(this);
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
        setSize(600, 300);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setController();
    }

    private void setController() {
        buscarLivroButton.addActionListener(e -> {
            try {
                controller.buscarLivro(this.idLivro.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        addLivroButton.addActionListener(e -> {
            controller.addLivro();
        });
        buttonOK.addActionListener(e -> {
            try {
                nascimentoAutor = diaNascimento.getText() + "/" + mesNascimento.getText() + "/" + anoNascimento.getText();
                controller.salvarAutor(nomeAutor.getText(), documentoAutor.getText(), naturalidadeAutor.getText(), nascimentoAutor);
                onOK();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonCancel.addActionListener(e -> onCancel());
    }

    public void showLivro(Livro livro) {
        livroEncontrado.setText(livro.getTitulo());
        livroEncontrado.setVisible(true);
        addLivroButton.setVisible(true);
    }

    public void updateLivros(String livros) {
        livrosAutor.setText(livros);
        livroEncontrado.setVisible(false);
        addLivroButton.setVisible(false);
    }
}
