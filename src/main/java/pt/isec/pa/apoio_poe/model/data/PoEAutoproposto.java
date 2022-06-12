package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * A classe PoEAutoproposto representa uma autoproposta de um aluno.
 */
public class PoEAutoproposto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 101L;
    final PoEAluno aluno;

    /** Construtor de uma autoproposta.
     * @param id ID da autoproposta.
     * @param titulo Título da autoproposta.
     * @param aluno Aluno que fez a autoproposta.
    **/
    public PoEAutoproposto(String id, String titulo, PoEAluno aluno) {
        super(id, titulo, aluno.getNrEstudante());
        this.aluno = aluno;
    }

    /** Construtor de uma autoproposta.
     * @param autoproposto Autoproposta a ser copiada.
    **/
    public PoEAutoproposto(PoEAutoproposto autoproposto) {
        super(autoproposto.getId(), autoproposto.getTitulo(), autoproposto.getNrAlunoAtribuido());
        this.aluno = autoproposto.aluno;
    }

    /** Obtém o aluno que fez a autoproposta.
     * @return Aluno que fez a autoproposta.
    **/
    @Override
    public PoEAluno getAluno() {
        return aluno;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Tipo: Estágio/projeto autoproposto\n";
    }

    @Override
    public String[] toStringArray(){
        return new String[]{
                "T3",
                super.getId(),
                super.getTitulo(),
                aluno.getEmail()
        };
    }
}
