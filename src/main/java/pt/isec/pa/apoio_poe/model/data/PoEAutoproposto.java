package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class PoEAutoproposto extends PoEProposta implements Serializable{
    static final long serialVersionUID = 101L;
    final PoEAluno aluno;

    public PoEAutoproposto(String id, String titulo, PoEAluno aluno) {
        super(id, titulo, aluno.getNrEstudante());
        this.aluno = aluno;
    }
    
    public PoEAutoproposto(PoEAutoproposto autoproposto) {
        super(autoproposto.getId(), autoproposto.getTitulo(), autoproposto.getNrAlunoAtribuido());
        this.aluno = autoproposto.aluno;
    }

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
