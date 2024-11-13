package tarefa6.mvc.models.connection;

import tarefa6.mvc.models.Autor;
import tarefa6.mvc.models.Livro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private final String stmtInserir = "INSERT INTO livro(titulo, assunto, publicacao, isbn) VALUES(?, ?, ?, ?)";
    private final String stmtConsultar = "SELECT * FROM livro WHERE id = ?";
    private final String stmtListaLivroAutor = "SELECT * FROM livro";

    public void inserirLivro(Livro livro) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = Database.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAssunto());
            stmt.setDate(3, java.sql.Date.valueOf(livro.getPublicacao()));
            stmt.setString(4, livro.getIsbn());
            stmt.executeUpdate();
            int idLivroGravado = lerIdLivro(stmt);
            livro.setId(idLivroGravado);
            this.gravarAutores(livro, con);

            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar rollback. Ex=" + ex1.getMessage());
            }
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro ao obter configurações do banco de dados");
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            ;
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
            ;
        }
    }

    private int lerIdLivro(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Livro consultarLivro(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Database.getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            Livro livroLido = null;
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();
            List<Autor> listaAutores = lerAutores(id, con);
            livroLido = new Livro(rs.getString("titulo"), listaAutores);
            livroLido.setId(rs.getInt("Id"));
            return livroLido;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Erro ao consultar um livro no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar rs. Ex=" + ex.getMessage());
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
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
            ;
        }

    }

    private void gravarAutores(Livro livro, Connection con) throws SQLException {
        String sql = "INSERT INTO livro_autor (idLivro, idAutor) VALUES (?, ?)";
        PreparedStatement stmt;
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, livro.getId());
        List<Autor> autores = livro.getAutores();
        for (Autor autor : autores) {
            stmt.setLong(2, autor.getId());
            stmt.executeUpdate();
        }
    }

    public List<Autor> lerAutores(long idLivro, Connection con) throws SQLException {
        String sql = "SELECT autor.id,autor.nome"
                + " FROM autor"
                + " INNER JOIN livro_autor"
                + " 	ON autor.id = livro_autor.idAutor"
                + " WHERE livro_autor.idLivro = ? ";
        PreparedStatement stmt = null;
        List<Autor> autores = null;
        stmt = con.prepareStatement(sql);
        stmt.setLong(1, idLivro);
        ResultSet resultado = stmt.executeQuery();
        autores = new ArrayList<Autor>();
        while (resultado.next()) {
            Autor autorLido = new Autor(resultado.getString("nome"));
            autorLido.setId(resultado.getInt("id"));
            autores.add(autorLido);
        }
        return autores;
    }


    public List<Livro> listarLivroComAutores() {
        try(Connection con = Database.getConnection(); PreparedStatement stmt = con.prepareStatement(stmtListaLivroAutor)) {
            ResultSet rs = stmt.executeQuery();
            List<Livro> listaLivros = new ArrayList<Livro>();
            while (rs.next()) {
                List<Autor> listAutores = lerAutores(rs.getInt(1), con);
                Livro livro = new Livro(rs.getString(2), listAutores);
                livro.setId(rs.getInt(1));
                livro.setAssunto(rs.getString("assunto"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setPublicacao(rs.getDate("publicacao").toLocalDate());
                listaLivros.add(livro);
            }
            return listaLivros;
        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Erro ao listar um livro com autores no banco de dados. Origem=" + ex.getMessage());
        }
    }
}
