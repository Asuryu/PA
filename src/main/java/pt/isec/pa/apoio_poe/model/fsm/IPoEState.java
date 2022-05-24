package pt.isec.pa.apoio_poe.model.fsm;

public interface IPoEState {

    boolean closePhase();
    boolean isClosed();
    boolean nextPhase();
    boolean previousPhase();
    boolean addAlunosCSV(String filePath);
    boolean saveAlunosCSV(String filePath);
    boolean addDocentesCSV(String filePath);
    boolean saveDocentesCSV(String filePath);
    boolean addPropostasCSV(String filePath);
    boolean savePropostasCSV(String filePath);
    boolean addCandidaturasCSV(String filePath);
    boolean saveCandidaturasCSV(String filePath);

    PoEState getState();
}
