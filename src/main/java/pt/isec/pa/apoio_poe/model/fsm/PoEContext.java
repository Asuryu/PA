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

    public PoEContext(PoEContext c){
        data = new PoEData(c.data);
        state = c.state;
    }

    void changeState(IPoEState newState){
        state = newState;
    }

    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    public void closePhase(){
        state.closePhase();
    }

    public boolean isClosed(){ return state.isClosed(); }

    public void nextPhase(){
        state.nextPhase();
    }

    public void previousPhase(){
        state.previousPhase();
    }

    public boolean exitAndSave(String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))){
            oos.writeObject(this);
            return true;
        }catch(Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("[!] Erro ao carregar o ficheiro");
            return false;
        }
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

    public void addAlunosCSV(String filePath){
        state.addAlunosCSV(filePath);
    }

    public void saveAlunosCSV(String filePath){
        state.saveAlunosCSV(filePath);
    }

    public void addDocentesCSV(String filePath){
        state.addDocentesCSV(filePath);
    }

    public void saveDocentesCSV(String filePath){
        state.saveDocentesCSV(filePath);
    }

    public void addPropostasCSV(String filePath){
        state.addPropostasCSV(filePath);
    }

    public void savePropostasCSV(String filePath){
        state.savePropostasCSV(filePath);
    }

    public void addCandidaturasCSV(String filePath){
        state.addCandidaturasCSV(filePath);
    }

    public void saveCandidaturasCSV(String filePath){
        state.saveCandidaturasCSV(filePath);
    }

    public ArrayList<PoEOrientador> getOrientador(){ return data.getOrientadores(); }

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
    public void removeAluno(PoEAluno aluno){
        data.removeAluno(aluno);
    }
    public ArrayList<PoEDocente> getDocentes() {
        return data.getDocentes();
    }
    public PoEDocente getDocenteByEmail(String email){
        return data.getDocenteByEmail(email);
    }
    public PoEDocente getDocenteByName(String nome) {
        return data.getDocenteByName(nome);
    }
    public void removeDocente(PoEDocente docente){
        data.removeDocente(docente);
    }
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
    public void removeProposta(PoEProposta proposta) {
        data.removeProposta(proposta);
    }
    public ArrayList<PoECandidatura> getCandidaturas(){
        return data.getCandidaturas();
    }
    public void addCandidatura(PoECandidatura candidatura){
        data.addCandidatura(candidatura);
    }
    public void removeCandidatura(PoECandidatura candidatura) {
        data.removeCandidatura(candidatura);
    }
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
    public void addOrientador(PoEOrientador orientador){
        data.addOrientador(orientador);
    }
    public ArrayList<PoEOrientador> getOrientadores(){
        return data.getOrientadores();
    }
    public PoEOrientador getOrientadorByDocente(PoEDocente docente){
        return data.getOrientadorByDocente(docente);
    }
    public ArrayList<PoEOrientador> getOrientadoresByProjeto(PoEProposta proposta){
        return data.getOrientadoresByProjeto(proposta);
    }

    public boolean isPhaseClosed(PoEState state){
        return data.isPhaseClosed(state);
    }

    public boolean removeOrientador(PoEOrientador orientador) {
        return data.removeOrientador(orientador);
    }
}
