package pt.isec.pa.apoio_poe.model.data;

public class PoEAutoproposto extends PoEProposta {

    public PoEAutoproposto(String id, String titulo, PoEAluno aluno) {
        super(id, titulo, aluno.getNrEstudante());
    }
}
