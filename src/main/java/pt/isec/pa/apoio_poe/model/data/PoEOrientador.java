package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa um orientador.
 */
public class PoEOrientador implements Serializable{
    static final long serialVersionUID = 114L;
    PoEDocente docente;
    final ArrayList<PoEProposta> propostas;

    /**
     * Construtor de um orientador.
     * @param docente Docente responsável.
     * @param propostas Lista de propostas pelas quais o orientador é responsável.
     */
    public PoEOrientador(PoEDocente docente) {
        this.docente = docente;
        this.propostas = new ArrayList<>();
    }

    /**
     * Construtor de um orientador.
     * @param ori Orientador a ser copiado.
     */
    public PoEOrientador(PoEOrientador ori) {
        this.docente = ori.docente;
        this.propostas = new ArrayList<>(ori.propostas);
    }

    /**
     * Obtém o docente responsável.
     * @return Docente responsável.
     */
    public PoEDocente getDocente() {
        return docente;
    }

    /**
     * Altera o docente responsável.
     * @param docente Docente responsável.
     */
    public void setDocente(PoEDocente docente) {
        this.docente = docente;
    }

    /**
     * Obtém a lista de propostas pelas quais o orientador é responsável.
     * @return Lista de propostas pelas quais o orientador é responsável.
     */
    public ArrayList<PoEProposta> getPropostas() {
        return propostas;
    }

    /**
     * Adiciona uma proposta à lista de propostas pelas quais o orientador é responsável.
     * @param proposta Proposta a ser adicionada.
     */
    public void addProposta(PoEProposta proposta) {
        if(!propostas.contains(proposta)){
            propostas.add(proposta);
        }
    }

    /**
     * Remove uma proposta da lista de propostas pelas quais o orientador é responsável.
     * @param proposta Proposta a ser removida.
     */
    public void removeProposta(PoEProposta proposta){
        propostas.remove(proposta);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        StringBuilder propostasStr = new StringBuilder();
        for(PoEProposta proposta : propostas){
            propostasStr.append(proposta.getId()).append(", ");
        }
        if(!propostasStr.isEmpty()){
            propostasStr = new StringBuilder(propostasStr.substring(0, propostasStr.length() - 2));
        } else propostasStr = new StringBuilder("Nenhuma");
        sb.append("---- Orientador ----\n");
        sb.append("Docente: ").append(docente.getNome()).append("    (").append(docente.getEmail()).append(")\n");
        sb.append("Propostas Atribuídas: ").append(propostasStr).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoEOrientador that)) return false;
        return getDocente().equals(that.getDocente()) && Objects.equals(getPropostas(), that.getPropostas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocente(), getPropostas());
    }
}
