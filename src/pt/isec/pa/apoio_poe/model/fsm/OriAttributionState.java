package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class OriAttributionState extends PoEStateAdapter {
    OriAttributionState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
        changeState(new PropAttributionState(context, data));
        return true;
    }

    @Override
    public boolean nextPhase(){
        changeState(new ReviewState(context, data));
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
        changeState(new OriAttributionState(context, data));
        return true;
    }

    @Override
    public PoEState getState(){
        return PoEState.ORI_ATTRIBUTION;
    }
}
