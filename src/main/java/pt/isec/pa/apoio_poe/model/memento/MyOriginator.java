package pt.isec.pa.apoio_poe.model.memento;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;

import java.util.ArrayList;

public class MyOriginator implements IOriginator {
    PoEAluno aluno;
    PoEProposta proposta;

    public MyOriginator(PoEAluno aluno, PoEProposta proposta) {
        this.aluno = aluno;
        this.proposta = proposta;
    }

    private static class MyMemento implements IMemento {
        PoEAluno aluno;
        PoEProposta proposta;

        MyMemento(MyOriginator base) {
            this.aluno = new PoEAluno(base.aluno);
            this.proposta = new PoEProposta(base.proposta);
        }
    }

    @Override
    public IMemento save() { return new MyMemento(this); }

    @Override
    public void restore(IMemento memento) {
        if(memento instanceof MyMemento m) {
            this.aluno = m.aluno;
            this.proposta = m.proposta;
        }
    }
}
