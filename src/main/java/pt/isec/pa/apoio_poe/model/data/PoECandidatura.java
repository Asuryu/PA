package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa uma candidatura de um aluno.
 */
public class PoECandidatura implements Serializable {
    static final long serialVersionUID = 121L;
    private long nrEstudante;
    private ArrayList<String> preferencias;

    /**
     * Construtor de uma candidatura.
     * @param nrEstudante Número de estudante da candidatura.
     * @param preferencias Lista de preferencias da candidatura.
     */
    public PoECandidatura(long nrEstudante, ArrayList<String> preferencias) {
        this.nrEstudante = nrEstudante;
        this.preferencias = preferencias;
    }

    /**
     * Construtor de uma candidatura.
     * @param c Candidatura a ser copiada.
     */
    public PoECandidatura(PoECandidatura c) {
        this.nrEstudante = c.getNrEstudante();
        this.preferencias = new ArrayList<>(c.getPreferencias());
    }

    /**
     * Obtém o número de estudante da candidatura.
     * @return Número de estudante da candidatura.
     */
    public long getNrEstudante() {
        return nrEstudante;
    }

    /**
     * Altera o número de estudante da candidatura.
     */
    public void setNrEstudante(long nrEstudante) {
        this.nrEstudante = nrEstudante;
    }

    /**
     * Obtém a lista de preferencias da candidatura.
     * @return Lista de preferencias da candidatura.
     */
    public ArrayList<String> getPreferencias() {
        return preferencias;
    }

    /**
     * Altera a lista de preferencias da candidatura.
     * @param preferencias Lista de preferencias da candidatura.
     */
    public void setPreferencias(ArrayList<String> preferencias) {
        this.preferencias = preferencias;
    }

    @Override
    public String toString() {
        return "---- Candidatura ----\n" +
                "Nrº Aluno: " + nrEstudante + "\n" +
                "Propostas (por ordem de preferência): " + preferencias.toString() + "\n";
    }

    public String[] toStringArray(){
        return new String[]{
                String.valueOf(nrEstudante),
                String.join(",", preferencias)
        };
    }
}
