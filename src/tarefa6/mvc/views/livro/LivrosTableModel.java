package tarefa6.mvc.views.livro;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LivrosTableModel extends AbstractTableModel {
    private List<Livro> livros = new ArrayList<>();
    private final String[] colunas = {"ID", "Titulo", "Assunto", "Autores", "Detalhes"};

    public LivrosTableModel(){}

    public void updateLivros(List<Livro> lista){
        this.livros = lista;
        this.fireTableDataChanged();
    }

    public Livro getLivro(int rowIndex){
        return livros.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna){
        return colunas[coluna];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro li = livros.get(rowIndex);
        return switch (columnIndex){
            case 0 -> li.getId();
            case 1 -> li.getTitulo();
            case 2 -> li.getAssunto();
            case 3 -> String.join("; ", li.getAutores().stream().map(Autor::getNome).toList());
            case 4 -> "Clique para ver";
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }
}
