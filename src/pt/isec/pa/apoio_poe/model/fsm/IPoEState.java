package pt.isec.pa.apoio_poe.model.fsm;

public interface IPoEState {

    boolean closePhase();
    boolean nextPhase();
    boolean previousPhase();
    boolean exitAndSave();
    boolean loadSave();

    PoEState getState();
}
