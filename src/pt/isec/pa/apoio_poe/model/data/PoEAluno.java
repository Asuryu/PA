package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class PoEAluno implements Serializable{
    static final long serialVersionUID = 100L;
    private String nome;
    private final Long nrEstudante;
    private String email;
    private String curso;
    private String ramo;
    private final double classificacao;
    private boolean estagios;
    private PoECandidatura candidatura;
    private PoEProposta propostaAtribuida;

    public PoEAluno(String nome, Long nrEstudante, String email, String curso, String ramo, double classificacao, boolean estagios) {
        this.nome = nome;
        this.nrEstudante = nrEstudante;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        this.estagios = estagios;
        this.candidatura = null;
        this.propostaAtribuida = null;
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

    public void setCandidatura(PoECandidatura candidatura) {
        this.candidatura = candidatura;
    }

    public void removeCandidatura() {
        this.candidatura = null;
    }

    public PoECandidatura getCandidatura() {
        return this.candidatura;
    }

    public void setPropostaAtribuida(PoEProposta proposta){
        this.propostaAtribuida = proposta;
    }

    public void removePropostaAtribuida(){
        this.propostaAtribuida = null;
    }

    public PoEProposta getPropostaAtribuida(){
        return propostaAtribuida;
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
        final StringBuilder sb = new StringBuilder();
        sb.append("---- Aluno nº " + nrEstudante + " ----\n");
        sb.append("Nome: " + nome + " (" + email + ")\n");
        sb.append("Curso: " + curso + "   Ramo: " + ramo + "\n");
        sb.append("Classificação: " + classificacao + "\n");
        sb.append("Estágios?: " + estagios + "\n");
        if(candidatura != null) sb.append("Candidaturas: " + candidatura.getPreferencias() + "\n");
        if(propostaAtribuida != null) sb.append("Proposta Atribuida: " + propostaAtribuida.getId() + "\n");
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
