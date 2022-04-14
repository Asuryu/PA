package pt.isec.pa.apoio_poe.model.data;

public class PoEProjeto extends PoEProposta {
    private String[] ramosDestino;
    private PoEDocente docente;

    public PoEProjeto(String id, String titulo, long nrAlunoAtribuido, String[] ramosDestino, PoEDocente docente) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.docente = docente;
    }

    public String[] getRamosDestino() {
        return ramosDestino;
    }

    public void setRamosDestino(String[] ramosDestino) {
        this.ramosDestino = ramosDestino;
    }

    public PoEDocente getDocente() {
        return docente;
    }

    public void setDocente(PoEDocente docente) {
        this.docente = docente;
    }
}
