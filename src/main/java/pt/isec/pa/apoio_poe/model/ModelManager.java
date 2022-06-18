package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoEDocente;
import pt.isec.pa.apoio_poe.model.data.PoEOrientador;
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

    public ArrayList<PoEAluno> getAlunosByName(String name){
        return ctx.getAlunosByName(name);
    }

    public ArrayList<PoEAluno> getAlunoByID(Long id){
        ArrayList<PoEAluno> aluno = new ArrayList<>();
        if(ctx.getAlunoById(id) != null){
            aluno.add(ctx.getAlunoById(id));
        }
        return aluno;
    }

    public ArrayList<PoEAluno> getAlunosByRamo(String ramo){
        return ctx.getAlunosByRamo(ramo);
    }

    public ArrayList<PoEAluno> getAlunosByCurso(String curso){
        return ctx.getAlunosByCurso(curso);
    }

    public boolean removeAluno(Long id){
        return ctx.removeAluno(ctx.getAlunoById(id));
    }

    public boolean removeDocente(PoEDocente docente){
        return ctx.removeDocente(docente);
    }

    public boolean removeProposta(PoEProposta proposta){
        return ctx.removeProposta(proposta);
    }

    public ArrayList<PoEDocente> getDocentes(){
        return ctx.getDocentes();
    }

    public ArrayList<PoEDocente> getDocentesByName(String name){
        ArrayList<PoEDocente> doc = new ArrayList<>();
        if(ctx.getDocenteByName(name) != null){
            doc.add(ctx.getDocenteByName(name));
        }
        return doc;
    }

    public ArrayList<PoEDocente> getDocenteByEmail(String email){
        ArrayList<PoEDocente> doc = new ArrayList<>();
        doc.add(ctx.getDocenteByEmail(email));
        return doc;
    }

    public ArrayList<PoEProposta> getPropostasByTitle(String name){
        return ctx.getPropostasByTitle(name);
    }

    public ArrayList<PoEProposta> getPropostasByID(String id){
        ArrayList<PoEProposta> prop = new ArrayList<>();
        if(ctx.getPropostaById(id) != null){
            prop.add(ctx.getPropostaById(id));
        }
        return prop;
    }

    public ArrayList<PoEProposta> getPropostasByType(String type){
        return ctx.getPropostasByType(type);
    }

    public ArrayList<PoEProposta> getPropostasByRamo(String ramo){
        ArrayList<PoEProposta> propostas = new ArrayList<>();
        for(PoEProposta proposta : ctx.getPropostas()){
            if(proposta.getRamosDestino().contains(ramo)){
                propostas.add(proposta);
            }
        }
        return propostas;
    }

    public ArrayList<PoEOrientador> getOrientadores(){
        return ctx.getOrientadores();
    }

    public ArrayList<PoEOrientador> getOrientadoresByName(String name){
        ArrayList<PoEOrientador> orientadores = new ArrayList<>();
        for(PoEOrientador orientador : ctx.getOrientadores()){
            if(orientador.getDocente().getNome().equalsIgnoreCase(name)){
                orientadores.add(orientador);
            }
        }
        return orientadores;
    }

    public ArrayList<PoEOrientador> getOrientadoresByEmail(String email){
        ArrayList<PoEOrientador> orientadores = new ArrayList<>();
        for(PoEOrientador orientador : ctx.getOrientadores()){
            if(orientador.getDocente().getEmail().equalsIgnoreCase(email)){
                orientadores.add(orientador);
            }
        }
        return orientadores;
    }

    public void addAlunosCSV(String fileName){ctx.addAlunosCSV(fileName); }
    public void saveAlunosCSV(String fileName){
        ctx.saveAlunosCSV(fileName);
    }
    public void addDocentesCSV(String fileName){ctx.addDocentesCSV(fileName); }
    public void saveDocentesCSV(String fileName){ctx.saveDocentesCSV(fileName); }
    public void addPropostasCSV(String fileName){ctx.addPropostasCSV(fileName); }
    public void savePropostasCSV(String fileName){ctx.savePropostasCSV(fileName); }

    public ArrayList<PoEProposta> getPropostas(){
        return new ArrayList<>(ctx.getPropostas());
    }
    public ArrayList<PoEAluno> getAlunos(){return new ArrayList<>(ctx.getAlunos()); }
    public void addAluno(PoEAluno aluno){
        ctx.data.addAluno(aluno);
    }

}
