package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

abstract class PoEStateAdapter implements IPoEState {
    protected PoEContext context;
    protected PoEData data;

    protected PoEStateAdapter(PoEContext context, PoEData data){
        this.context = context;
        this.data = data;
    }

    protected void changeState(IPoEState newState){
        context.changeState(newState);
    }

    @Override
    public boolean closePhase() { return false; }

    @Override
    public boolean nextPhase() { return false; }

    @Override
    public boolean previousPhase() { return false; }

    @Override
    public boolean exitAndSave() { return false; }

    @Override
    public boolean loadSave() { return false; }

}
