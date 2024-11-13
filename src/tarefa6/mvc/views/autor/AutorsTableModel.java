package tarefa6.mvc.views.autor;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AutorsTableModel extends AbstractTableModel {
    private List<Autor> autores = new ArrayList<>();
    private final String[] colunas = {"ID", "Nome", "Naturalidade", "Livros", "Detalhes"};

    public AutorsTableModel(){}

    public void updateAutores(List<Autor> lista){
        this.autores = lista;
        this.fireTableDataChanged();
    }

    public Autor getAutor(int rowIndex){
        return autores.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return autores.size();
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
        Autor a = autores.get(rowIndex);
        return switch (columnIndex){
            case 0 -> a.getId();
            case 1 -> a.getNome();
            case 2 -> a.getNaturalidade();
            case 3 -> String.join("; ", a.getLivros().stream().map(Livro::getTitulo).toList());
            case 4 -> "Clique para ver";
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }
}
