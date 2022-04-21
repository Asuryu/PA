package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;
import java.util.Objects;

public class PoEProposta {
    private final String id;
    private String titulo;
    private Long nrAlunoAtribuido;
    private ArrayList<PoECandidatura> candidaturas;

    public PoEProposta(String id, String titulo, Long nrAlunoAtribuido) {
        this.id = id;
        this.titulo = titulo;
        this.nrAlunoAtribuido = nrAlunoAtribuido;
        this.candidaturas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getNrAlunoAtribuido() {
        return nrAlunoAtribuido;
    }

    public void setNrAlunoAtribuido(long nrAlunoAtribuido) {
        this.nrAlunoAtribuido = nrAlunoAtribuido;
    }

    public ArrayList<String> getRamosDestino(){ return new ArrayList<>(); }

    public PoEAluno getAluno(){
        return null;
    }

    public ArrayList<PoECandidatura> getCandidaturas() {
        return candidaturas;
    }

    public void addCandidatura(PoECandidatura candidatura) {
        this.candidaturas.add(candidatura);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("---- Proposta nº " + id + " ----\n");
        sb.append("Título: " + titulo + "\n");
        sb.append("Nr. Aluno Atribuido: " + nrAlunoAtribuido + "\n");
        return sb.toString();
    }

    public String[] toStringArray(){
        return new String[]{
                String.valueOf(id),
                titulo,
                String.valueOf(nrAlunoAtribuido)
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoEProposta)) return false;
        PoEProposta that = (PoEProposta) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
