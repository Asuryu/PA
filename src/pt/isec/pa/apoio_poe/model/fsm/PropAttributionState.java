package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class PropAttributionState extends PoEStateAdapter {
    PropAttributionState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public PoEState getState(){
        return PoEState.PROP_ATTRIBUTION;
    }
}
