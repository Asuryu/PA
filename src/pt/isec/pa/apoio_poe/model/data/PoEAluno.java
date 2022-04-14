package pt.isec.pa.apoio_poe.model.data;

public class PoEAluno {
    private String nome;
    private long nrEstudante;
    private String email;
    private String curso;
    private String ramo;
    private double classificacao;
    private boolean estagios;

    public PoEAluno(String nome, long nrEstudante, String email, String curso, String ramo, double classificacao, boolean estagios) {
        this.nome = nome;
        this.nrEstudante = nrEstudante;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        this.estagios = estagios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getNrEstudante() {
        return nrEstudante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public boolean isEstagios() {
        return estagios;
    }

    public void setEstagios(boolean estagios) {
        this.estagios = estagios;
    }
}
