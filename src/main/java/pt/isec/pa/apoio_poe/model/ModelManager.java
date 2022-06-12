package pt.isec.pa.apoio_poe.model;

import pt.isec.pa.apoio_poe.model.fsm.PoEContext;
import pt.isec.pa.apoio_poe.model.fsm.PoEState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ModelManager {
    public static final String PROP_STATE = "state";
    public static final String PROP_DATA  = "data";

    final PoEContext ctx;
    final PropertyChangeSupport pcs;

    public ModelManager() {
        this.ctx = new PoEContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public PoEState getState() {
        return ctx.getState();
    }

    public boolean isClosed() {
        return ctx.isClosed();
    }

    public void next() {
        ctx.nextPhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }

    public void previous() {
        ctx.previousPhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }

    public void close() {
        ctx.closePhase();
        pcs.firePropertyChange(PROP_STATE,null,ctx.getState());
    }
}
