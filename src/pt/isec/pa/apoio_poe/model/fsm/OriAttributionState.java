package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;
import java.util.ArrayList;

import pt.isec.pa.apoio_poe.model.data.PoEAluno;
import pt.isec.pa.apoio_poe.model.data.PoECandidatura;
import pt.isec.pa.apoio_poe.model.data.PoEData;
import pt.isec.pa.apoio_poe.utils.Utils;

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
