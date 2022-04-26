package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class PropAttributionState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 112L;
    PropAttributionState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
        changeState(new ApplicationOptState(context, data));
        return true;
    }

    @Override
    public boolean nextPhase(){
        changeState(new OriAttributionState(context, data));
        return true;
    }

    @Override
    public boolean exitAndSave(){
        //rever
        return true;
    }

    @Override
    public boolean loadSave(String filename){
        //rever
        return true;
    }

    @Override
    public boolean closePhase(){
        data.closePhase(PoEState.PROP_ATTRIBUTION);
        nextPhase();
        return true;
    }

    @Override
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    @Override
    public PoEState getState(){
        return PoEState.PROP_ATTRIBUTION;
    }
}
