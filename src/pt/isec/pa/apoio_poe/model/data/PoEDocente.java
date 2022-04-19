package pt.isec.pa.apoio_poe.model.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PoEDocente implements Serializable {
    static final long serialVersionUID = 100L;
    private String nome;
    private String email;
    private String papel;

    public PoEDocente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("---- Docente ----\n");
        sb.append("Nome: " + nome + "\n");
        sb.append("Email: " + email + "\n");
        sb.append("Papel: " + papel + "\n");
        return sb.toString();
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
