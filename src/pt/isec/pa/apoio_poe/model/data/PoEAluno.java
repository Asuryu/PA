package pt.isec.pa.apoio_poe.model.data;

import java.util.Objects;

public class PoEAluno {
    private String nome;
    private final Long nrEstudante;
    private String email;
    private String curso;
    private String ramo;
    private final double classificacao;
    private boolean estagios;

    public PoEAluno(String nome, Long nrEstudante, String email, String curso, String ramo, double classificacao, boolean estagios) {
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

    public Long getNrEstudante() {
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

    public String[] toStringArray(){
        return new String[]{
            String.valueOf(nrEstudante),
            nome,
            email,
            curso,
            ramo,
            String.valueOf(classificacao),
            String.valueOf(estagios)
        };
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("---- Aluno nº " + nrEstudante + " ----\n");
        sb.append("Nome: " + nome + " (" + email + ")\n");
        sb.append("Curso: " + curso + "   Ramo: " + ramo + "\n");
        sb.append("Classificação: " + classificacao + "\n");
        sb.append("Estágios?: " + estagios + "\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoEAluno poEAluno = (PoEAluno) o;
        return nrEstudante == poEAluno.nrEstudante;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrEstudante);
    }
}
