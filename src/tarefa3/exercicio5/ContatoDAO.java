package tarefa3.exercicio5;

import tarefa3.conexao.Database;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private final String insert = "INSERT INTO contato (nome, email, nascimento, endereco) VALUES (?,?,?,?)";
    private final String update = "UPDATE contato SET nome = ?, email = ?, endereco = ?, nascimento =  ? WHERE id = ?";
    private final String delete = "DELETE FROM contato WHERE id = ?";
    private final String select = "SELECT * FROM contato ORDER BY nome";
    private static ContatoDAO instance;

    private ContatoDAO(){}

    public static ContatoDAO getContatoDAO(){
        if (instance == null){
            instance = new ContatoDAO();
        }
        return instance;
    }

    public void insere(Contato contato){
        try(Connection conn = Database.getConnection(); PreparedStatement sql = conn.prepareStatement(this.insert)){
            sql.setString(1, contato.getNome());
            sql.setString(2,  contato.getEmail());
            sql.setDate(3, Date.valueOf(contato.getDataNascimento()));
            sql.setString(4, contato.getEndereco());
            sql.executeUpdate();
            System.out.println("Inserção executada com sucesso!");
        } catch (SQLException | IOException e){
            System.out.println("Erro ao carregar conexão: "+ e.getMessage());
        }
    }
    public void altera(Contato contato){
        try(Connection conn = Database.getConnection(); PreparedStatement sql = conn.prepareStatement(this.update)){
            sql.setString(1, contato.getNome());
            sql.setString(2,  contato.getEmail());
            sql.setString(3, contato.getEndereco());
            sql.setDate(4, Date.valueOf(contato.getDataNascimento()));
            sql.setLong(5, contato.getId());
            sql.executeUpdate();
            System.out.println("Atualização executada com sucesso!");
        } catch (SQLException | IOException e){
            System.out.println("Erro ao carregar conexão: "+ e.getMessage());
        }
    }
    public void remove(Contato contato){
        try(Connection conn = Database.getConnection(); PreparedStatement sql = conn.prepareStatement(this.delete)){
            sql.setLong(1, contato.getId());
            sql.executeUpdate();
            System.out.println("Remoção executada com sucesso!");
        } catch (SQLException | IOException e){
            System.out.println("Erro ao carregar conexão: "+ e.getMessage());
        }
    }
    public List<Contato> lista(){
        ArrayList<Contato> lista = new ArrayList<>();
        try(Connection conn = Database.getConnection(); PreparedStatement sql = conn.prepareStatement(this.select)){
            ResultSet rs = sql.executeQuery();
            while (rs.next()){
                lista.add(new Contato(rs.getInt("id"), rs.getString("nome"), rs.getDate("nascimento").toLocalDate(), rs.getString("email"), rs.getString("endereco")));
            }
        } catch (SQLException | IOException e){
            System.out.println("Erro ao carregar conexão "+ e.getMessage());
        }
        return lista;
    }
}
