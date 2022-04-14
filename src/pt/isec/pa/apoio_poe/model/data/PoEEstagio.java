package pt.isec.pa.apoio_poe.model.data;

public class PoEEstagio extends PoEProposta {
    private String[] ramosDestino;
    private String entidade;

    public PoEEstagio(String id, String titulo, Long nrAlunoAtribuido, String[] ramosDestino, String entidade) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.entidade = entidade;
    }

    public String[] getRamosDestino() {
        return ramosDestino;
    }

    public void setRamosDestino(String[] ramosDestino) {
        this.ramosDestino = ramosDestino;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }
}
