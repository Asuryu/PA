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
    public boolean closePhase(){
        data.closePhase(getState());
        changeState(new ReviewState(context, data));
        System.out.println("[·] Fase de ATRIBUIÇÃO DE ORIENTADORES fechada com sucesso!");
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
