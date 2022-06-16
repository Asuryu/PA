package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;

import pt.isec.pa.apoio_poe.model.data.PoEData;

abstract class PoEStateAdapter implements IPoEState, Serializable{
    static final long serialVersionUID = 111L;
    protected final PoEContext context;
    protected final PoEData data;

    protected PoEStateAdapter(PoEContext context, PoEData data){
        this.context = context;
        this.data = data;
    }

    /**
     * Altera o estado da máquina de estados
     * @param newState novo estado da máquina de estados
     */
    protected void changeState(IPoEState newState){
        context.changeState(newState);
    }

    /**
     * Fecha a fase atual
     * @return
     */
    @Override
    public ReturnValue closePhase() { return ReturnValue.NULL; }

    /**
     * Verifica se a fase se encontra fechada
     * @return true se a fase estiver fechada, false caso contrário
     */
    @Override
    public boolean isClosed() { return false; }

    /**
     * Avança para a próxima fase da máquina de estados
     */
    @Override
    public boolean nextPhase() { return false; }

    /**
     * Retrocede para a fase anterior da máquina de estados
     */
    @Override
    public boolean previousPhase() { return false; }

    @Override
    public boolean addAlunosCSV(String filePath) { return false; }

    @Override
    public boolean saveAlunosCSV(String filePath) { return false; }

    @Override
    public boolean addDocentesCSV(String filePath) { return false; }

    @Override
    public boolean saveDocentesCSV(String filePath) { return false; }

    @Override
    public boolean addPropostasCSV(String filePath) { return false; }

    @Override
    public boolean savePropostasCSV(String filePath) { return false; }

    @Override
    public boolean addCandidaturasCSV(String filePath) { return false; }

    @Override
    public boolean saveCandidaturasCSV(String filePath) { return false; }

}
