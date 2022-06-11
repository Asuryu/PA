package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class PoEOrientador implements Serializable{
    static final long serialVersionUID = 114L;
    PoEDocente docente;
    final ArrayList<PoEProposta> propostas;

    public PoEOrientador(PoEDocente docente) {
        this.docente = docente;
        this.propostas = new ArrayList<>();
    }

    public PoEOrientador(PoEOrientador other) {
        this.docente = other.docente;
        this.propostas = new ArrayList<>(other.propostas);
    }

    public PoEDocente getDocente() {
        return docente;
    }

    public void setDocente(PoEDocente docente) {
        this.docente = docente;
    }

    public ArrayList<PoEProposta> getPropostas() {
        return propostas;
    }

    public void addProposta(PoEProposta proposta) {
        if(!propostas.contains(proposta)){
            propostas.add(proposta);
        }
    }

    public boolean removeProposta(PoEProposta proposta){
        return propostas.remove(proposta);
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
