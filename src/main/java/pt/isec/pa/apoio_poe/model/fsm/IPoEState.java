package pt.isec.pa.apoio_poe.model.fsm;

/**
 * A interface IPoEState contém o protótipo das funções que permitem trocas de estado.
 */
public interface IPoEState {

    /**
     * Fecha a fase atual
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    ReturnValue closePhase();

    /**
     * Verifica se a fase atual está fechada
     * @return true caso a fase atual esteja fechada, false caso contrário
     */
    boolean isClosed();

    /**
     * Avança para a próxima fase
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean nextPhase();

    /**
     * Retroceder para a fase anterior
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean previousPhase();

    /**
     * Adiciona alunos a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean addAlunosCSV(String filePath);

    /**
     * Guarda alunos num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean saveAlunosCSV(String filePath);

    /**
     * Adiciona docentes a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean addDocentesCSV(String filePath);

    /**
     * Guarda docentes num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean saveDocentesCSV(String filePath);

    /**
     * Adiciona propostas a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean addPropostasCSV(String filePath);

    /**
     * Guarda propostas num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean savePropostasCSV(String filePath);

    /**
     * Adiciona candidaturas a partir de um ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean addCandidaturasCSV(String filePath);

    /**
     * Guarda candidaturas num ficheiro CSV
     * @param filePath caminho do ficheiro CSV
     * @return true caso a operação seja bem sucedida, false caso contrário
     */
    boolean saveCandidaturasCSV(String filePath);

    /**
     * Obtém o estado atual da FSM
     * @return estado atual da FSM
     */
    PoEState getState();
}
