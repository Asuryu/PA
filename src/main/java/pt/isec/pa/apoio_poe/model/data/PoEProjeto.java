package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class PoEProjeto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 105L;
    private ArrayList<String> ramosDestino;
    private final PoEDocente docente;

    public PoEProjeto(String id, String titulo, Long nrAlunoAtribuido, ArrayList<String> ramosDestino, PoEDocente docente) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.docente = docente;
    }

    public PoEProjeto(PoEProjeto projeto) {
        super(projeto.getId(), projeto.getTitulo(), projeto.getNrAlunoAtribuido());
        this.ramosDestino = projeto.getRamosDestino();
        this.docente = projeto.getDocente();
    }

    public ArrayList<String> getRamosDestino() {
        return ramosDestino;
    }

    public void setRamosDestino(ArrayList<String> ramosDestino) {
        this.ramosDestino = ramosDestino;
    }

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
