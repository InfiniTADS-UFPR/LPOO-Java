package tarefa3.exercicio5;

import java.time.LocalDate;

public class Contato {
    private long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String endereco;

    public Contato(long id, String nome, LocalDate dataNascimento, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", nome: '" + nome + '\'' +
                ", data de nascimento: " + dataNascimento +
                ", email: '" + email + '\'' +
                ", endereco: '" + endereco + '\'' +
                "}";
    }
}
