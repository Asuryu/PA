package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class PoEProjeto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 105L;
    private ArrayList<String> ramosDestino;
    private PoEDocente docente;

    public PoEProjeto(String id, String titulo, Long nrAlunoAtribuido, ArrayList<String> ramosDestino, PoEDocente docente) {
        super(id, titulo, nrAlunoAtribuido);
        this.ramosDestino = ramosDestino;
        this.docente = docente;
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
    public void setDocente(PoEDocente docente) {
        this.docente = docente;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Tipo: Projeto\n");
        sb.append("Ramos destino: " + ramosDestino.toString() + "\n");
        sb.append("Docente: " + docente.getNome() + "   (" + docente.getEmail() + ")\n");
        return sb.toString();
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
