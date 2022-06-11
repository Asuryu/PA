package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.PoEState;
import pt.isec.pa.apoio_poe.model.memento.CareTaker;
import pt.isec.pa.apoio_poe.model.memento.MyOriginator;

import java.io.Serializable;
import java.util.ArrayList;

public class PoEData implements Serializable{
    static final long serialVersionUID = 102L;
    private final ArrayList<PoEAluno> alunos;
    private final ArrayList<PoEDocente> docentes;
    private final ArrayList<PoEProposta> propostas;
    private final ArrayList<PoECandidatura> candidaturas;
    private final ArrayList<PoEOrientador> orientadores;

    private final ArrayList<PoEState> closedPhases;

    public PoEData() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        candidaturas = new ArrayList<>();
        closedPhases = new ArrayList<>();
        orientadores = new ArrayList<>();
    }

    public PoEData(PoEData data) {
        this.alunos = new ArrayList<>(data.getAlunos());
        this.docentes = new ArrayList<>(data.getDocentes());
        this.propostas = new ArrayList<>(data.getPropostas());
        this.candidaturas = new ArrayList<>(data.getCandidaturas());
        this.closedPhases = new ArrayList<>(data.getClosedPhases());
        this.orientadores = new ArrayList<>(data.getOrientadores());
    }

    public ArrayList<PoEAluno> getAlunos() {
        ArrayList<PoEAluno> copy = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            copy.add(new PoEAluno(aluno));
        }
        return copy;
    }
    public PoEAluno getAlunoById(long nrEstudante) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getNrEstudante() == nrEstudante) {
                return new PoEAluno(aluno);
            }
        }
        return null;
    }
    public ArrayList<PoEAluno> getAlunosByName(String nomeAluno) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunosEncontrados.add(new PoEAluno(aluno));
            }
        }
        return alunosEncontrados;
    }
    public ArrayList<PoEAluno> getAlunosByCurso(String curso) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosEncontrados.add(new PoEAluno(aluno));
            }
        }
        return alunosEncontrados;
    }
    public ArrayList<PoEAluno> getAlunosByRamo(String ramo) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getRamo().equalsIgnoreCase(ramo)) {
                alunosEncontrados.add(new PoEAluno(aluno));
            }
        }
        return alunosEncontrados;
    }
    public PoEAluno getAlunoByEmail(String email) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getEmail().equalsIgnoreCase(email)) {
                return new PoEAluno(aluno);
            }
        }
        return null;
    }
    public void addAluno(PoEAluno aluno) {
        alunos.add(aluno);
    }
    public boolean removeAluno(PoEAluno aluno) {
        return alunos.remove(aluno);
    }

    public ArrayList<PoEDocente> getDocentes() {
        ArrayList<PoEDocente> copy = new ArrayList<>();
        for (PoEDocente docente : docentes) {
            copy.add(new PoEDocente(docente));
        }
        return copy;
    }
    public PoEDocente getDocenteByName(String nome) {
        for (PoEDocente docente : docentes) {
            if (docente.getNome().equals(nome)) {
                return new PoEDocente(docente);
            }
        }
        return null;
    }
    public PoEDocente getDocenteByEmail(String email) {
        for (PoEDocente docente : docentes) {
            if (docente.getEmail().equals(email)) {
                return new PoEDocente(docente);
            }
        }
        return null;
    }
    public void addDocente(PoEDocente docente) {
        docentes.add(docente);
    }
    public boolean removeDocente(PoEDocente docente) {
        return docentes.remove(docente);
    }

    public ArrayList<PoEProposta> getPropostas() {
        ArrayList<PoEProposta> copy = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            copy.add(new PoEProposta(proposta));
        }
        return copy;
    }
    public PoEProposta getPropostaById(String nrProposta) {
        for (PoEProposta proposta : propostas) {
            if (proposta.getId().equalsIgnoreCase(nrProposta)) {
                return new PoEProposta(proposta);
            }
        }
        return null;
    }
    public ArrayList<PoEProposta> getPropostasByTitle(String titulo) {
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (proposta.getTitulo().equalsIgnoreCase(titulo)) {
                propostasEncontradas.add(new PoEProposta(proposta));
            }
        }
        return propostasEncontradas;
    }
    public ArrayList<PoEProposta> getPropostasByType(String tipo){
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (tipo.equalsIgnoreCase("T1"))
                if(proposta instanceof PoEEstagio) propostasEncontradas.add(new PoEProposta(proposta));
            if (tipo.equalsIgnoreCase("T2"))
                if(proposta instanceof PoEProjeto) propostasEncontradas.add(new PoEProposta(proposta));
            if (tipo.equalsIgnoreCase("T3"))
                if (proposta instanceof PoEAutoproposto) propostasEncontradas.add(new PoEProposta(proposta));
        }
        return propostasEncontradas;
    }
    public ArrayList<PoEProposta> getPropostasByRamo(String ramo){
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for(PoEProposta proposta : propostas){
            if(proposta.getRamosDestino().contains(ramo)){
                propostasEncontradas.add(new PoEProposta(proposta));
            }
        }
        return propostasEncontradas;
    }
    public void addProposta(PoEProposta proposta) {
        propostas.add(proposta);
    }
    public boolean removeProposta(PoEProposta proposta) {
        return propostas.remove(proposta);
    }

    public void addCandidatura(PoECandidatura candidatura) {
        candidaturas.add(candidatura);
    }
    public boolean removeCandidatura(PoECandidatura candidatura) {
        return candidaturas.remove(candidatura);
    }
    public ArrayList<PoECandidatura> getCandidaturas() {
        ArrayList<PoECandidatura> copy = new ArrayList<>();
        for (PoECandidatura candidatura : candidaturas) {
            copy.add(new PoECandidatura(candidatura));
        }
        return copy;
    }
    public PoECandidatura getCandidaturaByAluno(PoEAluno aluno) {
        for (PoECandidatura candidatura : candidaturas) {
            if (candidatura.getNrEstudante() == aluno.getNrEstudante()) {
                return new PoECandidatura(candidatura);
            }
        }
        return null;
    }
    public ArrayList<PoECandidatura> getCandidaturasByProposta(PoEProposta proposta) {
        ArrayList<PoECandidatura> candidaturasEncontradas = new ArrayList<>();
        for (PoECandidatura candidatura : candidaturas) {
            if (candidatura.getPreferencias().contains(proposta.getId())) {
                candidaturasEncontradas.add(new PoECandidatura(candidatura));
            }
        }
        return candidaturasEncontradas;
    }

    public void closePhase(PoEState state) {
        if(!closedPhases.contains(state)) {
            closedPhases.add(state);
        }
    }
    public boolean isPhaseClosed(PoEState state) {
        return closedPhases.contains(state);
    }

    public void addOrientador(PoEOrientador orientador) {
        orientadores.add(orientador);
    }

    public ArrayList<PoEOrientador> getOrientadores(){
        ArrayList<PoEOrientador> copy = new ArrayList<>();
        for (PoEOrientador orientador : orientadores) {
            copy.add(new PoEOrientador(orientador));
        }
        return copy;
    }

    public PoEOrientador getOrientadorByDocente(PoEDocente docente){
        for(PoEOrientador orientador : orientadores){
            if(orientador.getDocente() == docente)
                return new PoEOrientador(orientador);
        }
        return null;
    }

    public ArrayList<PoEOrientador> getOrientadoresByProjeto(PoEProposta proposta){
        ArrayList<PoEOrientador> orientadoresEncontrados = new ArrayList<>();
        for(PoEOrientador orientador : orientadores){
            if(orientador.getPropostas().contains(proposta))
                orientadoresEncontrados.add(new PoEOrientador(orientador));
        }
        return orientadoresEncontrados;
    }

    public boolean removeOrientador(PoEOrientador orientador){
        return orientadores.remove(orientador);
    }

    public ArrayList<PoEState> getClosedPhases() {
        ArrayList<PoEState> copy = new ArrayList<>();
        for (PoEState state : closedPhases) {
            copy.add(state);
        }
        return copy;
    }
}
