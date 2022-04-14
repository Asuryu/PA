package pt.isec.pa.apoio_poe.model.data;

public class PoEDocente {
    private String nome;
    private String email;
    private String papel;

    public PoEDocente(String nome, String email, String papel) {
        this.nome = nome;
        this.email = email;
        this.papel = papel;
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
}
