package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class PoECandidatura implements Serializable {
    static final long serialVersionUID = 121L;
    private long nrEstudante;
    private ArrayList<String> preferencias;

    public PoECandidatura(long nrEstudante, ArrayList<String> preferencias) {
        this.nrEstudante = nrEstudante;
        this.preferencias = preferencias;
    }

    public PoECandidatura(PoECandidatura c) {
        this.nrEstudante = c.getNrEstudante();
        this.preferencias = new ArrayList<>(c.getPreferencias());
    }

    public long getNrEstudante() {
        return nrEstudante;
    }

    public void setNrEstudante(long nrEstudante) {
        this.nrEstudante = nrEstudante;
    }

    public ArrayList<String> getPreferencias() {
        return preferencias;
    }

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
