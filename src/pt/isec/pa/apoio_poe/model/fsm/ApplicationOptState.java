package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class ApplicationOptState extends PoEStateAdapter{
    ApplicationOptState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public PoEState getState(){
        return PoEState.APPLICATION_OPT;
    }
}
