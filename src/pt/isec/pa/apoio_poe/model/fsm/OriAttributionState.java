package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class OriAttributionState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 109L;
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
        // FALTA FAZER AS CONDIÇÕES DE FECHAMENTO DA FASE
        data.closePhase(getState());
        nextPhase();
        return true;
    }

    @Override
    public boolean isClosed() {
        return data.isPhaseClosed(getState());
    }

    @Override
    public PoEState getState(){
        return PoEState.ORI_ATTRIBUTION;
    }
}
