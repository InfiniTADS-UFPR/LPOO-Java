package tarefa6.mvc;

import tarefa6.mvc.views.LivrosEAutoresView;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Exemplo Livro & Autor");
        frame.setContentPane(new LivrosEAutoresView().panel);
        Dimension dimension = new Dimension(800,500);
        frame.setMinimumSize(dimension);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}