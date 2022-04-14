package pt.isec.pa.apoio_poe.model.data;

public class PoEProposta {
    private final String id;
    private String titulo;
    private Long nrAlunoAtribuido;

    public PoEProposta(String id, String titulo, Long nrAlunoAtribuido) {
        this.id = id;
        this.titulo = titulo;
        this.nrAlunoAtribuido = nrAlunoAtribuido;
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

    public long getNrAlunoAtribuido() {
        return nrAlunoAtribuido;
    }

    public void setNrAlunoAtribuido(long nrAlunoAtribuido) {
        this.nrAlunoAtribuido = nrAlunoAtribuido;
    }
}
