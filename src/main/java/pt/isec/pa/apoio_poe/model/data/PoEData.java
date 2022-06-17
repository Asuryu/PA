package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A classe PoEData reperesenta os dados geridos pela aplicação.
 */
public class PoEData implements Serializable{
    static final long serialVersionUID = 102L;
    private final ArrayList<PoEAluno> alunos;
    private final ArrayList<PoEDocente> docentes;
    private final ArrayList<PoEProposta> propostas;
    private final ArrayList<PoECandidatura> candidaturas;
    private final ArrayList<PoEOrientador> orientadores;

    private final ArrayList<PoEState> closedPhases;

    /**
     * Construtor de um PoEData.
     */
    public PoEData() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        candidaturas = new ArrayList<>();
        closedPhases = new ArrayList<>();
        orientadores = new ArrayList<>();
    }

    /**
     * Construtor de um PoEData.
     * @param data PoEData a ser copiada.
     */
    public PoEData(PoEData data) {
        this.alunos = new ArrayList<>(data.getAlunos());
        this.docentes = new ArrayList<>(data.getDocentes());
        this.propostas = new ArrayList<>(data.getPropostas());
        this.candidaturas = new ArrayList<>(data.getCandidaturas());
        this.closedPhases = new ArrayList<>(data.getClosedPhases());
        this.orientadores = new ArrayList<>(data.getOrientadores());
    }

    /**
     * Obtém os alunos.
     * @return Alunos.
     */
    public ArrayList<PoEAluno> getAlunos() {
        return alunos;
    }

    /**
     * Obtém um aluno através do seu número de estudante
     * @param nrEstudante Número de estudante do aluno a procurar
     * @return Aluno caso exista uma correspondência ou null se não for encontrado nenhum aluno com esse número de estudante.
     */
    public PoEAluno getAlunoById(long nrEstudante) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getNrEstudante() == nrEstudante) {
                return aluno;
            }
        }
        return null;
    }

    /**
     * Obtém os alunos atráves do seu nome
     * @param nomeAluno Nome dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByName(String nomeAluno) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }

    /**
     * Obtém os alunos de um determinado curso
     * @param curso Curso dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByCurso(String curso) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }

    /**
     * Obtém alunos de um determinado ramo
     * @param ramo Ramo dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByRamo(String ramo) {
        ArrayList<PoEAluno> alunosEncontrados = new ArrayList<>();
        for (PoEAluno aluno : alunos) {
            if (aluno.getRamo().equalsIgnoreCase(ramo)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }

    /**
     * Obtém um aluno através do seu email
     * @param email Email do aluno a procurar
     * @return Aluno caso exista uma correspondência ou null se não for encontrado nenhum aluno com esse email.
     */
    public PoEAluno getAlunoByEmail(String email) {
        for (PoEAluno aluno : alunos) {
            if (aluno.getEmail().equalsIgnoreCase(email)) {
                return aluno;
            }
        }
        return null;
    }

    /**
     * Adiciona aluno à lista de alunos
     * @param aluno Aluno a adicionar
     */
    public void addAluno(PoEAluno aluno) {
        alunos.add(aluno);
    }

    /**
     * Remove aluno da lista de alunos
     * @param aluno Aluno a remover
     */
    public void removeAluno(PoEAluno aluno) {
        alunos.remove(aluno);
    }

    /**
     * Obtém a lista de docentes
     * @return Lista de docentes
     */
    public ArrayList<PoEDocente> getDocentes() {
        return docentes;
    }

    /**
     * Obtém docente através do nome.
     * @param nome Nome do docente a procurar.
     * @return Docente caso exista uma correspondência ou null se não for encontrado nenhum docente com esse nome.
     */
    public PoEDocente getDocenteByName(String nome) {
        for (PoEDocente docente : docentes) {
            if (docente.getNome().equals(nome)) {
                return docente;
            }
        }
        return null;
    }

    /**
     * Obtém docente através do email
     * @param email Email do docente
     * @return Docente caso exista uma correspondência ou null se não for encontrado nenhum docente com esse email.
     */
    public PoEDocente getDocenteByEmail(String email) {
        for (PoEDocente docente : docentes) {
            if (docente.getEmail().equals(email)) {
                return docente;
            }
        }
        return null;
    }

    /**
     * Adiciona o docente à lista de docentes
     * @param docente Docente a adicionar
     */
    public void addDocente(PoEDocente docente) {
        docentes.add(docente);
    }

    /**
     * Remove o docente da lista de docentes
     * @param docente Docente a remover
     */
    public void removeDocente(PoEDocente docente) {
        docentes.remove(docente);
    }

    /**
     * Obtém a lista de propostas
     * @return Lista de propostas
     */
    public ArrayList<PoEProposta> getPropostas() {
        return new ArrayList<>(propostas);
    }

    /**
     * Obtém proposta através do seu ID
     * @param nrProposta ID da proposta a encontrar
     * @return Proposta caso exista uma correspondência ou null se não for encontrado nenhuma proposta com esse ID.
     */
    public PoEProposta getPropostaById(String nrProposta) {
        for (PoEProposta proposta : propostas) {
            if (proposta.getId().equalsIgnoreCase(nrProposta)) {
                return proposta;
            }
        }
        return null;
    }

    /**
     * Obtém propostas através do seu título
     * @param titulo Título da propostas a encontrar
     * @return Lista de propostas com o título dado
     */
    public ArrayList<PoEProposta> getPropostasByTitle(String titulo) {
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (proposta.getTitulo().equalsIgnoreCase(titulo)) {
                propostasEncontradas.add(proposta);
            }
        }
        return propostasEncontradas;
    }

    /**
     * Obtém propostas através do seu tipo
     * @param tipo Tipo de propostas a encontrar
     * @return Lista de propostas com o tipo dado
     */
    public ArrayList<PoEProposta> getPropostasByType(String tipo){
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for (PoEProposta proposta : propostas) {
            if (tipo.equalsIgnoreCase("T1"))
                if(proposta instanceof PoEEstagio) propostasEncontradas.add(proposta);
            if (tipo.equalsIgnoreCase("T2"))
                if(proposta instanceof PoEProjeto) propostasEncontradas.add(proposta);
            if (tipo.equalsIgnoreCase("T3"))
                if (proposta instanceof PoEAutoproposto) propostasEncontradas.add(proposta);
        }
        return propostasEncontradas;
    }

    /**
     * Obtém as propostas através do seu ramo de destino
     * @param ramo Ramo de destino das propostas a encontrar
     * @return Lista de propostas com o ramo de destino dado
     */
    public ArrayList<PoEProposta> getPropostasByRamo(String ramo){
        ArrayList<PoEProposta> propostasEncontradas = new ArrayList<>();
        for(PoEProposta proposta : propostas){
            if(proposta.getRamosDestino().contains(ramo)){
                propostasEncontradas.add(proposta);
            }
        }
        return propostasEncontradas;
    }

    /**
     * Adiciona a proposta à lista de propostas
     * @param proposta Proposta a adicionar
     */
    public void addProposta(PoEProposta proposta) {
        propostas.add(proposta);
    }

    /**
     * Remove a proposta da lista de propostas
     * @param proposta Proposta a remover.
     */
    public void removeProposta(PoEProposta proposta) {
        propostas.remove(proposta);
    }

    /**
     * Adiciona a candidatura à lista de candidaturas
     * @param candidatura Candidatura a adicionar
     */
    public void addCandidatura(PoECandidatura candidatura) {
        candidaturas.add(candidatura);
    }

    /**
     * Remove a candidatura da lista de candidaturas
     * @param candidatura Candidatura a remover
     */
    public void removeCandidatura(PoECandidatura candidatura) {
        candidaturas.remove(candidatura);
    }

    /**
     * Obtém a lista de candidaturas
     * @return Lista de candidaturas
     */
    public ArrayList<PoECandidatura> getCandidaturas() {
        return new ArrayList<>(candidaturas);
    }

    /**
     * Obtém candidatura efetuada por um aluno
     * @param aluno Aluno que efetuou a candidatura
     * @return Candidatura caso exista uma correspondência ou null se não for encontrada nenhuma candidatura efetuada por esse aluno.
     */
    public PoECandidatura getCandidaturaByAluno(PoEAluno aluno) {
        for (PoECandidatura candidatura : candidaturas) {
            if (candidatura.getNrEstudante() == aluno.getNrEstudante()) {
                return candidatura;
            }
        }
        return null;
    }

    /**
     * Obtém candidaturas através de uma proposta
     * @param proposta Proposta que as candidaturas estão associadas
     * @return Lista de candidaturas para a proposta dada
     */
    public ArrayList<PoECandidatura> getCandidaturasByProposta(PoEProposta proposta) {
        ArrayList<PoECandidatura> candidaturasEncontradas = new ArrayList<>();
        for (PoECandidatura candidatura : candidaturas) {
            if (candidatura.getPreferencias().contains(proposta.getId())) {
                candidaturasEncontradas.add(candidatura);
            }
        }
        return candidaturasEncontradas;
    }

    /**
     * Fecha a fase
     * Adiciona o estado à lista de estados fechados.
     * (Isto é uma má ideia)
     * @param state Estado a adicionar à lista de estados fechados
     */
    public void closePhase(PoEState state) {
        if(!closedPhases.contains(state)) {
            closedPhases.add(state);
        }
    }

    /**
     * Verifica se um certo estado está fechado
     * @param state Estado a verificar
     * @return true se o estado estiver fechado, false caso contrário
     */
    public boolean isPhaseClosed(PoEState state) {
        return closedPhases.contains(state);
    }

    /**
     * Adiciona um orientador à lista de orientadores
     * @param orientador Orientador a adicionar
     */
    public void addOrientador(PoEOrientador orientador) {
        orientadores.add(orientador);
    }

    /**
     * Obtém a lista de orientadores
     * @return Lista de orientadores
     */
    public ArrayList<PoEOrientador> getOrientadores(){
        return new ArrayList<>(orientadores);
    }

    /**
     * Obtém um orientador através do seu objeto docente
     * @param docente Docente que representa o orientador a obter
     * @return Orientador correspondente ao docente dado ou null se não for encontrado nenhum orientador com o docente dado
     * (Isto é uma má ideia)
     */
    public PoEOrientador getOrientadorByDocente(PoEDocente docente){
        for(PoEOrientador orientador : orientadores){
            if(orientador.getDocente() == docente)
                return orientador;
        }
        return null;
    }

    /**
     * Obtém orientadores associados a uma proposta
     * @param proposta Proposta a que os orientadores estão associados
     * @return Lista de orientadores associados à proposta dada
     */
    public ArrayList<PoEOrientador> getOrientadoresByProjeto(PoEProposta proposta){
        ArrayList<PoEOrientador> orientadoresEncontrados = new ArrayList<>();
        for(PoEOrientador orientador : orientadores){
            if(orientador.getPropostas().contains(proposta))
                orientadoresEncontrados.add(orientador);
        }
        return orientadoresEncontrados;
    }

    /**
     * Remove um orientador da lista de orientadores
     * @param orientador Orientador a remover
     * @return true se o orientador foi removido, false caso contrário
     */
    public boolean removeOrientador(PoEOrientador orientador){
        return orientadores.remove(orientador);
    }

    /**
     * Obtém a lista de estados fechados
     * @return Lista de estados fechados
     */
    public ArrayList<PoEState> getClosedPhases() {
        return new ArrayList<>(closedPhases);
    }
}
