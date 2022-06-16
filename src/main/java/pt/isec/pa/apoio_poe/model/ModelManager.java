package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEProposta;
import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class ModelManager {
    public static final String PROP_STATE = "state";
    public static final String PROP_DATA  = "data";

    PoEContext ctx;
    final PropertyChangeSupport pcs;

    public ModelManager() {
        this.ctx = new PoEContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public PoEState getState() {
        return ctx.getState();
    }

    public boolean isClosed() {
        return ctx.isClosed();
    }

    public void next() {
        ctx.nextPhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }

    public void previous() {
        ctx.previousPhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }

    public void close() {
        ctx.closePhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }

    public void save(String fileName) {
        ctx.exitAndSave(fileName);
    }

    public void load(String fileName) {
        this.ctx = ctx.loadSave(fileName);
        pcs.firePropertyChange(PROP_STATE, null, ctx.getState());
    }

    public ArrayList<PoEAluno> getAlunosWithProps(){
        ArrayList<PoEAluno> alunos = new ArrayList<>();
        for(PoEAluno aluno : ctx.getAlunos()){
            if(aluno.getPropostaAtribuida() != null){
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public ArrayList<PoEAluno> getAlunosWithoutProps(){
        ArrayList<PoEAluno> alunos = new ArrayList<>();
        for(PoEAluno aluno : ctx.getAlunos()){
            if(aluno.getPropostaAtribuida() == null){
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public ArrayList<PoEProposta> getPropostas(){
        return new ArrayList<>(ctx.getPropostas());
    }

    public void addAluno(PoEAluno aluno){
        ctx.data.addAluno(aluno);
    }

}