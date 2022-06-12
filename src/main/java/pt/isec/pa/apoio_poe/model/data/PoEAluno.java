package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * A classe PoEAluno representa um aluno do ISEC.
 */
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

    /**
     * Construtor de um aluno.
     * @param nome Nome do aluno.
     * @param nrEstudante Número de estudante do aluno.
     * @param email Email do aluno.
     * @param curso Curso do aluno.
     * @param ramo Ramo do aluno.
     * @param classificacao Classificação do aluno.
     * @param estagios Estágios do aluno.
     **/
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

    /**
     * Construtor de um aluno.
     * @param aluno Aluno a ser copiado.
    **/
    public PoEAluno(PoEAluno aluno){
        this.nome = aluno.nome;
        this.nrEstudante = aluno.nrEstudante;
        this.email = aluno.email;
        this.curso = aluno.curso;
        this.ramo = aluno.ramo;
        this.classificacao = aluno.classificacao;
        this.estagios = aluno.estagios;
        this.candidatura = aluno.candidatura;
        this.propostaAtribuida = aluno.propostaAtribuida;
    }

    /**
     * Obtém o nome do aluno.
     * @return Nome do aluno.
     **/
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do aluno.
     * @param nome Novo nome do aluno.
    **/
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número de estudante do aluno.
     * @return Número de estudante do aluno.
    **/
    public Long getNrEstudante() {
        return nrEstudante;
    }

    /**
     * Obtém o email do aluno.
     * @return Email do aluno.
    **/
    public String getEmail() {
        return email;
    }

    /**
     * Altera o email do aluno.
     * @param email Novo email do aluno.
    **/
    public void setEmail(String email) { this.email = email; }

    /**
     * Obtém o curso do aluno.
     * @return Curso do aluno.
    **/
    public String getCurso() {
        return curso;
    }

    /**
     * Altera o curso do aluno.
     * @param curso Novo curso do aluno.
     * @return True se o curso foi alterado com sucesso, false caso contrário.
    **/
    public boolean setCurso(String curso) {
        if(curso.equals("LEI") || curso.equals("LEI-PL")){
            this.curso = curso;
            return true;
        }
        return false;
    }

    /**
     * Obtém o ramo do aluno.
     * @return Ramo do aluno.
    **/
    public String getRamo() {
        return ramo;
    }

    /**
     * Altera o ramo do aluno.
     * @param ramo Novo ramo do aluno.
     * @return True se o ramo foi alterado com sucesso, false caso contrário.
    **/
    public boolean setRamo(String ramo) {
        if(ramo.equals("DA") || ramo.equals("RAS") || ramo.equals("SI")){
            this.ramo = ramo;
            return true;
        }
        return false;
    }

    /**
     * Obtém a classificação do aluno.
     * @return Classificação do aluno.
    **/
    public double getClassificacao() {
        return classificacao;
    }

    /**
     * Indica se o aluno pode aceder a estágios
     * @return True se o aluno pode aceder a estágios, false caso contrário.
    **/
    public boolean isEstagios() {
        return estagios;
    }

    /**
     * Altera o indicador de possibilidade de acesso a estágios do aluno.
         * @param estagios Possibilidade de acesso a estágios.
    **/
    public void setEstagios(boolean estagios) {
        this.estagios = estagios;
    }

    /**
     * Altera a candidatura do aluno.
     * @param candidatura Nova candidatura do aluno.
    **/
    public void setCandidatura(PoECandidatura candidatura) {
        this.candidatura = candidatura;
    }

    /**
     * Remove a candidatura do aluno.
    **/
    public void removeCandidatura() {
        this.candidatura = null;
    }

    /**
     * Obtém a candidatura do aluno.
     * @return Candidatura do aluno.
    **/
    public PoECandidatura getCandidatura() {
        return this.candidatura;
    }

    /**
     * Altera a proposta atribuida ao aluno.
     * @param proposta Nova proposta atribuida ao aluno.
    **/
    public void setPropostaAtribuida(PoEProposta proposta){
        this.propostaAtribuida = proposta;
    }

    /**
     * Remove a proposta atribuida ao aluno.
    **/
    public void removePropostaAtribuida(){
        this.propostaAtribuida = null;
    }

    /**
     * Obtém a proposta atribuida ao aluno.
     * @return Proposta atribuida ao aluno.
    **/
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
        sb.append("---- Aluno nº ").append(nrEstudante).append(" ----\n");
        sb.append("Nome: ").append(nome).append(" (").append(email).append(")\n");
        sb.append("Curso: ").append(curso).append("   Ramo: ").append(ramo).append("\n");
        sb.append("Classificação: ").append(classificacao).append("\n");
        sb.append("Estágios?: ").append(estagios).append("\n");
        if(candidatura != null) sb.append("Candidaturas: ").append(candidatura.getPreferencias()).append("\n");
        if(propostaAtribuida != null) sb.append("Proposta Atribuida: ").append(propostaAtribuida.getId()).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoEAluno poEAluno = (PoEAluno) o;
        return Objects.equals(nrEstudante, poEAluno.nrEstudante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrEstudante);
    }
}
