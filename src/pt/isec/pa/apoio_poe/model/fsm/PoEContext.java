package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

public class PoEContext {
    IPoEState state;
    PoEData data;

    public PoEContext(){
        data = new PoEData();
        state = PoEState.CONFIG.createState(this,data);
    }

    void changeState(IPoEState newState){
        state = newState;
    }

    public PoEState getState(){
        if(state == null)
            return null;
        return state.getState();
    }

    public void start(){}

    public void closePhase(){
        return data.closePhase();
    }

    public void nextPhase(){
        return data.nextPhase();
    }

    public void previousPhase(){
        return data.previousPhase();
    }

    public void exitAndSave(){
        return data.exitAndSave();
    }

    public void loadSave(String filename){
        return data.loadSave(String filename);
    }

}
