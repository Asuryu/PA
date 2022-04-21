package pt.isec.pa.apoio_poe.model.fsm;

import java.io.Serializable;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class ReviewState extends PoEStateAdapter implements Serializable{
    static final long serialVersionUID = 113L;
    ReviewState(PoEContext context, PoEData data) {
        super(context, data);
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
    public PoEState getState(){
        return PoEState.REVIEW;
    }
}
