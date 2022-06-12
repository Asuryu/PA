package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa uma proposta de projeto.
 */
public class PoEProjeto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 105L;
    private ArrayList<String> ramosDestino;
    private final PoEDocente docente;

    /**
     * Construtor de uma proposta de projeto.
     * @param id ID da proposta.
     * @param titulo Título da proposta.
     * @param nrAlunoAtribuido Número de aluno atribuído à proposta.
     * @param ramosDestino Ramos de destino da proposta.
     * @param docente Docente responsável pela proposta.
     */
    public PoEProjeto(String id, String titulo, Long nrAlunoAtribuido, ArrayList<String> ramosDestino, PoEDocente docente) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.docente = docente;
    }

    /**
     * Construtor de uma proposta de projeto.
     * @param projeto Proposta a ser copiada.
     */
    public PoEProjeto(PoEProjeto projeto) {
        super(projeto.getId(), projeto.getTitulo(), projeto.getNrAlunoAtribuido());
        this.ramosDestino = projeto.getRamosDestino();
        this.docente = projeto.getDocente();
    }

    /**
     * Obtém os ramos de destino da proposta.
     * @return Ramos de destino da proposta.
     */
    public ArrayList<String> getRamosDestino() {
        return ramosDestino;
    }

    /**
     * Define os ramos de destino da proposta.
     * @param ramosDestino Ramos de destino da proposta.
     */
    public void setRamosDestino(ArrayList<String> ramosDestino) {
        this.ramosDestino = ramosDestino;
    }

    /**
     * Obtém o docente responsável pela proposta.
     * @return Docente responsável pela proposta.
     */
    @Override
    public PoEDocente getDocente() {
        return docente;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Tipo: Projeto\n" +
                "Ramos destino: " + ramosDestino.toString() + "\n" +
                "Docente: " + docente.getNome() + "   (" + docente.getEmail() + ")\n";
    }

    @Override
    public String[] toStringArray(){
        String[] ret;
        if(super.getNrAlunoAtribuido() == null){
            ret = new String[]{
                    "T2",
                    super.getId(),
                    String.join("|", ramosDestino),
                    super.getTitulo(),
                    docente.getEmail()
            };
        } else {
            ret = new String[]{
                    "T2",
                    super.getId(),
                    String.join("|", ramosDestino),
                    super.getTitulo(),
                    docente.getEmail(),
                    String.valueOf(super.getNrAlunoAtribuido())
            };
        }
        return ret;
    }
}
