package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;

public class PoECandidatura {
    private long nrEstudante;
    private ArrayList<String> preferencias;

    public PoECandidatura(long nrEstudante, ArrayList<String> preferencias) {
        this.nrEstudante = nrEstudante;
        this.preferencias = preferencias;
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
        final StringBuffer sb = new StringBuffer();
        sb.append("---- Candidatura ----\n");
        sb.append("Nrº Aluno: " + nrEstudante + "\n");
        sb.append("Propostas (por ordem de preferência): " + preferencias.toString() + "\n");
        return sb.toString();
    }

    public String[] toStringArray(){
        return new String[]{
                String.valueOf(nrEstudante),
                String.join(",", preferencias)
        };
    }
}
