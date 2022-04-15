package pt.isec.pa.apoio_poe.model.fsm;

public interface IPoEState {

    boolean closePhase();
    boolean isClosed();
    boolean nextPhase();
    boolean previousPhase();
    boolean exitAndSave();
    boolean loadSave(String filename);
    boolean addAlunosCSV(String filePath);
    boolean saveAlunosCSV(String filePath);
    boolean addDocentesCSV(String filePath);
    boolean addPropostasCSV(String filePath);

    PoEState getState();
}
