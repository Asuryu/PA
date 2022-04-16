package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PoEData {
    private final ArrayList<PoEAluno> alunos;
    private final ArrayList<PoEDocente> docentes;
    private final ArrayList<PoEProposta> propostas;

    private final ArrayList<PoEState> closedPhases;

    public PoEData() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        closedPhases = new ArrayList<>();
    }

    public ArrayList<PoEAluno> getAlunos() {
        return alunos;
    }
    public PoEAluno getAlunoById(long nrEstudante) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getNrEstudante() == nrEstudante) {
                return aluno;
            }
        }
        return null;
    }
    public ArrayList<PoEAluno> getAlunosByName(String nomeAluno) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }
    public ArrayList<PoEAluno> getAlunosByCurso(String curso) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }
    public ArrayList<PoEAluno> getAlunosByRamo(String ramo) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getRamo().equalsIgnoreCase(ramo)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }
    public PoEAluno getAlunoByEmail(String email) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getEmail().equalsIgnoreCase(email)) {
                return aluno;
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
        return docentes;
    }
    public PoEDocente getDocenteByName(String nome) {
        for (PoEDocente docente : docentes) {
            if (docente.getNome().equals(nome)) {
                return docente;
            }
        }
        return null;
    }
    public PoEDocente getDocenteByEmail(String email) {
        for (PoEDocente docente : docentes) {
            if (docente.getEmail().equals(email)) {
                return docente;
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
        return propostas;
    }
    public PoEProposta getPropostaById(String nrProposta) {
        for (PoEProposta proposta : propostas) {
            if (proposta.getId().equalsIgnoreCase(nrProposta)) {
                return proposta;
            }
        }
        return null;
    }
    public ArrayList<PoEProposta> getPropostasByTitle(String titulo) {
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (proposta.getTitulo().equalsIgnoreCase(titulo)) {
                propostasEncontradas.add(proposta);
            }
        }
        return propostasEncontradas;
    }
    public ArrayList<PoEProposta> getPropostasByType(String tipo){
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (tipo.equalsIgnoreCase("T1"))
                if(proposta instanceof PoEEstagio) propostasEncontradas.add(proposta);
            else if (tipo.equalsIgnoreCase("T2"))
                if(proposta instanceof PoEProjeto) propostasEncontradas.add(proposta);
            else if (tipo.equalsIgnoreCase("T3"))
                if(proposta instanceof PoEAutoproposto) propostasEncontradas.add(proposta);
        }
        return propostasEncontradas;
    }
    public void addProposta(PoEProposta proposta) {
        propostas.add(proposta);
    }
    public boolean removeProposta(PoEProposta proposta) {
        return propostas.remove(proposta);
    }

    public void closePhase(PoEState state) {
        if(!closedPhases.contains(state)) {
            closedPhases.add(state);
        }
    }
    public boolean isPhaseClosed(PoEState state) {
        return closedPhases.contains(state);
    }
}
