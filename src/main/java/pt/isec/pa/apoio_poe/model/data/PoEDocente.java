package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa um docente.
 */
public class PoEDocente implements Serializable {
    static final long serialVersionUID = 103L;
    private String nome;
    private String email;
    private String papel;

    /**
     * Construtor de um docente.
     * @param nome Nome do docente.
     * @param email Email do docente.
     */
    public PoEDocente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Construtor de um docente.
     * @param d Docente a ser copiado.
     */
    public PoEDocente(PoEDocente d) {
        this.nome = d.getNome();
        this.email = d.getEmail();
        this.papel = d.papel;
    }

    /**
     * Obtém o nome do docente.
     * @return Nome do docente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do docente.
     * @param nome Nome do docente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do docente.
     * @return Email do docente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Altera o email do docente.
     * @param email Email do docente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o papel do docente.
     * @return Papel do docente.
     */
    public String getPapel() {
        return papel;
    }

    /**
     * Altera o papel do docente.
     * @param papel Papel do docente.
     */
    public void setPapel(String papel) {
        this.papel = papel;
    }

    @Override
    public String toString() {
        return "---- Docente ----\n" +
                "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Papel: " + papel + "\n";
    }

    public String[] toStringArray(){
        return new String[]{
                nome,
                email,
                papel
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoEDocente that = (PoEDocente) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
