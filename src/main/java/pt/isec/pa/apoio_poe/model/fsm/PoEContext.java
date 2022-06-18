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

/**
 * A classe PoEContext implementa o contexto da aplicação.
 * Serve como ponto de ligação entre o modelo e a interface.
 * Os métodos desta classe redirecionam os pedidos de execução para o estado atual.
 */
public class PoEContext implements Serializable{
    static final long serialVersionUID = 110L;
    IPoEState state;
    public PoEData data;

    /**
     * Construtor da classe PoEContext.
     */
    public PoEContext(){
        data = new PoEData();
        state = new ConfigState(this, data);
    }

    /**
     * Construtor cópia da classe PoEContext.
     * @param c contexto a ser copiado
     */
    public PoEContext(PoEContext c){
        data = new PoEData(c.data);
        state = c.state;
    }

    /**
     * Altera o estado da máquina de estados
     * @param newState novo estado da máquina de estados
     */
    void changeState(IPoEState newState){
        state = newState;
    }

    /**
     * Obtém o estado da máquina de estados
     * @return estado da máquina de estados
     */
    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    /**
     * Fecha a fase atual
     */
    public ReturnValue closePhase(){
        return state.closePhase();
    }

    /**
     * Verifica se a fase se encontra fechada
     * @return true se a fase estiver fechada, false caso contrário
     */
    public boolean isClosed(){ return state.isClosed(); }

    /**
     * Avança para a próxima fase da máquina de estados
     */
    public void nextPhase(){
        state.nextPhase();
    }

    /**
     * Retrocede para a fase anterior da máquina de estados
     */
    public void previousPhase(){
        state.previousPhase();
    }

    /**
     * Salva o estado da aplicação num ficheiro
     * @param filename nome do ficheiro
     * @return true se o ficheiro foi salvo com sucesso, false caso contrário
     */
    public boolean exitAndSave(String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))){
            oos.writeObject(this);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Carrega o estado da aplicação de um ficheiro
     * @param filename nome do ficheiro
     * @return true se o ficheiro foi carregado com sucesso, false caso contrário
     */
    public PoEContext loadSave(String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
            PoEContext context = (PoEContext)ois.readObject();
            this.data = context.data;
            this.state = context.state;
            return context;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Adiciona alunos a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void addAlunosCSV(String filePath){
        state.addAlunosCSV(filePath);
    }

    /**
     * Guarda alunos num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void saveAlunosCSV(String filePath){
        state.saveAlunosCSV(filePath);
    }

    /**
     * Adiciona alunos a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void addDocentesCSV(String filePath){
        state.addDocentesCSV(filePath);
    }

    /**
     * Guarda docentes num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void saveDocentesCSV(String filePath){
        state.saveDocentesCSV(filePath);
    }

    /**
     * Adiciona propostas a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void addPropostasCSV(String filePath){
        state.addPropostasCSV(filePath);
    }

    /**
     * Guarda propostas num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void savePropostasCSV(String filePath){
        state.savePropostasCSV(filePath);
    }

    /**
     * Adiciona candidaturas a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void addCandidaturasCSV(String filePath){
        state.addCandidaturasCSV(filePath);
    }

    /**
     * Guarda candidaturas num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    public void saveCandidaturasCSV(String filePath){
        state.saveCandidaturasCSV(filePath);
    }

    /**
     * Obtém os alunos.
     * @return Alunos.
     */
    public ArrayList<PoEAluno> getAlunos(){
        return data.getAlunos();
    }

    /**
     * Obtém um aluno através do seu número de estudante
     * @param nrAluno Número de estudante do aluno a procurar
     * @return Aluno caso exista uma correspondência ou null se não for encontrado nenhum aluno com esse número de estudante.
     */
    public PoEAluno getAlunoById(long nrAluno){
        return data.getAlunoById(nrAluno);
    }

    /**
     * Obtém os alunos atráves do seu nome
     * @param nomeAluno Nome dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByName(String nomeAluno){
        return data.getAlunosByName(nomeAluno);
    }

    /**
     * Obtém os alunos de um determinado curso
     * @param curso Curso dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByCurso(String curso){
        return data.getAlunosByCurso(curso);
    }

    /**
     * Obtém alunos de um determinado ramo
     * @param ramo Ramo dos alunos a procurar
     * @return Lista de alunos
     */
    public ArrayList<PoEAluno> getAlunosByRamo(String ramo){
        return data.getAlunosByRamo(ramo);
    }

    /**
     * Obtém um aluno através do seu email
     * @param email Email do aluno a procurar
     * @return Aluno caso exista uma correspondência ou null se não for encontrado nenhum aluno com esse email.
     */
    public PoEAluno getAlunoByEmail(String email){ return data.getAlunoByEmail(email); }

    /**
     * Remove aluno da lista de alunos
     * @param aluno Aluno a remover
     */
    public boolean removeAluno(PoEAluno aluno){
        return data.removeAluno(aluno);
    }

    /**
     * Obtém a lista de docentes
     * @return Lista de docentes
     */
    public ArrayList<PoEDocente> getDocentes() {
        return data.getDocentes();
    }

    /**
     * Obtém docente através do email
     * @param email Email do docente
     * @return Docente caso exista uma correspondência ou null se não for encontrado nenhum docente com esse email.
     */
    public PoEDocente getDocenteByEmail(String email){
        return data.getDocenteByEmail(email);
    }

    /**
     * Obtém docente através do nome.
     * @param nome Nome do docente a procurar.
     * @return Docente caso exista uma correspondência ou null se não for encontrado nenhum docente com esse nome.
     */
    public PoEDocente getDocenteByName(String nome) {
        return data.getDocenteByName(nome);
    }

    /**
     * Remove o docente da lista de docentes
     * @param docente Docente a remover
     */
    public boolean removeDocente(PoEDocente docente){
        return data.removeDocente(docente);
    }

    /**
     * Obtém a lista de propostas
     * @return Lista de propostas
     */
    public ArrayList<PoEProposta> getPropostas(){
        return data.getPropostas();
    }

    /**
     * Obtém proposta através do seu ID
     * @param nrProposta ID da proposta a encontrar
     * @return Proposta caso exista uma correspondência ou null se não for encontrado nenhuma proposta com esse ID.
     */
    public PoEProposta getPropostaById(String nrProposta){
        return data.getPropostaById(nrProposta);
    }

    /**
     * Obtém propostas através do seu título
     * @param titulo Título da propostas a encontrar
     * @return Lista de propostas com o título dado
     */
    public ArrayList<PoEProposta> getPropostasByTitle(String titulo){
        return data.getPropostasByTitle(titulo);
    }

    /**
     * Obtém propostas através do seu tipo
     * @param tipo Tipo de propostas a encontrar
     * @return Lista de propostas com o tipo dado
     */
    public ArrayList<PoEProposta> getPropostasByType(String tipo){
        return data.getPropostasByType(tipo);
    }

    /**
     * Remove a proposta da lista de propostas
     * @param proposta Proposta a remover.
     */
    public boolean removeProposta(PoEProposta proposta) {
        return data.removeProposta(proposta);
    }

    /**
     * Obtém a lista de candidaturas
     * @return Lista de candidaturas
     */
    public ArrayList<PoECandidatura> getCandidaturas(){
        return data.getCandidaturas();
    }

    /**
     * Adiciona a candidatura à lista de candidaturas
     * @param candidatura Candidatura a adicionar
     */
    public void addCandidatura(PoECandidatura candidatura){
        data.addCandidatura(candidatura);
    }

    /**
     * Remove a candidatura da lista de candidaturas
     * @param candidatura Candidatura a remover
     */
    public void removeCandidatura(PoECandidatura candidatura) {
        data.removeCandidatura(candidatura);
    }

    /**
     * Obtém candidatura efetuada por um aluno
     * @param aluno Aluno que efetuou a candidatura
     * @return Candidatura caso exista uma correspondência ou null se não for encontrada nenhuma candidatura efetuada por esse aluno.
     */
    public PoECandidatura getCandidaturaByAluno(Long nrAluno){
        PoEAluno aluno = data.getAlunoById(nrAluno);
        if(aluno == null) return null;
        return data.getCandidaturaByAluno(aluno);
    }

    /**
     * Obtém candidaturas através de uma proposta
     * @param proposta Proposta que as candidaturas estão associadas
     * @return Lista de candidaturas para a proposta dada
     */
    public ArrayList<PoECandidatura> getCandidaturasByProposta(String nrProposta){
        PoEProposta proposta = data.getPropostaById(nrProposta);
        if(proposta == null) return new ArrayList<>();
        return data.getCandidaturasByProposta(proposta);
    }

    /**
     * Adiciona um orientador à lista de orientadores
     * @param orientador Orientador a adicionar
     */
    public void addOrientador(PoEOrientador orientador){
        data.addOrientador(orientador);
    }

    /**
     * Obtém a lista de orientadores
     * @return Lista de orientadores
     */
    public ArrayList<PoEOrientador> getOrientadores(){
        return data.getOrientadores();
    }

    /**
     * Obtém um orientador através do seu objeto docente
     * @param docente Docente que representa o orientador a obter
     * @return Orientador correspondente ao docente dado ou null se não for encontrado nenhum orientador com o docente dado
     * (Isto é uma má ideia)
     */
    public PoEOrientador getOrientadorByDocente(PoEDocente docente){
        return data.getOrientadorByDocente(docente);
    }

    /**
     * Obtém orientadores associados a uma proposta
     * @param proposta Proposta a que os orientadores estão associados
     * @return Lista de orientadores associados à proposta dada
     */
    public ArrayList<PoEOrientador> getOrientadoresByProjeto(PoEProposta proposta){
        return data.getOrientadoresByProjeto(proposta);
    }

    /**
     * Remove um orientador da lista de orientadores
     * @param orientador Orientador a remover
     * @return true se o orientador foi removido, false caso contrário
     */
    public boolean removeOrientador(PoEOrientador orientador) {
        return data.removeOrientador(orientador);
    }

    /**
     * Verifica se um certo estado está fechado
     * @param state Estado a verificar
     * @return true se o estado estiver fechado, false caso contrário
     */
    public boolean isPhaseClosed(PoEState state){
        return data.isPhaseClosed(state);
    }
}
