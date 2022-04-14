package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class ReviewState extends PoEStateAdapter {
    ReviewState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public boolean previousPhase(){
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
        data.closePhase(getState());
        return true;
    }

    @Override
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    @Override
    public PoEState getState(){
        return PoEState.REVIEW;
    }
}
