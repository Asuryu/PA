package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.PoEData;

class ConfigState extends PoEStateAdapter {
    ConfigState(PoEContext context, PoEData data) {
        super(context, data);
    }

    @Override
    public PoEState getState(){
        return PoEState.CONFIG;
    }
}
