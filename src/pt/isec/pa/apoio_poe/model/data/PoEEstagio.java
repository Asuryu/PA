package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class PoEEstagio extends PoEProposta implements Serializable{
    static final long serialVersionUID = 104L;
    private ArrayList<String> ramosDestino;
    private String entidade;

    public PoEEstagio(String id, String titulo, Long nrAlunoAtribuido, ArrayList<String> ramosDestino, String entidade) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.entidade = entidade;
    }

    @Override
    public ArrayList<String> getRamosDestino() {
        return ramosDestino;
    }

    public void setRamosDestino(ArrayList<String> ramosDestino) {
        this.ramosDestino = ramosDestino;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Tipo: Estágio\n");
        sb.append("Ramos destino: " + ramosDestino.toString() + "\n");
        sb.append("Entidade: " + entidade + "\n");
        return sb.toString();
    }

    @Override
    public String[] toStringArray(){
        String[] ret;
        if(super.getNrAlunoAtribuido() == null){
            ret = new String[]{
                    "T1",
                    String.valueOf(super.getId()),
                    String.join("|", ramosDestino),
                    super.getTitulo(),
                    entidade
            };
        } else {
            ret = new String[]{
                    "T1",
                    String.valueOf(super.getId()),
                    String.join("|", ramosDestino),
                    super.getTitulo(),
                    entidade,
                    String.valueOf(super.getNrAlunoAtribuido())
            };
        }
        return ret;
    }
}
