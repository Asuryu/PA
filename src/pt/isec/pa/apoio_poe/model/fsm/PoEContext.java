package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class PoEContext implements Serializable{
    static final long serialVersionUID = 110L;
    IPoEState state;
    PoEData data;

    public PoEContext(){
        data = new PoEData();
        state = new ConfigState(this, data);
    }

    void changeState(IPoEState newState){
        state = newState;
    }

    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    public boolean closePhase(){
        return state.closePhase();
    }

    public boolean isClosed(){ return state.isClosed(); }

    public boolean nextPhase(){
        return state.nextPhase();
    }

    public boolean previousPhase(){
        return state.previousPhase();
    }

    public boolean exitAndSave(String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))){
            oos.writeObject(this);
        }catch(Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("[!] Erro ao carregar o ficheiro");
        }
        return state.exitAndSave();
    }

    public PoEContext loadSave(String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
            PoEContext context = (PoEContext)ois.readObject();
            this.data = context.data;
            this.state = context.state;
            return context;
        }catch(Exception e){
            System.out.println("[!] Erro ao carregar o ficheiro");
            return null;
        }
    }

    public boolean addAlunosCSV(String filePath){ return state.addAlunosCSV(filePath); }

    public boolean saveAlunosCSV(String filePath){ return state.saveAlunosCSV(filePath); }

    public boolean addDocentesCSV(String filePath){ return state.addDocentesCSV(filePath); }

    public boolean saveDocentesCSV(String filePath){ return state.saveDocentesCSV(filePath); }

    public boolean addPropostasCSV(String filePath){ return state.addPropostasCSV(filePath); }

    public boolean savePropostasCSV(String filePath){ return state.savePropostasCSV(filePath); }

    public boolean addCandidaturasCSV(String filePath){ return state.addCandidaturasCSV(filePath); }

    public boolean saveCandidaturasCSV(String filePath){ return state.saveCandidaturasCSV(filePath); }

    public ArrayList<PoEAluno> getAlunos(){
        return data.getAlunos();
    }
    public PoEAluno getAlunoById(long nrAluno){
        return data.getAlunoById(nrAluno);
    }
    public ArrayList<PoEAluno> getAlunosByName(String nomeAluno){
        return data.getAlunosByName(nomeAluno);
    }
    public ArrayList<PoEAluno> getAlunosByCurso(String curso){
        return data.getAlunosByCurso(curso);
    }
    public ArrayList<PoEAluno> getAlunosByRamo(String ramo){
        return data.getAlunosByRamo(ramo);
    }
    public PoEAluno getAlunoByEmail(String email){ return data.getAlunoByEmail(email); }
    public boolean removeAluno(PoEAluno aluno){ return data.removeAluno(aluno); }
    public ArrayList<PoEDocente> getDocentes() {
        return data.getDocentes();
    }
    public PoEDocente getDocenteByEmail(String email){
        return data.getDocenteByEmail(email);
    }
    public PoEDocente getDocenteByName(String nome) {
        return data.getDocenteByName(nome);
    }
    public boolean removeDocente(PoEDocente docente){ return data.removeDocente(docente); }
    public ArrayList<PoEProposta> getPropostas(){
        return data.getPropostas();
    }
    public PoEProposta getPropostaById(String nrProposta){
        return data.getPropostaById(nrProposta);
    }
    public ArrayList<PoEProposta> getPropostasByTitle(String titulo){
        return data.getPropostasByTitle(titulo);
    }
    public ArrayList<PoEProposta> getPropostasByType(String tipo){
        return data.getPropostasByType(tipo);
    }
    public boolean removeProposta(PoEProposta proposta) { return data.removeProposta(proposta); }
    public ArrayList<PoECandidatura> getCandidaturas(){
        return data.getCandidaturas();
    }
    public void addCandidatura(PoECandidatura candidatura){
        data.addCandidatura(candidatura);
    }
    public boolean removeCandidatura(PoECandidatura candidatura) { return data.removeCandidatura(candidatura); }
    public PoECandidatura getCandidaturaByAluno(Long nrAluno){
        PoEAluno aluno = data.getAlunoById(nrAluno);
        if(aluno == null) return null;
        return data.getCandidaturaByAluno(aluno);
    }
    public ArrayList<PoECandidatura> getCandidaturasByProposta(String nrProposta){
        PoEProposta proposta = data.getPropostaById(nrProposta);
        if(proposta == null) return new ArrayList<>();
        return data.getCandidaturasByProposta(proposta);
    }

    public boolean isPhaseClosed(PoEState state){
        return data.isPhaseClosed(state);
    }

}
