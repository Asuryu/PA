package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class PoEOrientador implements Serializable{
    static final long serialVersionUID = 114L;
    PoEDocente docente;
    PoEProjeto projeto;
    PoEProposta proposta;

    public PoEOrientador(PoEDocente docente, PoEProjeto projeto, PoEProposta proposta) {
        this.docente = docente;
        this.projeto = projeto;
        this.proposta = proposta;
    }

    public PoEOrientador getOrientador() {
        return this;
    }

    public PoEDocente getDocente() {
        return docente;
    }

    public void setDocente(PoEDocente docente) {
        this.docente = docente;
    }

    public PoEProjeto getProjeto() {
        return projeto;
    }

    public void setProjeto(PoEProjeto projeto) {
        this.projeto = projeto;
    }

    public PoEProposta getPropostaAtribuida() {
        return proposta;
    }

    public void setPropostaAtribuida(PoEProposta proposta) {
        this.proposta = proposta;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("---- Orientador ----\n");
        sb.append("Docente: " + docente.toString() + "\n");
        sb.append("Projeto: " + projeto.toString() + "\n");
        sb.append("Proposta: " + proposta.toString() + "\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoEOrientador that = (PoEOrientador) o;
        return Objects.equals(docente, that.docente) &&
                Objects.equals(projeto, that.projeto) &&
                Objects.equals(proposta, that.proposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docente, projeto, proposta);
    }
}
