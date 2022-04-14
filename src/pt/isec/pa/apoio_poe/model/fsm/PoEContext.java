package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

public class PoEContext {
    IPoEState state;
    PoEData data;

    public PoEContext(){
        data = new PoEData();
        state = new ConfigState(this, data);
    }

    void changeState(IPoEState newState){
        state = newState;
    }

    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    public boolean closePhase(){
        return state.closePhase();
    }

    public boolean isClosed(){ return state.isClosed(); }

    public boolean nextPhase(){
        return state.nextPhase();
    }

    public boolean previousPhase(){
        return state.previousPhase();
    }

    public boolean exitAndSave(){
        return state.exitAndSave();
    }

    public boolean loadSave(String filename){
        return state.loadSave(filename);
    }

    public boolean addAlunosCSV(){ return state.addAlunosCSV(); }

    public boolean addDocentesCSV(){ return state.addDocentesCSV(); }

    public boolean addPropostasCSV(){ return state.addPropostasCSV(); }

}
