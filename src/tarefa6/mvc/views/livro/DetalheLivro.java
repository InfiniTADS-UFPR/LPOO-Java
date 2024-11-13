package tarefa6.mvc.views.livro;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class DetalheLivro extends JDialog {
    private static final String baseUrl = "https://covers.openlibrary.org/b/isbn/";
    public JPanel contentPane;
    private Livro livro;
    private JButton buttonOK;
    private JLabel cover;
    private JLabel tituloLivro;
    private JLabel assuntoLivro;
    private JLabel publicacaoLivro;
    private JLabel isbnLivro;
    private JLabel autoresLivro;

    public DetalheLivro(Livro livro) throws MalformedURLException {
        super((Frame) null, "Detalhes do livro", true);
        this.livro = livro;
        initView();

        buttonOK.addActionListener(e -> onOK());
    }

    private void initView() throws MalformedURLException {
        setContentPane(contentPane);
        setModal(true);
        setSize(600,400);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        tituloLivro.setText(livro.getTitulo());
        assuntoLivro.setText(livro.getAssunto());
        publicacaoLivro.setText(livro.getPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        isbnLivro.setText(livro.getIsbn());
        autoresLivro.setText(String.join("; ", livro.getAutores().stream().map(Autor::getNome).toList()));

        URL url = new URL(baseUrl+livro.getIsbn()+"-M.jpg");
        ImageIcon image = new ImageIcon(url);
        cover.setIcon(image);
    }

    private void onOK() {
        dispose();
    }
}
