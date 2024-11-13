package tarefa6.mvc.models.connection;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutorDAO {

    private final String stmtInserir = "INSERT INTO autor(nome, documento, nacionalidade, nascimento) VALUES(?, ?, ?, ?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor";
    private final String stmtListarLivros = "SELECT * FROM livro INNER JOIN livro_autor ON livro.id = livro_autor.idLivro WHERE livro_autor.idAutor = ?";

    public void inserirAutor(Autor autor) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = Database.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getDocumento());
            stmt.setString(3, autor.getNaturalidade());
            stmt.setDate(4, Date.valueOf(autor.getNascimento()));
            stmt.executeUpdate();
            autor.setId(lerIdAutor(stmt));
            gravarLivros(autor.getLivros(), autor, con);
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar rollback. Ex=" + ex1.getMessage());
            }
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar con. Ex=" + ex.getMessage());
            }
        }
    }

    private void gravarLivros(List<Livro> livros, Autor autor, Connection con) throws SQLException {
        String sql = "INSERT INTO livro_autor (idLivro, idAutor) VALUES (?, ?)";
        PreparedStatement stmt;
        stmt = con.prepareStatement(sql);
        for (Livro li : livros){
            stmt.setInt(1, li.getId());
            stmt.setInt(2, autor.getId());
            stmt.executeUpdate();
        }
    }

    private int lerIdAutor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Autor consultarAutor(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autor autorLido;
        try {
            con = Database.getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                autorLido = new Autor(rs.getString("nome"));
                autorLido.setId(rs.getInt("id"));
                return autorLido;
            } else {
                throw new RuntimeException("Não existe autor com este id. Id=" + id);
            }

        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Erro ao consultar um autor no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            ;
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            ;
            try {
                con.close();
                ;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
            ;
        }

    }

    public List<Autor> listarAutores() {
        List<Autor> lista = new ArrayList<>();
        try(Connection con = Database.getConnection(); PreparedStatement stmt = con.prepareStatement(stmtListar)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("nome"));
                autor.setId(rs.getInt("id"));
                autor.setNaturalidade(rs.getString("nacionalidade"));
                autor.setDocumento(rs.getString("documento"));
                autor.setNascimento(rs.getDate("nascimento").toLocalDate());
                lista.add(autor);
            }
            return lista;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        }
    }

    public List<Livro> lerLivros(int idAutor) {
        List<Livro> lista = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement sql = conn.prepareStatement(stmtListarLivros)) {
            Autor autor = this.consultarAutor(idAutor);
            sql.setInt(1, idAutor);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"), Collections.singletonList(autor));
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setPublicacao(rs.getDate("publicacao").toLocalDate());
                livro.setAssunto(rs.getString("assunto"));
                lista.add(livro);
            }
        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        }
        return lista;
    }

    public List<Autor> listarAutoresComLivros(){
        List<Autor> autores = this.listarAutores();
        autores.forEach(autor -> {
            autor.setLivros(this.lerLivros(autor.getId()));
        });
        return autores;
    }

}
