package pt.isec.pa.apoio_poe.model.data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class PoEProposta implements Serializable{
    static final long serialVersionUID = 106L;
    private final String id;
    private String titulo;
    private Long nrAlunoAtribuido;
    private final ArrayList<PoECandidatura> candidaturas;
    private PoEOrientador orientador;

    public PoEProposta(String id, String titulo, Long nrAlunoAtribuido) {
        this.id = id;
        this.titulo = titulo;
        this.nrAlunoAtribuido = nrAlunoAtribuido;
        this.candidaturas = new ArrayList<>();
    }

    public PoEOrientador getOrientador() {
        return orientador;
    }

    public void setOrientador(PoEOrientador orientador) {
        this.orientador = orientador;
    }
    
    public PoEDocente getDocente() {
        return null;
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

    public void setNrAlunoAtribuido(Long nrAlunoAtribuido) {
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
        final StringBuilder sb = new StringBuilder();
        sb.append("---- Proposta nº ").append(id).append(" ----\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Nr. Aluno Atribuido: ").append(nrAlunoAtribuido).append("\n");
        if(orientador != null){
            sb.append("Orientador: ").append(orientador.getDocente().getNome()).append("   (").append(orientador.getDocente().getEmail()).append(")\n");
        }
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
        if (!(o instanceof PoEProposta that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
