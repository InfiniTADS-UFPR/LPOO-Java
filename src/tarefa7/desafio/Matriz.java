package tarefa7.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Matriz {
    private final List<List<Long>> matrix;
    private int x;
    private int y;

    public Matriz(int x, int y){
        this.x = x;
        this.y = y;
        Random random = new Random();
        matrix = LongStream.range(0, x)
                .mapToObj(i -> LongStream.range(0, y)
                        .mapToObj(j -> random.nextLong(100))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public Matriz(List<List<Long>> matrix){
        this.matrix = matrix;
        this.x = matrix.size();
        this.y = matrix.get(0).size();
    }

    public Matriz(){
        this.matrix = new ArrayList<>();
    }

    public List<Long> getRow(int x){
        return matrix.get(x);
    }

    public List<Long> getColumn(int y){
        ArrayList<Long> column = new ArrayList<>();
        for(List<Long> row : matrix){
            column.add(row.get(y));
        }
        return column;
    }

    private List<List<Long>> toList(){
        return this.matrix.stream().toList();
    }

    public void join(Matriz subMatriz){
        this.matrix.addAll(subMatriz.toList());
        this.x = this.matrix.size();
        this.y = this.matrix.get(0).size();
    }

    public Matriz subMatriz(int start, int end){
        return new Matriz(this.matrix.subList(start, end));
    }

    public void setValue(int x, int y, long value){
        matrix.get(x).set(y, value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        StringBuilder matrix = new StringBuilder();
        for(int i=0;i<x;i++){
            matrix.append("| ");
            matrix.append(String.join(", ", this.matrix.get(i).stream().map(v -> String.format("%2d", v)).toList()));
            matrix.append(" |\n");
        }
        return matrix.toString();
    }
}
