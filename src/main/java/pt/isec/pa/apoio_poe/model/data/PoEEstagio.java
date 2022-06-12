package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa uma proposta de estágio.
 */
public class PoEEstagio extends PoEProposta implements Serializable{
    static final long serialVersionUID = 104L;
    private ArrayList<String> ramosDestino;
    private String entidade;

    /**
     * Construtor de uma proposta de estágio.
     * @param id ID da proposta.
     * @param titulo Título da proposta.
     * @param nrAlunoAtribuido Número de aluno atribuído.
     * @param ramosDestino Ramos destino da proposta.
     * @param entidade Entidade da proposta.
    **/
    public PoEEstagio(String id, String titulo, Long nrAlunoAtribuido, ArrayList<String> ramosDestino, String entidade) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.entidade = entidade;
    }

    /**
     * Construtor de uma proposta de estágio.
     * @param e Estágio a ser copiado.
     */
    public PoEEstagio(PoEEstagio estagio) {
        super(estagio.getId(), estagio.getTitulo(), estagio.getNrAlunoAtribuido());
        this.ramosDestino = estagio.getRamosDestino();
        this.entidade = estagio.getEntidade();
    }

    /**
     * Obtém os ramos destino da proposta.
     * @return Ramos destino da proposta.
     */
    @Override
    public ArrayList<String> getRamosDestino() {
        return ramosDestino;
    }

    /**
     * Altera os ramos destino da proposta.
     * @param ramosDestino Ramos destino da proposta.
     */
    public void setRamosDestino(ArrayList<String> ramosDestino) { this.ramosDestino = ramosDestino; }

    /**
     * Obtém a entidade da proposta.
     * @return Entidade da proposta.
     */
    public String getEntidade() {
        return entidade;
    }

    /**
     * Altera a entidade da proposta.
     * @param entidade Entidade da proposta.
     */
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Tipo: Estágio\n" +
                "Ramos destino: " + ramosDestino.toString() + "\n" +
                "Entidade: " + entidade + "\n";
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
