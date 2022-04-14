package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.IPoEState;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.util.ArrayList;

public class PoEData {
    private ArrayList<PoEAluno> alunos;
    private ArrayList<PoEDocente> docentes;
    private ArrayList<PoEProposta> propostas;

    private ArrayList<PoEState> closedPhases;

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
    public void addAluno(PoEAluno aluno) {
        alunos.add(aluno);
    }
    public void removeAluno(PoEAluno aluno) {
        alunos.remove(aluno);
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
    public void removeDocente(PoEDocente docente) {
        docentes.remove(docente);
    }

    public ArrayList<PoEProposta> getPropostas() {
        return propostas;
    }
    public PoEProposta getProposta(String nrProposta) {
        for (PoEProposta proposta : propostas) {
            if (proposta.getId().equalsIgnoreCase(nrProposta)) {
                return proposta;
            }
        }
        return null;
    }
    public void addProposta(PoEProposta proposta) {
        propostas.add(proposta);
    }
    public void removeProposta(PoEProposta proposta) {
        propostas.remove(proposta);
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
