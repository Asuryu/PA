package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class PoEAutoproposto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 101L;
    PoEAluno aluno;

    public PoEAutoproposto(String id, String titulo, PoEAluno aluno) {
        super(id, titulo, aluno.getNrEstudante());
        this.aluno = aluno;
    }

    @Override
    public PoEAluno getAluno() {
        return aluno;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Tipo: Estágio/projeto autoproposto\n");
        return sb.toString();
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
